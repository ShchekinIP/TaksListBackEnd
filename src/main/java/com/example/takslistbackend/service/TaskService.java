package com.example.takslistbackend.service;

import com.example.takslistbackend.entity.TaskEntity;
import com.example.takslistbackend.repo.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

// всегда нужно создавать отдельный класс Service для доступа к данным, даже если кажется,
// что мало методов или это все можно реализовать сразу в контроллере
// Такой подход полезен для будущих доработок и правильной архитектуры (особенно, если работаете с транзакциями)
@Service

// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
@Transactional
public class TaskService {

    private final TaskRepository repository; // сервис имеет право обращаьтся к репозиторию (БД)

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<TaskEntity> findAll() {
        return repository.findAll();
    }

    public TaskEntity add(TaskEntity task) {
        return repository.save(task); // метод save обновляет или создает новый объект, если его не было
    }

    public TaskEntity update(TaskEntity task){
        return repository.save(task); // метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }


    public Page findByParams(String text, Integer completed, Long priorityId, Long categoryId, PageRequest paging){
        return repository.findByParams(text, completed, priorityId, categoryId, paging);
    }

    public TaskEntity findById(Long id){
        return repository.findById(id).get(); // т.к. возвращается Optional - нужно получить объект методом get()
    }


}
