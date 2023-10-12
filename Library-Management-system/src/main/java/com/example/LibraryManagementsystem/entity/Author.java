package com.example.LibraryManagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
@NoArgsConstructor //default const.
@AllArgsConstructor //All args const.
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    private String qualification;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    List<Book> book = new ArrayList<>(); //Since when the first author is registered, we won't have the data of all the books
}
