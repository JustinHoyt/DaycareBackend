package com.example.demo.Service;

import com.example.demo.Entities.Child;
import com.example.demo.Entities.Parent;
import com.example.demo.Repository.ChildRepository;
import com.example.demo.Repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class SeederService {

    @Autowired
    Environment env;

    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if ("dev".equals(env.getProperty("spring.profiles.active"))) {
            seedParentWithTwoChildren();
        }
    }

    private void seedParentWithTwoChildren() {
        Child child1;
        Child child2;
        Parent parent;
        child1 = Child.builder()
                .name("Ben")
                .age(24)
                .build();
        child2 = Child.builder()
                .name("Cameron")
                .age(24)
                .build();

        List<Child> children = asList(child1, child2);
        children = childRepository.saveAll(children);

        parent = Parent.builder()
                .name("Susan")
                .age(40)
                .children(children)
                .build();

        parentRepository.save(parent);
    }

}
