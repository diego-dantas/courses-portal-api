package com.course.portal.api.model.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.course.portal.api.model.dao.entity.StateEntity;;

public interface StateRepository extends JpaRepository<StateEntity, Long>{

}
