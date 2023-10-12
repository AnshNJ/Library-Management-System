package com.example.LibraryManagementsystem.service.impl;

import com.example.LibraryManagementsystem.DTO.RequestDTO.IssueBookRequestDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.IssueBookResponseDto;
import com.example.LibraryManagementsystem.entity.Book;
import com.example.LibraryManagementsystem.entity.Card;
import com.example.LibraryManagementsystem.entity.Transaction;
import com.example.LibraryManagementsystem.enums.CardStatus;
import com.example.LibraryManagementsystem.enums.TransactionStatus;
import com.example.LibraryManagementsystem.exceptions.BookNotFound;
import com.example.LibraryManagementsystem.exceptions.BookUnavailable;
import com.example.LibraryManagementsystem.exceptions.CardNotActive;
import com.example.LibraryManagementsystem.exceptions.CardNotFound;
import com.example.LibraryManagementsystem.repository.BookRepository;
import com.example.LibraryManagementsystem.repository.CardRepository;
import com.example.LibraryManagementsystem.repository.TransactionRepository;
import com.example.LibraryManagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    @Autowired
    CardRepository cardRepo;
    @Autowired
    BookRepository bookRepo;
    @Autowired
    TransactionRepository transactionRepo;
    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws CardNotFound, BookNotFound, CardNotActive, BookUnavailable {
        //Create a transaction irrespective of card or book presence
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIsIssueOperation(true);

        //Try obtaining Card
        Card card;
        try{
            card = cardRepo.findById(issueBookRequestDto.getCardId()).get();
        } catch( Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepo.save(transaction);
            throw new CardNotFound("Invalid Card Id!");
        }
        transaction.setCard(card); //Add card to transaction

        //Try obtaining Book
        Book book;
        try{
            book = bookRepo.findById(issueBookRequestDto.getBookId()).get();
        } catch( Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepo.save(transaction);
            throw new BookNotFound("Invalid Book Id!");
        }
        transaction.setBook(book); //Add book to transaction

        //If Card status is not active
        if(card.getCardStatus() != CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepo.save(transaction);
            throw new CardNotActive("Card is not Active!");
        }

        //If book is already issued
        if(book.getIsIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepo.save(transaction);
            throw new BookUnavailable("Book unavailable.");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        //Finish up all the entities
        book.setIsIssued(true);
        book.setCard(card);
        book.getTransactionList().add(transaction);
        card.getBookList().add(book);
        card.getTransactionList().add(transaction);

        //Since card is the parent of both book and transaction, saving card will save the children data in db as well
        cardRepo.save(card);

        //prepare response
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());
        issueBookResponseDto.setBookName(book.getTitle());

        return issueBookResponseDto;
    }
}
