package com.example.springandangular_group2_ams.repository;

import com.example.springandangular_group2_ams.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
