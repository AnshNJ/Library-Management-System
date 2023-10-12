package com.example.LibraryManagementsystem.service;

import com.example.LibraryManagementsystem.DTO.RequestDTO.IssueBookRequestDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.IssueBookResponseDto;
import com.example.LibraryManagementsystem.exceptions.BookNotFound;
import com.example.LibraryManagementsystem.exceptions.BookUnavailable;
import com.example.LibraryManagementsystem.exceptions.CardNotActive;
import com.example.LibraryManagementsystem.exceptions.CardNotFound;

public interface TransactionService {
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws CardNotFound, BookNotFound, CardNotActive, BookUnavailable;

}
