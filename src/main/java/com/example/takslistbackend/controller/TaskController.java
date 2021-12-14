package com.example.takslistbackend.controller;

import com.example.takslistbackend.entity.TaskEntity;
import com.example.takslistbackend.repo.TaskRepository;
import com.example.takslistbackend.search.TaskSearchValues;
import com.example.takslistbackend.service.TaskService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.LongFunction;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskEntity>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<TaskEntity> add(@RequestBody TaskEntity task) {
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() != null && task.getTitle().trim().length() != 0) {
            return new ResponseEntity("", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.add(task));

    }

    @PutMapping("/update")
    public ResponseEntity<TaskEntity> update(@RequestBody TaskEntity task) {
        if (task.getId() == null && task.getId() == 0) {
            return new ResponseEntity("", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskService.update(task));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TaskEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<TaskEntity>> search(@RequestBody TaskSearchValues taskSearchValues) {
        String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;
        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;
        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;




        Sort.Direction direction = sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;


        Sort sort = Sort.by(direction,sortColumn);
        PageRequest pageRequest = PageRequest.of(taskSearchValues.getPageNumber(), taskSearchValues.getPageSize());
        Page result = taskService.findByParams(title, completed, priorityId, categoryId, pageRequest);
        return ResponseEntity.ok(result);
    }
}
