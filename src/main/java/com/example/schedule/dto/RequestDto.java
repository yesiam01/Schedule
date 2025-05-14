package com.example.schedule.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class RequestDto {
    private String name;
    private String todo;
    private String password;

    public RequestDto(String name, String todo, String password) {
        this.name = name;
        this.todo = todo;
        this.password = password;
    }
}