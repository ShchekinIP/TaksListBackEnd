package com.example.takslistbackend.controller;


import com.example.takslistbackend.entity.CategoryEntity;
import com.example.takslistbackend.repo.CategoryRepository;
import com.example.takslistbackend.search.CategorySearchValues;
import com.example.takslistbackend.service.CategoryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<CategoryEntity> findAll() {
        return categoryService.findAllByOrderByTitleAsc();
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() != null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.add(category));

    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CategoryEntity category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.update(category));
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable Long id) {

        CategoryEntity category = null;

        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(category);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/search")
    public ResponseEntity<List<CategoryEntity>> search(@RequestBody CategorySearchValues categorySearchValues) {
        return ResponseEntity.ok(categoryService.findByTitle(categorySearchValues.getText()));
    }


}
