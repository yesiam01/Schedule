package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ResourceUrlProvider resourceUrlProvider;

    public JdbcTemplateScheduleRepositoryImpl(JdbcTemplate jdbcTemplate, ResourceUrlProvider resourceUrlProvider) {
        this.jdbcTemplate = jdbcTemplate;
        this.resourceUrlProvider = resourceUrlProvider;
    }

    @Override
    public Schedule save(Schedule schedule) {
        String sql = "INSERT INTO schedule (name, todo, password, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                schedule.getName(),
                schedule.getTodo(),
                schedule.getPassword(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());

        String selectSql = "SELECT * FROM schedule WHERE id = LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(selectSql, scheduleRowMapper());
    }

    @Override
    public List<Schedule> findAll() {
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, scheduleRowMapper());
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, scheduleRowMapper(), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int updateSchedule(Long id, String name, String todo) {
        String sql = "UPDATE schedule SET name = ?, todo = ?, password = ?, updatedAt = ? WHERE id = ?";
        return jdbcTemplate.update(sql, name, todo, null, null, id);
    }

    @Override
    public int deleteSchedule(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return (resultSet, rowNum) -> new Schedule(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("todo"),
                resultSet.getString("password"),
                resultSet.getTimestamp("createdAt").toLocalDateTime(),
                resultSet.getTimestamp("updatedAt").toLocalDateTime()
        );
    }
}

