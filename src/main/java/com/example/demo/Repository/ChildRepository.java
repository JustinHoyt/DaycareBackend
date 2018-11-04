package com.example.demo.Repository;

import com.example.demo.Entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Integer> {
}
