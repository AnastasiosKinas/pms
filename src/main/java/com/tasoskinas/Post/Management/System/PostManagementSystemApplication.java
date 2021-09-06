package com.tasoskinas.Post.Management.System;

import com.tasoskinas.Post.Management.System.service.InitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PostManagementSystemApplication {
    @Autowired
    InitDataService initDataService;

    public static void main(String[] args) {
        SpringApplication.run(PostManagementSystemApplication.class, args);
    }

    @PostConstruct
    public void init() {
        initDataService.createInitData();
    }
}
