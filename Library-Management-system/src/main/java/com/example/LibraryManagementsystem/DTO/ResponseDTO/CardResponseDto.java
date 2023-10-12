package com.example.LibraryManagementsystem.DTO.ResponseDTO;

import com.example.LibraryManagementsystem.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponseDto {
    private int id;
    private LocalDate issueDate;
    private CardStatus cardStatus;
    private LocalDate validTill;
}
