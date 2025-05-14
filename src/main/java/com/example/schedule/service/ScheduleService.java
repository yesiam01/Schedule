package com.example.schedule.service;

import com.example.schedule.dto.RequestDto;
import com.example.schedule.dto.ResponseDto;

import java.util.List;

public interface ScheduleService {

    ResponseDto createSchedule(RequestDto requestDto);

    List<ResponseDto> getAllSchedules();

    ResponseDto findScheduleById(Long id);

    ResponseDto updateSchedule(Long id, RequestDto requestDto);

    void deleteSchedule(Long id);
}
