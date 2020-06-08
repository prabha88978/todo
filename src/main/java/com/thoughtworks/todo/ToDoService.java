package com.thoughtworks.todo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo addTodo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public ToDo updateToDoById(Long id, ToDo toDo) {
        return null;
    }
}
