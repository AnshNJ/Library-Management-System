package com.example.LibraryManagementsystem.service.impl;

import com.example.LibraryManagementsystem.DTO.RequestDTO.StudentRequestDto;
import com.example.LibraryManagementsystem.DTO.RequestDTO.UpdateStudentMobileRequestDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.CardResponseDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.StudentResponseDto;
import com.example.LibraryManagementsystem.DTO.ResponseDTO.UpdateStudentMobileResponseDto;
import com.example.LibraryManagementsystem.entity.Card;
import com.example.LibraryManagementsystem.entity.Student;
import com.example.LibraryManagementsystem.enums.CardStatus;
import com.example.LibraryManagementsystem.exceptions.StudentNotFound;
import com.example.LibraryManagementsystem.repository.StudentRepository;
import com.example.LibraryManagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        //Create student obj using DTO
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobile(studentRequestDto.getMobile());

        //Generate a new card obj since we want to add a card as well when adding a student to db
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setValidTill(LocalDate.of(2024, 01, 01));

        //Establish relationships between both the parent and child
        card.setStudent(student);
        student.setCard(card);

        //We don't have to save in card repo thanks to cascade
        studentRepo.save(student);
        return "Student added successfully";
    }

    @Override
    public StudentResponseDto getStudentById(Integer studentId) throws Exception {

        try {

            Student student = studentRepo.findById(studentId).get();

            //prepare response
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setMobile(student.getMobile());
            student.setDepartment(student.getDepartment());

            //Prepare card details and add to student dto
            Card card = student.getCard();
            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setId(card.getId());
            cardResponseDto.setIssueDate(card.getIssueDate());
            cardResponseDto.setValidTill(card.getValidTill());
            cardResponseDto.setCardStatus(card.getCardStatus());

            studentResponseDto.setCardResponseDto(cardResponseDto);

            return studentResponseDto;

        } catch(Exception e){
            throw new Exception("Invalid id!");
        }

    }

    @Override
    public UpdateStudentMobileResponseDto updateMobileNo(UpdateStudentMobileRequestDto updateStudentMobileRequestDto) throws StudentNotFound {
        try{
            //Find student obj
            Student student = studentRepo.findById(updateStudentMobileRequestDto.getId()).get();
            student.setMobile(updateStudentMobileRequestDto.getMobile());
            Student updatedStudent = studentRepo.save(student); //save() returns the value

            //prepare response
            UpdateStudentMobileResponseDto updateStudentMobileResponseDto = new UpdateStudentMobileResponseDto();
            updateStudentMobileResponseDto.setName(updatedStudent.getName());
            updateStudentMobileResponseDto.setMobile(updatedStudent.getMobile());

            return updateStudentMobileResponseDto;

        } catch (Exception e){
            throw new StudentNotFound("Invalid student ID");
        }
    }
}
