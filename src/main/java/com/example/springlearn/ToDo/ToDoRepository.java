package com.example.springlearn.ToDo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//Used to tell this is a repository to Hibernate
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
 
}