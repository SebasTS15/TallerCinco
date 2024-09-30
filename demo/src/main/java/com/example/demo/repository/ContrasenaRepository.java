package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contrasena;

@Repository
public interface ContrasenaRepository extends JpaRepository<Contrasena,Long>{

}
