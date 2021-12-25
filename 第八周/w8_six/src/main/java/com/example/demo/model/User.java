package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
public class User {

    private Long id;

    private String username;

    private String mobile;
}
