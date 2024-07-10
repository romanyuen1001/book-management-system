package com.roman.bookmanagementsystem.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Title must be alphanumeric only")
    public String title;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Author must be alphanumeric only")
    public String author;
    public Boolean published;
}
