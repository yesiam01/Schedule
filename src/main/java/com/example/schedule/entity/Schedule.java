package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private Long id;
    private String name; //contents
    private String todo; //
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; //locatdatetime ,localdate


    public Schedule(String name, String todo, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.todo = todo;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}