package com.example.demo.Beans;

import com.example.demo.Beans.Child;
import lombok.Data;

import java.util.List;

@Data
public class Parent {
    public Parent(int parentId) {
        this.parentId = parentId;
    }

    private int parentId;
    private String name;
    private int age;
    private List<Child> children;
}
