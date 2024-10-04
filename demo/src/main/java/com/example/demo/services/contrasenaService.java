package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.model.Contrasena;
import com.example.demo.repository.ContrasenaRepository;

@Service
public class contrasenaService {
	
	@Autowired
	private ContrasenaRepository repository;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	

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
    
    public String asignar_ranking(String number){
		String sql = "select asignar_ranking(?) from dual";
		return jdbcTemplate.queryForObject(sql, new Object[] {number}, String.class);
	}
    
    
    
    //This function is number 1 of Taller
    public int Factorial(int num){
    	try{
    		String sql = "select Factorial(?) from dual";
    		return jdbcTemplate.queryForObject(sql, int.class,num);
    	} catch (DataAccessException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            return -1;
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            return -1;
        }
    }
    
  //This function is number 2 of Taller
    public int calcularMCD(int num1, int num2){
    	try {
    	String sql = "select calcularMCD(?,?) from dual";
    	return jdbcTemplate.queryForObject(sql,int.class,num1,num2);
    	} catch(DataAccessException e){
    		System.out.println("Error al ejecutar la consulta: " + e.getMessage());
    		return -1;
    	} catch(Exception e){
    		 System.out.println("Ocurrió un error inesperado: " + e.getMessage());
    		 return -1;
    	}
    }
    
    
  //This function is number 3 of Taller
    public String is_prime(int number){
    	try {
    	String sql = "select Primos(?) from dual";
    	return jdbcTemplate.queryForObject(sql,String.class,number);
    	} catch(DataAccessException e){
    		System.out.println("Error al ejecutar la consulta: " + e.getMessage());
   		 	return  "Error en la consulta SQL";
    	} catch (Exception e) {
    		System.out.println("Ocurrió un error inesperado: " + e.getMessage());
   	     	return "Error general";
    	}
    }
    
  //This function is number 4 of Taller
    public String Fibonacci(int number){
    	 try{
    		String sql = "select serieFibo(?) from dual"; 
    		return jdbcTemplate.queryForObject(sql, String.class,number);
    	 } catch (DataAccessException e){
    		 System.out.println("Error al ejecutar la consulta: " + e.getMessage());
    		 return  "Error en la consulta SQL";
    	 } catch (Exception e) {
    		 System.out.println("Ocurrió un error inesperado: " + e.getMessage());
    	     return "Error general";
    	 }
    }
    
    
    public Contrasena check_password(Long id, Contrasena date){
    	String contra = date.getPassword();
    	String sql = "select check_password(?,?) from dual";
    	return jdbcTemplate.queryForObject(sql, new Object[] {id,contra},Contrasena.class);
    }
    
    //This function is number 5 of Taller
    public void actualizarPrecioProducto(String codProducto, double nuevoPrecio) {
        try {
            String sql = "{call actualizar_precio_producto(?, ?)}";
            jdbcTemplate.update(sql, codProducto, nuevoPrecio);
            System.out.println("Precio actualizado correctamente.");
        } catch (DataAccessException e) {
            System.out.println("Error al ejecutar el procedimiento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    
}
