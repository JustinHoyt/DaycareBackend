package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany(mappedBy = "children")
    @JsonIgnore
    private List<Parent> parents;
    private String name;
    private Integer age;
}
