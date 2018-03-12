package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.SubGridEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubGridRepositoy extends JpaRepository<SubGridEntity, Long> {

    SubGridEntity findByLabelUrl(String subGris);
}
