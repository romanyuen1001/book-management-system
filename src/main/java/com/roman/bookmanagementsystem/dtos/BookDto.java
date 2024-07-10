package com.roman.bookmanagementsystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
    public Long id; // TODO: Remove and gen in service
    public String title; // TODO: alphanumeric only
    public String author; // TODO: alphanumeric only
    public Boolean published;
}
