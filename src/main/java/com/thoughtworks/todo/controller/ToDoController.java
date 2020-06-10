package com.thoughtworks.todo.controller;

import com.thoughtworks.todo.model.ToDo;
import com.thoughtworks.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @RequestMapping("/todos")
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/todos")
    public ToDo addTodo(@RequestBody ToDo toDo) {
        return toDoService.addTodo(toDo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/todos/{id}")
    public ToDo updateToDoById(@RequestBody ToDo toDo, @PathVariable Long id) {
        return toDoService.updateToDoById(id, toDo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteToDoBasedOnId(@PathVariable Long id) {
        toDoService.deleteToDoById(id);
    }
}
