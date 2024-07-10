package com.roman.bookmanagementsystem.dtos;

import jakarta.validation.constraints.Pattern;

public class BookDto {
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Title must be alphanumeric only") // TODO: Study validation
    public String title;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Author must be alphanumeric only") // TODO: Study validation
    public String author;
    public Boolean published;
}
