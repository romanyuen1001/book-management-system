package com.roman.bookmanagementsystem.services.validate;

public interface ValidateService {
    void validateBookId(Long id);
    void validateAuthor(String string);
    void validateTitle(String string);
}
