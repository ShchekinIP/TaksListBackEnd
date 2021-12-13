package com.example.takslistbackend.repo;

import com.example.takslistbackend.entity.StatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends CrudRepository<StatEntity, Long> {

}
