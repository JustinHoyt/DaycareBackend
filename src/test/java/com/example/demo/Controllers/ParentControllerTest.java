package com.example.demo.Controllers;

import com.example.demo.Entities.Child;
import com.example.demo.Entities.Parent;
import com.example.demo.Repository.ParentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ParentController.class)
public class ParentControllerTest {

    @MockBean
    ParentRepository mockParentRepository;

    @Autowired
    MockMvc mockMvc;

    @Mock
    Child child1;

    @Mock
    Child child2;

    @Mock
    Parent parent;

    Optional<Parent> optionalParent;

    @Before
    public void setUp() throws Exception {
        child1 = Child.builder()
                .parents(asList(parent))
                .name("sam")
                .age(2)
                .build();
        child2 = Child.builder()
                .parents(asList(parent))
                .name("paul")
                .age(3)
                .build();
        List<Child> children = asList(child1, child2);
        parent = Parent.builder()
                .name("Ben")
                .age(24)
                .children(children)
                .build();
        optionalParent = Optional.of(parent);
    }

    @Test
    public void shouldReturnChildrenOfParent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONArray expectedResponse = new JSONArray();
        expectedResponse.appendElement(new JSONObject(mapper.convertValue(child1, Map.class)));
        expectedResponse.appendElement(new JSONObject(mapper.convertValue(child2, Map.class)));

        when(mockParentRepository.findById(1)).thenReturn(optionalParent);

        mockMvc.perform(get("/parent/children?parentId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse.toJSONString()));
    }

    @Test
    public void shouldReturnParent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject expectedResponse = new JSONObject(mapper.convertValue(parent, Map.class));

        when(mockParentRepository.findById(1)).thenReturn(optionalParent);

        mockMvc.perform(get("/parent?parentId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse.toJSONString()));
    }

    @Test
    public void shouldSaveParent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject requestBody = new JSONObject(mapper.convertValue(parent, Map.class));
        when(mockParentRepository.save(parent)).thenReturn(parent);

        JSONObject expectedJson = new JSONObject(mapper.convertValue(parent, Map.class));

        System.out.println(expectedJson);
        mockMvc.perform(post("/parent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody.toJSONString()))
                .andExpect(status().isOk());
    }

}