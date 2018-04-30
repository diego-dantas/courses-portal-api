package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.NewsletterEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<NewsletterEntity, Long>{

}