package com.example.takslistbackend.repo;

import com.example.takslistbackend.entity.CategoryEntity;
import com.example.takslistbackend.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
    List<PriorityEntity> findAllByOrderByIdAsc();

}
