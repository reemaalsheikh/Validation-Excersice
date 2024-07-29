package com.example.q2validationexcersice.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Event {

    //Event Class : ID , description , capacity, startDate , endDate
// • ID Cannot be null Length more than 2
//• Description Cannot be null Length more than 15
//• Capacity Cannot be null It has to be number It must be more than 25
//• startDate • endDate

    @NotEmpty(message= "Id should not be Empty!")
    @Size(min=3,max=10)
    private String id;

    @NotEmpty(message= "Description should not be Empty!")
    @Size(min=16,max=50)
    private String description;

    @NotNull(message= "Capacity should not be Empty!")
    @Min(26)
    @Max(100)
    @Positive(message= "You should enter positive numbers only!")
    private int capacity;

    //( use @JsonFormat(pattern="yyyy-MM-dd") and LocalDateTime )
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
}
