package com.roman.bookmanagementsystem.services.validate;

public interface ValidateService {
    void validateBookId(Long id);
    void validateAuther(String string);
    void validateTitle(String string);
}
