package com.example.q1validationexercise.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

//In question 2 project Class : ID , title , description , status, companyName
// • ID Cannot be null Length more than 2
//• Title Cannot be null Length more than 8
//• Description Cannot be null Length more than 15
// • Status Cannot be null
//must be Not Started or in Progress or Completed only
//• companyName Cannot be null Length more than 6
@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "Id should not be EMPTY!")
    @Size(min=3,max=10)
    private String id;

    @NotEmpty(message="Title should not be EMPTY!")
    @Size(min=9,max=30)
    private String title;

    @NotEmpty(message="Description should not be EMPTY!")
    @Size(min=16,max=100)
    private String description;

    @NotEmpty(message="Status should not be EMPTY!")
    //must be Not Started or in Progress or Completed only
    @Pattern(regexp="^(Not Started|Progress|Completed)$",message = " 3 valid iputs only: Not Started or in Progress or Completed ")
    private String status;

    @NotEmpty(message="Description should not be EMPTY!")
    @Size(min=7,max=15)
    private String companyName;


}
