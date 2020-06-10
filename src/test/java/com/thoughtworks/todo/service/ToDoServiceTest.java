package com.thoughtworks.todo.service;

import com.thoughtworks.todo.model.ToDo;
import com.thoughtworks.todo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ToDoServiceTest {
    @InjectMocks
    ToDoService toDoService;
    @Mock
    private ToDoRepository toDoRepository;


    @Test
    void shouldGetTheToDosWhenFindAllMethodCalls() {
        ToDo todoSample = new ToDo("Todo dummy text", true);
        when(toDoRepository.findAll()).thenReturn(Arrays.asList(todoSample));

        List<ToDo> allToDos = toDoService.getAllToDos();

        verify(toDoRepository, times(1)).findAll();
        assertEquals(todoSample, allToDos.get(0));
    }


    @Test
    void shouldAddTheToDoWhenSaveMethodCalls() {
        ToDo todoSample = new ToDo("Todo dummy text", true);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(todoSample);

        ToDo actualToDo = toDoService.addTodo(todoSample);

        verify(toDoRepository, times(1)).save(todoSample);
        assertEquals(todoSample.getId(), actualToDo.getId());
        assertEquals(todoSample.getText(), actualToDo.getText());
        assertEquals(todoSample.isCompleted(), actualToDo.isCompleted());
    }

    @Test
    void shouldUpdateTheToDoBasedOnId() {
        ToDo toDoSample = new ToDo(10L, "ToDo task", true);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(toDoSample);

        ToDo actualToDo = toDoService.updateToDoById(10L, toDoSample);

        verify(toDoRepository, times(1)).save(toDoSample);
        assertEquals(toDoSample.getId(), actualToDo.getId());
        assertEquals(toDoSample.getText(), actualToDo.getText());
        assertEquals(toDoSample.isCompleted(), actualToDo.isCompleted());
    }

    @Test
    void shouldDeleteTheToDoBasedOnId() {
        Long id = 12L;

        toDoService.deleteToDoById(id);

        verify(toDoRepository, times(1)).deleteById(id);
    }

}
