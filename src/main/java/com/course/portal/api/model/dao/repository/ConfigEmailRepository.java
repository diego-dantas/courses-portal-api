package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigEmailRepository extends JpaRepository<ConfigEmailEntity, Long> {
}
