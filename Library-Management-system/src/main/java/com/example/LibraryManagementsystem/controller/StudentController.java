package com.example.LibraryManagementsystem.controller;

import com.example.LibraryManagementsystem.DTO.RequestDTO.StudentRequestDto;
import com.example.LibraryManagementsystem.DTO.RequestDTO.UpdateStudentMobileRequestDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.StudentResponseDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.UpdateStudentMobileResponseDto;
import com.example.LibraryManagementsystem.exceptions.StudentNotFound;
import com.example.LibraryManagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    //ADD STUDENT
    @PostMapping("/add-student")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }

    //DELETE STUDENT BY ID

    //UPDATE STUDENT MOBILE BY ID
    @PutMapping("/update-mobileNo")
    public UpdateStudentMobileResponseDto updateMobileNo(UpdateStudentMobileRequestDto updateStudentMobileRequestDto) throws StudentNotFound {
        return studentService.updateMobileNo(updateStudentMobileRequestDto);
    }

    //FIND A STUDENT BY ID
    @GetMapping("/get-student-by-id")
    public StudentResponseDto getStudentById(@RequestParam Integer studentId) throws Exception {
        return studentService.getStudentById(studentId); //Since we changed the return type to dto, deserialization from java to json will not take place. Hence no infinite loop
    }

    //FIND ALL STUDENTS

}
