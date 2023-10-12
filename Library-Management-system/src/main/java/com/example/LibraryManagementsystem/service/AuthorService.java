package com.example.LibraryManagementsystem.service;


import com.example.LibraryManagementsystem.DTO.ResponseDTO.AuthorEmailResponseDto;
import com.example.LibraryManagementsystem.entity.Author;
import com.example.LibraryManagementsystem.exceptions.AuthorNotFound;

public interface AuthorService {
    public String addAuthor(Author author);

    public AuthorEmailResponseDto getAuthorByEmail(String email) throws AuthorNotFound;
}
