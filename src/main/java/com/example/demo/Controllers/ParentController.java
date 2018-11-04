package com.example.demo.Controllers;

import com.example.demo.Entities.Child;
import com.example.demo.Entities.Parent;
import com.example.demo.Repository.ParentRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/parent")
public class ParentController {

    @Autowired
    ParentRepository parentRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void postParent(@RequestBody Parent parent) {
        parentRepository.save(parent);
//        parent.getParentId();
//        Parent susan = Parent.builder()
//                .age(2)
//                .name("Susan")
//                .parentId(1)
//                .build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Optional<Parent> getParentById(@RequestParam Integer parentId) {
        return parentRepository.findById(parentId);
    }

    @RequestMapping(value = "/children", method = RequestMethod.GET)
    public List<Child> getChildrenByParentId(@RequestParam Integer parentId) {
        return parentRepository.findById(parentId).get().getChildren();
//                .stream()
//                .map(child -> child.getName())
//                .collect(Collectors.toList());
    }

}
