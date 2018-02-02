package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

    ProviderEntity findByEmail(String email);
}
