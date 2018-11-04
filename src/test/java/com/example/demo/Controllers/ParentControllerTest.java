package com.example.demo.Controllers;

import com.example.demo.Entities.Child;
import com.example.demo.Entities.Parent;
import com.example.demo.Repository.ParentRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
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
                .id(2)
                .parents(Arrays.asList(parent))
                .name("sam")
                .age(2)
                .build();
        child2 = Child.builder()
                .id(3)
                .parents(Arrays.asList(parent))
                .name("paul")
                .age(3)
                .build();
        List<Child> children = Arrays.asList(child1, child2);
        parent = Parent.builder()
                .id(1)
                .name("Ben")
                .age(24)
                .children(children)
                .build();
        optionalParent = Optional.of(parent);
    }

    @Test
    public void shouldReturnChildrenOfParent() throws Exception {
        JSONArray expectedResponse = getExpectedResponse();

        JSONObject requestBody = new JSONObject();
        requestBody.appendField("id", 1);

        when(mockParentRepository.findById(1)).thenReturn(optionalParent);

        mockMvc.perform(get("/parent/children?parentId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse.toJSONString()));
    }

    private JSONArray getExpectedResponse() {
        JSONArray expectedResponse = new JSONArray();
        JSONObject child1Json = new JSONObject();
        child1Json.appendField("name", "sam");
        child1Json.appendField("age", 2);
        JSONObject child2Json = new JSONObject();
        child2Json.appendField("name", "paul");
        child2Json.appendField("age", 3);
        expectedResponse.appendElement(child1Json);
        expectedResponse.appendElement(child2Json);
        return expectedResponse;
    }
}