package com.thoughtworks;

import com.thoughtworks.todo.ToDo;
import com.thoughtworks.todo.ToDoRepository;
import com.thoughtworks.todo.ToDoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoServiceTest {
    @Autowired
    private ToDoRepository toDoRepository;

    @AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
    }

    @Test
    void testShouldGetTheToDos() {
        ToDo todoSample = new ToDo("Todo dummy text", true);
        toDoRepository.save(todoSample);
        ToDoService toDoService = new ToDoService(toDoRepository);

        List<ToDo> toDoList = toDoService.getAllToDos();
        ToDo lastToDo = toDoList.get(toDoList.size() - 1);

        assertEquals(todoSample.getText(), lastToDo.getText());
        assertEquals(todoSample.isCompleted(), lastToDo.isCompleted());
        assertEquals(todoSample.getId(), lastToDo.getId());
    }

    @Test
    void testShouldAddTheToDo() {
        ToDoService toDoService = new ToDoService(toDoRepository);
        ToDo todoSample = new ToDo("Todo dummy text",true);

        toDoService.addTodo(todoSample);

        assertEquals(1.0, toDoRepository.count());
    }
}
