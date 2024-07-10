package com.roman.bookmanagementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
    public Long id;
    public String title;
    public String author;
    public Boolean published;
}
