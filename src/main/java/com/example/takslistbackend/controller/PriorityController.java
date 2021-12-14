package com.example.takslistbackend.controller;

import com.example.takslistbackend.entity.PriorityEntity;
import com.example.takslistbackend.repo.PriorityRepository;
import com.example.takslistbackend.search.PrioritySearchValues;
import com.example.takslistbackend.service.PriorityService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/all")
    public List<PriorityEntity> test() {
        return priorityService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<PriorityEntity> add(@RequestBody PriorityEntity priority) {
        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() != null && priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityService.add(priority));

    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody PriorityEntity priority) {
        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null && priority.getColor().trim().length() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityService.update(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PriorityEntity> findById(@PathVariable Long id) {

        PriorityEntity priority = null;

        try {
            priority = priorityService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(priority);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            priorityService.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/search")
    public ResponseEntity<List<PriorityEntity>> search(@RequestBody PrioritySearchValues prioritySearchValues) {
        return ResponseEntity.ok(priorityService.findByTitle(prioritySearchValues.getText()));
    }
}
