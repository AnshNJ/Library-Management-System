package com.example.LibraryManagementsystem.DTO.ResponseDTO;

import com.example.LibraryManagementsystem.entity.Card;
import com.example.LibraryManagementsystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponseDto {
    private int id;
    private String name;
    private int age;
    private Department department;
    private String mobile;
    CardResponseDto cardResponseDto;
}
