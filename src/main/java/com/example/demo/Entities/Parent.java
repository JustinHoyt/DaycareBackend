package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Parent {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Parent_Child",
            joinColumns = { @JoinColumn(name = "parentId") },
            inverseJoinColumns = { @JoinColumn(name = "childId") }
    )
    private List<Child> children;
    private String name;
    private Integer age;
}
