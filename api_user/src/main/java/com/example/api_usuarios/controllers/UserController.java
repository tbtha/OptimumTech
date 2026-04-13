package com.example.api_usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_usuarios.models.entities.User;
import com.example.api_usuarios.models.requests.UserCreate;
import com.example.api_usuarios.models.requests.UserUpdate;
import com.example.api_usuarios.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Operation(
        summary = "Obtiene lista de usuarios", 
        description = "Obtiene una lista completa con datos de todos los usuarios registrados."
    )
    public List<User> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtiene un usuario por ID",
        description = "Obtiene los detalles de un usuario específico por su ID."
    )
    public User obtenerUno(@PathVariable int id) {
        return userService.obtenerPorId(id);
    }

    @PostMapping("/")
    @Operation(
        summary = "Registra un nuevo usuario", 
        description = "Permite registrar un nuevo usuario con los datos proporcionados."
    )
    public User registrar(@Valid @RequestBody UserCreate body) {
        return userService.registrar(body);
    }

    @PutMapping()
    @Operation(
        summary = "Actualiza los datos de un usuario",
        description = "Actualiza la información de un usuario existente."
    )
    public User actualizar(@Valid @RequestBody UserUpdate body) {
        return userService.actualizar(body);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Elimina un usuario por ID", 
        description = "Elimina un usuario de la base de datos mediante su ID."
    )
    public String eliminar(@PathVariable int id) {
        userService.eliminar(id);
        return "Usuario eliminado";
    }

    @GetMapping("/total")
    @Operation(
        summary = "Obtiene el total de usuarios",
        description = "Devuelve la cantidad total de usuarios registrados"
    )
    public long obtenerTotalUsuarios() {
        return userService.obtenerTotalUsuarios();
    }
}
