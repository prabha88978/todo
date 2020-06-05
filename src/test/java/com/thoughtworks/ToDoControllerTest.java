package com.thoughtworks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.todo.ToDo;
import com.thoughtworks.todo.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ToDoService toDoService;

    @Test
    void getAllToDos() throws Exception {
        List<ToDo> toDoList = new ArrayList<ToDo>();
        toDoList.add(new ToDo(1L, "Do assignments daily", true));
        toDoList.add(new ToDo(2L, "Do exercise daily", true));
        toDoList.add(new ToDo(3L, "Sleep well daily", true));
        when(toDoService.getAllToDos()).thenReturn(toDoList);

        mockMvc.perform(get("/todos")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$", hasSize(3))).andDo(print());

    }

    @Test
    void testShouldSuccessfullyAddTheTodo() throws Exception {
        ToDo writeTodo = new ToDo("Write dairy daily", true);
        when(toDoService.addTodo(any(ToDo.class))).thenReturn(writeTodo);
        ObjectMapper objectMapper = new ObjectMapper();
        String writeToDoJSON = objectMapper.writeValueAsString(writeTodo);

        ResultActions result = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeToDoJSON)
        );

        result.andExpect(jsonPath("$.text").value("Write dairy daily"))
                .andExpect(jsonPath("$.completed").value(true));
    }
}
