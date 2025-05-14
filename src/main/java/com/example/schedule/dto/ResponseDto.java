package com.example.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor

public class ResponseDto {
    private Long id;
    private String name;
    private String todo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseDto(Long id, String name, String todo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.todo = todo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
