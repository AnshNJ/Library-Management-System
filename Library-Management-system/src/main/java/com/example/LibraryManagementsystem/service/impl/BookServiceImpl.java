package com.example.LibraryManagementsystem.service.impl;

import com.example.LibraryManagementsystem.entity.Author;
import com.example.LibraryManagementsystem.entity.Book;
import com.example.LibraryManagementsystem.repository.AuthorRepository;
import com.example.LibraryManagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepo;
    public String addBook(Book book) throws Exception {
        Author currAuthor;
        //Add new book to existing author
        try{
            //We're passing an author obj with only the id. Hence, retrieval of that id shouldn't be an issue
            currAuthor = authorRepo.findById(book.getAuthor().getId()).get();
        } catch (Exception e){
            throw new Exception("Author not present");
        }

        book.setAuthor(currAuthor); //Since we are only passing author ID to book, we need to update the other details of author
        List<Book> currBookList = currAuthor.getBook();
        currBookList.add(book);

        authorRepo.save(currAuthor); //Book will automatically be saved since it is a child
        return "Book added successfully";
    }
}
