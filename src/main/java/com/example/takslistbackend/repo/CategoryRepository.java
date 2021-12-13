package com.example.takslistbackend.repo;

import com.example.takslistbackend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("select c from CategoryEntity c where " +
    "(:title is null or :title ='' or lower(c.title) like lower (concat('%' , :title, '%')))" +
    "order by c.title asc ")
    List<CategoryEntity> findByTitle(@Param("title")String title);

    List<CategoryEntity> findAllByOrderByTitleAsc();
}
