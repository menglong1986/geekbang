package com.example.eight.bean;

import lombok.Data;

import java.util.List;

@Data
public class School {

    private List<Klass> klasses;


    public School(List<Klass> klasses) {
        this.klasses = klasses;
    }

    @Override
    public String toString() {
        return "myclass::" + klasses.toString();
    }

}
