package com.example.LibraryManagementsystem.entity;

import com.example.LibraryManagementsystem.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@NoArgsConstructor //default const.
@AllArgsConstructor //All args const.
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int noOfPages;

    private int price;

    private Boolean isIssued = false;

    @ManyToOne
    private Author author;

    @ManyToOne
    @JoinColumn
    Card card;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();

}
