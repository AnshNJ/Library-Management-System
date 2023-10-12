package com.example.LibraryManagementsystem.entity;

import com.example.LibraryManagementsystem.enums.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student") //Write either way so there's no scope of confusion
@NoArgsConstructor //Lombok application - default const.
@AllArgsConstructor //All args const.
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Department department;

    private String mobile;

    @OneToOne(mappedBy = "student"  ,cascade = CascadeType.ALL) //Cascade - whatever operations are applied on parent, they are applied on child as well
    Card card;
}
