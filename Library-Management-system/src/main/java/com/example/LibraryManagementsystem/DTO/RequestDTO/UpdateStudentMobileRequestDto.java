package com.example.LibraryManagementsystem.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateStudentMobileRequestDto {
    private int id;
    private String name;
    private String mobile;
}
