package com.example.LibraryManagementsystem.service.impl;

import com.example.LibraryManagementsystem.DTO.ResponseDTO.AuthorEmailResponseDto;
import com.example.LibraryManagementsystem.entity.Author;
import com.example.LibraryManagementsystem.exceptions.AuthorNotFound;
import com.example.LibraryManagementsystem.repository.AuthorRepository;
import com.example.LibraryManagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepo;

    @Override
    public String addAuthor(Author author) {
        authorRepo.save(author);
        return "Author added successfully";
    }

    @Override
    public AuthorEmailResponseDto getAuthorByEmail(String email) throws AuthorNotFound {
        try {
            //Step 1: Define the find by method in your repo. Since we don't want multiple authors with same email, we will first update column email to unique
            //Step 2: call repo with defined method
            Author author = authorRepo.findByEmail(email);

            //form response
            AuthorEmailResponseDto authorEmailResponseDto = new AuthorEmailResponseDto();
            authorEmailResponseDto.setEmail(author.getEmail());
            authorEmailResponseDto.setName(author.getName());
            return authorEmailResponseDto;

        } catch (Exception e){

            throw new AuthorNotFound("Author not found");
        }


    }
}
