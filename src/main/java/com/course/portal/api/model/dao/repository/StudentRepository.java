package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<StudentEntity, Long>{

    StudentEntity findByEmail(String email);

}
