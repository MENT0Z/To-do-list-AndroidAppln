package com.mruraza.to_do;

public class Task {
    int id;
    String tasks;

    public Task(int id, String tasks) {
        this.id = id;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
}

