package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Contrasena;
import com.example.demo.repository.ContrasenaRepository;

@Service
public class contrasenaService {
	
	@Autowired
	private ContrasenaRepository repository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public String asignar_ranking(String number){
		String sql = "select asignar_ranking(?) from dual";
		return jdbcTemplate.queryForObject(sql, new Object[] {number}, String.class);
	}
		

	public Contrasena saveContrasena(Contrasena contrasena) {
	    return repository.save(contrasena);
	}


	public List<Contrasena> obtenerTodasLasContraseñas(){
		List<Contrasena> contrasenas = repository.findAll();
		return contrasenas ;
	}
	

	public Contrasena obtenerContrasenaPorId(Long id) {
        Optional<Contrasena> contrasena = repository.findById(id);
        if ( contrasena.isPresent()) {
        	return contrasena.get();
        }else {
        	throw new RuntimeException("Contraseña no encontrada con el id: " + id);
        }
	}
	

	public Contrasena actualizarContrasena(Long id,Contrasena detallesContrasena) {
	    Optional<Contrasena> contrasenaExistente = repository.findById(id);

	    if (contrasenaExistente.isPresent()) {
	        Contrasena contrasenaActualizada = contrasenaExistente.get();
	        
	        // Asigna los nuevos valores con getters y setters
	        contrasenaActualizada.setPassword(detallesContrasena.getPassword());
	        contrasenaActualizada.setUsername(detallesContrasena.getUsername());

	        return repository.save(contrasenaActualizada);  // Guarda los cambios en la base de datos
	    } else {
	    	throw new RuntimeException("Contraseña no encontrada con el ID: " + id);  // Si no se encuentra el ID, retorna 404
	    }
	}


    public Contrasena eliminarContrasena(Long id) {
        Optional<Contrasena> contrasena = repository.findById(id);
        if (contrasena.isPresent()) {
        	Contrasena contra = contrasena.get();
        	repository.delete(contra);
        	return contra;
        } else {
        	throw new RuntimeException("Contraseña no encontrada con el ID: " + id);
        }
    }
    
    
    public Contrasena check_password(Long id, Contrasena date){
    	String contra = date.getPassword();
    	String sql = "select check_password(?,?) from dual";
    	return jdbcTemplate.queryForObject(sql, new Object[] {id,contra},Contrasena.class);
    }
    
}
