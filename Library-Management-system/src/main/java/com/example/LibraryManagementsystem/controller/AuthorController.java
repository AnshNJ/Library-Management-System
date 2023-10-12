package com.example.LibraryManagementsystem.controller;

import com.example.LibraryManagementsystem.DTO.ResponseDTO.AuthorEmailResponseDto;
import com.example.LibraryManagementsystem.entity.Author;
import com.example.LibraryManagementsystem.exceptions.AuthorNotFound;
import com.example.LibraryManagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    //ADD AUTHOR
    @PostMapping("/add-author")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    //GET AUTHOR BY EMAIL
    @GetMapping("/get-author-by-email")
    public AuthorEmailResponseDto getAuthorByEmail(@RequestParam("email") String email) throws AuthorNotFound {
        return authorService.getAuthorByEmail(email);
    }
}
