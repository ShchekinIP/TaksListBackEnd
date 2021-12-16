package com.example.takslistbackend.service;

import com.example.takslistbackend.entity.StatEntity;
import com.example.takslistbackend.repo.StatRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service


@Transactional
public class StatService {

    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public StatEntity findById(Long id){
        return repository.findById(id).get();
    }


}
