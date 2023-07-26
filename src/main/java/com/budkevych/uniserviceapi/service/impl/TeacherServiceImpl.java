package com.budkevych.uniserviceapi.service.impl;

import com.budkevych.uniserviceapi.model.Teacher;
import com.budkevych.uniserviceapi.repository.TeacherRepository;
import com.budkevych.uniserviceapi.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher get(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No teacher found for id " + id));
    }

    @Override
    public Teacher update(Long id, Teacher teacher) {
        get(id);
        teacher.setId(id);
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

}
