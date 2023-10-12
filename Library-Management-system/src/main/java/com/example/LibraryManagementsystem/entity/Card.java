package com.example.LibraryManagementsystem.entity;

import com.example.LibraryManagementsystem.enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
@NoArgsConstructor //default const.
@AllArgsConstructor //All args const.
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp //Automatically create a timestamp
    private LocalDate issueDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    private LocalDate validTill;

    //Cardinality between parent and child.
    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "card" , cascade = CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();

    @OneToMany(mappedBy = "card" , cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();
}
