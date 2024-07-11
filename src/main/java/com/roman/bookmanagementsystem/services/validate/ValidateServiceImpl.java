package com.roman.bookmanagementsystem.services.validate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    @Override
    public void validateBookId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid book ID: " + id);
        }
    }

    @Override
    public void validateAuther(String string) {
        if (validateAlphanumericWithSpaces(string))
            throw new IllegalArgumentException("Invalid author: " + string);
    }

    @Override
    public void validateTitle(String string) {
        if (validateAlphanumericWithSpaces(string))
            throw new IllegalArgumentException("Invalid title: " + string);
    }

    private boolean validateAlphanumericWithSpaces(String string) {
        return (string == null || !string.matches("^[a-zA-Z0-9 ]+$"));
    }
}
