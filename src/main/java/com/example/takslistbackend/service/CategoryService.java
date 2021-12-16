package com.example.takslistbackend.service;

import com.example.takslistbackend.entity.CategoryEntity;
import com.example.takslistbackend.repo.CategoryRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service


@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }


    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    public CategoryEntity add(CategoryEntity category) {
        return repository.save(category);
    }

    public CategoryEntity update(CategoryEntity category){
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<CategoryEntity> findByTitle(String text){
        return repository.findByTitle(text);
    }

    public CategoryEntity findById(Long id){
        return repository.findById(id).get();
    }

    public List<CategoryEntity> findAllByOrderByTitleAsc(){
        return repository.findAllByOrderByTitleAsc();
    }


}
