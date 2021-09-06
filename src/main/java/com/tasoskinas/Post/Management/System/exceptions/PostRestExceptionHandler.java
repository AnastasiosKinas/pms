package com.tasoskinas.Post.Management.System.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostRestExceptionHandler {

    // Add an exception handler for PostNotFoundException

    @ExceptionHandler
    public ResponseEntity<PostErrorResponse>handleException(PostNotFoundException exc) {

        // create PostErrorResponse

        PostErrorResponse error = new PostErrorResponse(
                HttpStatus.NOT_FOUND.value(),

                // we should not give the exception message on production
                // it's here just for debugging
                exc.getMessage(),

                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    // Add another exception handler ... to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<PostErrorResponse> handleException(Exception exc) {

        // create PostErrorResponse

       PostErrorResponse error = new PostErrorResponse(
                HttpStatus.BAD_REQUEST.value(),

                // we should not give the exception message on production
                // it's here just for debugging
                exc.getMessage(),

                System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}