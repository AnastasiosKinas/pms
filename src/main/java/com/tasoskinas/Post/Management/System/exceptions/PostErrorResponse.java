package com.tasoskinas.Post.Management.System.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}