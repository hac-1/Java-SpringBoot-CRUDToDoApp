package com.example.springlearn.ToDo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

interface OnUpdate {
}// used to filter certain condition for on update alone

interface OnCreate {
}// used to filter certain condition for on create alone

@Entity
@Table(name = "todo")
//The above 2 Annotations make the class also as a table representative for Spring Data JPA
public class ToDo {
    // THIS CLASS IS A BEAN

    //The id is generated for column task_id
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // input in json should be of type {"task":"taskDetails","priorty":"Low"}
    // if groups is specified and input function does not have a @Validated class then no validation takes place
    @Column(name = "task")
    @NotBlank(message = "Missing task",groups = {OnCreate.class})
    private String Task;

    @Column(name = "priority")
    @NotBlank(message = "Missing priority",groups = {OnCreate.class,OnUpdate.class}) 
    private String Priority;

    public String getTask() {
        return Task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    @Override
    public String toString() {
        return "ToDo [id=" + id + ", Task=" + Task + ", Priority=" + Priority + "]";
    }
}
