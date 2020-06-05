package com.thoughtworks.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
