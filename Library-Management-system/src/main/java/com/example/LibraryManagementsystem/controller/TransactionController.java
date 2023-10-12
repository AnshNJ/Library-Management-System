package com.example.LibraryManagementsystem.controller;

import com.example.LibraryManagementsystem.DTO.RequestDTO.IssueBookRequestDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.IssueBookResponseDto;
import com.example.LibraryManagementsystem.exceptions.BookNotFound;
import com.example.LibraryManagementsystem.exceptions.BookUnavailable;
import com.example.LibraryManagementsystem.exceptions.CardNotActive;
import com.example.LibraryManagementsystem.exceptions.CardNotFound;
import com.example.LibraryManagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws BookNotFound, CardNotFound, CardNotActive, BookUnavailable {
        return transactionService.issueBook(issueBookRequestDto);
    }
}
