package com.example.springlearn.ToDo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Tells spring framework this is a service
public class ToDoService {
    @Autowired
    // Used to configure and fetch the toDoRepository properly
    private ToDoRepository toDoRepository;

    public List<ToDo> getAllRecords() {
        List<ToDo> todo_list = toDoRepository.findAll();
        return todo_list;
    }

    public boolean writeRecord(ToDo new_task) {
        try {
            toDoRepository.save(new_task);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public boolean updateRecord(int id, ToDo new_task) {
        try {
            new_task.setId(id);
            toDoRepository.save(new_task);// save is used for both update and create in JPA
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public boolean deleteRecord(int id) {
        try {
            toDoRepository.deleteById((long)id);// save is used for both update and delete in JPA
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }
}
