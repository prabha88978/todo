package com.thoughtworks.todo.repository;

import com.thoughtworks.todo.model.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
    }

    @Test
    void shouldGetTheToDos() {
        ToDo todoSample = new ToDo("Todo dummy text", true);
        toDoRepository.save(todoSample);


        List<ToDo> toDoList = toDoRepository.findAll();
        ToDo lastToDo = toDoList.get(toDoList.size() - 1);

        assertEquals(todoSample.getText(), lastToDo.getText());
        assertEquals(todoSample.isCompleted(), lastToDo.isCompleted());
        assertEquals(todoSample.getId(), lastToDo.getId());
    }

    @Test
    void shouldAddTheToDo() {
        ToDo todoSample = new ToDo("Todo dummy text", true);

        ToDo expectedToDo = toDoRepository.save(todoSample);

        assertEquals(1.0, toDoRepository.count());
        assertEquals(expectedToDo.getText(), todoSample.getText());
        assertEquals(expectedToDo.getId(), todoSample.getId());
        assertEquals(expectedToDo.isCompleted(), todoSample.isCompleted());
    }

    @Test
    void shouldDeleteToDoById() {
        ToDo todoSample = new ToDo(1L, "exercise for 30 minutes", true);
        ToDo anotherTodoSample = new ToDo(2L, "Read for ten minutes", true);
        toDoRepository.save(todoSample);
        toDoRepository.save(anotherTodoSample);

        toDoRepository.deleteById(1L);

        assertEquals(1.0, toDoRepository.count());


    }
}
