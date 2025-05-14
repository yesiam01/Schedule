package com.example.schedule.service;

import com.example.schedule.dto.RequestDto;
import com.example.schedule.dto.ResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ResponseDto createSchedule(RequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getName(),
                requestDto.getTodo(),
                requestDto.getPassword(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Schedule saved = scheduleRepository.save(schedule);
        return toResponseDto(saved);
    }

    @Override
    public List<ResponseDto> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));
        return toResponseDto(schedule);
    }

    @Override
    public ResponseDto updateSchedule(Long id, RequestDto requestDto) {
        if (requestDto.getName() == null || requestDto.getTodo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이름과 내용을 입력해야 합니다.");
        }

        int updated = scheduleRepository.updateSchedule(id, requestDto.getName(), requestDto.getTodo());
        if (updated == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "수정된 일정이 없습니다.");
        }

        Schedule updatedSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수정 후 일정을 찾을 수 없습니다."));

        return toResponseDto(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        int deleted = scheduleRepository.deleteSchedule(id);
        if (deleted == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 일정이 없습니다.");
        }
    }


    private ResponseDto toResponseDto(Schedule schedule) {
        return new ResponseDto(
                schedule.getId(),
                schedule.getName(),
                schedule.getTodo(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
