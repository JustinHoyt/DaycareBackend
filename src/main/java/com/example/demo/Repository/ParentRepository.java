package com.example.demo.Repository;

import com.example.demo.Entities.Child;
import com.example.demo.Entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
}
