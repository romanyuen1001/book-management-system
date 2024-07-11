package com.roman.bookmanagementsystem.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    public String title;
    public String author;
    public Boolean published;
}
