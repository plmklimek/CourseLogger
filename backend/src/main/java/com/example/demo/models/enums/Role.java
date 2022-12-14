package com.example.demo.models.enums;

public enum Role {
    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");
    private final String name;
    Role(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
