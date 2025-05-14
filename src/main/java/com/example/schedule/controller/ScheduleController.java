package com.example.schedule.controller;

import com.example.schedule.dto.RequestDto;
import com.example.schedule.dto.ResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 추가
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody RequestDto requestDto) {
        ResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 전체 일정
    @GetMapping
    public ResponseEntity<List<ResponseDto>> findAll() {
        List<ResponseDto> list = scheduleService.getAllSchedules();
        return ResponseEntity.ok(list);
    }

    // 일부 일정
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Long id) {
        ResponseDto dto = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(dto);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateSchedule(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        ResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateDelete(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
