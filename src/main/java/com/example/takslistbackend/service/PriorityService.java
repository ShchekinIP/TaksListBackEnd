package com.example.takslistbackend.service;

import com.example.takslistbackend.entity.PriorityEntity;
import com.example.takslistbackend.repo.PriorityRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service


@Transactional
public class PriorityService {

    private final PriorityRepository repository;

    public PriorityService(PriorityRepository repository) {
        this.repository = repository;
    }

    public List<PriorityEntity> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public PriorityEntity add(PriorityEntity priority) {
        return repository.save(priority);
    }

    public PriorityEntity update(PriorityEntity priority){
        return repository.save(priority);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public PriorityEntity findById(Long id){
        return repository.findById(id).get();
    }

    public List<PriorityEntity> findByTitle(String text){
        return repository.findByTitle(text);
    }

}
