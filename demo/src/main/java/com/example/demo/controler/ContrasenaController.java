package com.example.demo.controler;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Contrasena;

import com.example.demo.services.contrasenaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class ContrasenaController {
	
	@Autowired
	private contrasenaService service;
	
	@PostMapping("/contrasen")
	public ResponseEntity<Contrasena> saveContrasena(@RequestBody Contrasena contrasena) {
	    Contrasena contra = service.saveContrasena(contrasena); 
	    return new ResponseEntity<>(contra, HttpStatus.CREATED);
	}
	
	@GetMapping("/contrasena/all")
	public ResponseEntity<List<Contrasena>> obtenerTodasLasContraseñas(){
		List<Contrasena> contrasenas = service.obtenerTodasLasContraseñas();
		return new ResponseEntity<>(contrasenas,HttpStatus.OK);
	}
	
	@PostMapping("/contrasena/{id}")
	public ResponseEntity<Contrasena> obtenerContrasena(@PathVariable Long id){
		Contrasena contra = service.obtenerContrasenaPorId(id);
		if (contra != null) {
			return new ResponseEntity<>(contra,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	};
	
	@PutMapping("/contrasena/{id}")
	public ResponseEntity<Contrasena> actualizarContrasena(@PathVariable Long id, @RequestBody Contrasena newDate){
		Contrasena contra = service.actualizarContrasena(id,newDate);
		if (contra !=null) {
			return new ResponseEntity<>(contra,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/contrasena/{id}")
	public ResponseEntity<Contrasena> deletContrasena(@PathVariable Long id){
		Contrasena contra = service.eliminarContrasena(id);
		if ( contra != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Check Password
	@PostMapping("/contrasena/check/{id}")
	public ResponseEntity<Contrasena> checkPassword(@PathVariable Long id, @RequestBody Contrasena date){
		Contrasena contra = service.check_password(id,date);
		return new ResponseEntity<>(contra,HttpStatus.OK);
	}
	
    //Ejemplo function conection
    @PostMapping("/ejemplo/{entity}")
    public ResponseEntity<String> ejemploCall(@PathVariable String entity) {
    	String ejemplo = service.asignar_ranking(entity);
    	System.out.print(ejemplo);
        return new ResponseEntity<>(ejemplo,HttpStatus.OK);
    }
    
    
    //Factorial
    @GetMapping("/Factorial/{param}")
    public ResponseEntity<Integer> Factorial(@PathVariable int param) {
        int result = service.Factorial(param);
        if (result == -1) {
        	return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
    
    //calcularMCD
    @GetMapping("/MCD")
    public ResponseEntity<Integer> calcularMCD(@RequestParam int param1, @RequestParam int param2) {
        int result = service.calcularMCD(param1, param2);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    //Is prime
    @GetMapping("/Primo/{param}")
    public ResponseEntity<String> Primo(@PathVariable int param) {
        String result = service.is_prime(param);
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
    
    //serieFibo
    @GetMapping("/Fibo/{param}")
    public ResponseEntity<String> Fibonacci(@PathVariable int param) {
        String result = service.Fibonacci(param);
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }
    
    //Actualizar precio
    @PostMapping("/productos/actualizarPrecio")
    public ResponseEntity<String> actualizarPrecio(@RequestParam String codProducto, @RequestParam double nuevoPrecio) {
        try {
            service.actualizarPrecioProducto(codProducto, nuevoPrecio);
            return new ResponseEntity<>("Precio actualizado correctamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el precio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
