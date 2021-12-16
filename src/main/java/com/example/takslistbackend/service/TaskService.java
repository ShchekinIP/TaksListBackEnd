package com.example.takslistbackend.service;

import com.example.takslistbackend.entity.TaskEntity;
import com.example.takslistbackend.repo.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service


@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<TaskEntity> findAll() {
        return repository.findAll();
    }

    public TaskEntity add(TaskEntity task) {
        return repository.save(task);
    }

    public TaskEntity update(TaskEntity task){
        return repository.save(task);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }


    public Page findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging){
        return repository.findByParams(text, completed, priorityId, categoryId, paging);
    }

    public TaskEntity findById(Long id){
        return repository.findById(id).get();
    }


}
