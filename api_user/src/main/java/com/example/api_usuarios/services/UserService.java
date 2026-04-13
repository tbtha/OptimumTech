package com.example.api_usuarios.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.UserCreate;
import com.example.api_usuarios.models.requests.UserUpdate;
import com.example.api_usuarios.repositories.RoleRepository;
import com.example.api_usuarios.repositories.UserRepository;
import com.example.api_usuarios.models.entities.Role;


// import jakarta.validation.Valid;

@Service
public class UserService {
    
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> obtenerTodos() {
        return userRepository.findAll();    
    }

    public User obtenerPorId(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User registrar(UserCreate usuario){
        try {
            User nuevoUsuario = new User();
            //cammpos de negocio
            nuevoUsuario.setFechaCreacion(new Date());
            nuevoUsuario.setActivo(true);
            //campos que vienen del cliente
            nuevoUsuario.setNombre(usuario.getNombre());
            nuevoUsuario.setEmail(usuario.getEmail());
            nuevoUsuario.setPassword(hashearPassword(usuario.getPassword()));
            nuevoUsuario.setTelefono(usuario.getTelefono());
            //nuevoUsuario.setRol(usuario.getRol());

            // Asignar roles
            if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
                for (String nombreRol : usuario.getRoles()) {
                    Role role = roleRepository.findByName(nombreRol);
                    if (role == null) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol '" + nombreRol + "' Rol no existente, coloque uno rol válido.");
                    }
                    nuevoUsuario.getRoles().add(role);
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe asignar al menos un rol al usuario.");
            }

            return userRepository.save(nuevoUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    public String hashearPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password); 
    }

    public boolean comprobarPassword(String password, String hash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, hash); 
    }

    public User actualizar(UserUpdate body) {
        User usuario = userRepository.findById(body.getId()).orElse(null);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        if(body.getNombre() != null) {
            usuario.setNombre(body.getNombre());
        }
        
        if(body.getTelefono() != null) {
            usuario.setTelefono(body.getTelefono());
        }

        if(body.getPassword() != null) {
            usuario.setPassword(hashearPassword(body.getPassword()));
        }
        
        if(body.getActivo() != null) {
            usuario.setActivo(body.getActivo());
        }

        if(body.getRoles() != null && !body.getRoles().isEmpty()) {
            usuario.getRoles().clear();
            for (String nombreRol : body.getRoles()) {
                Role role = roleRepository.findByName(nombreRol);
                if (role == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol '" + nombreRol + "' no existente, coloque uno válido.");
                }
                usuario.getRoles().add(role);
            }
        }

        return userRepository.save(usuario);
    }

    public void eliminar(int id){
        User usuario = userRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        userRepository.delete(usuario);
    }

    public long obtenerTotalUsuarios() {
        return userRepository.count();
    }

}
