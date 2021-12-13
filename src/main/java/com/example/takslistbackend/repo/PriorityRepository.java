package com.example.takslistbackend.repo;

import com.example.takslistbackend.entity.CategoryEntity;
import com.example.takslistbackend.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {
    List<PriorityEntity> findAllByOrderByIdAsc();


    @Query("select c from PriorityEntity c where " +
            "(:title is null or :title ='' or lower(c.title) like lower (concat('%' , :title, '%')))" +
            "order by c.title asc ")
    List<PriorityEntity> findByTitle(@Param("title")String title);

}
