package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.exceptions.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionController
{
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNoSentencesFound(NoContentException e)
    {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("error", e.getMessage()));
    }
}
