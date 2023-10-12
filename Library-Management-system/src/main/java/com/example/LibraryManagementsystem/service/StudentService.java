package com.example.LibraryManagementsystem.service;

import com.example.LibraryManagementsystem.DTO.RequestDTO.StudentRequestDto;
import com.example.LibraryManagementsystem.DTO.RequestDTO.UpdateStudentMobileRequestDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.StudentResponseDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.UpdateStudentMobileResponseDto;
import com.example.LibraryManagementsystem.exceptions.StudentNotFound;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public StudentResponseDto getStudentById(Integer studentId) throws Exception;

    public UpdateStudentMobileResponseDto updateMobileNo(UpdateStudentMobileRequestDto updateStudentMobileRequestDto) throws StudentNotFound;
}
