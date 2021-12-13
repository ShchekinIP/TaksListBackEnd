package com.example.takslistbackend.controller;

import com.example.takslistbackend.entity.StatEntity;
import com.example.takslistbackend.repo.StatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    private StatRepository statRepository;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    private final Long defaultId = 1L;

    @GetMapping("/stat")
    public ResponseEntity<StatEntity> findById(){
        return ResponseEntity.ok(statRepository.findById(defaultId).get());
    }
}
