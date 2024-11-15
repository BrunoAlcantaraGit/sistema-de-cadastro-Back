package com.CRUD.sitema_de_cadastro.controller;

import com.CRUD.sitema_de_cadastro.component.FormatarCPF;
import com.CRUD.sitema_de_cadastro.entity.Cliente;
import com.CRUD.sitema_de_cadastro.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@Data
@RequestMapping("/cliente")
public class ClienteController implements WebMvcConfigurer {
    FormatarCPF formatarCPF;
    @Autowired
    ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) throws Exception {
        try {
            return new ResponseEntity<Cliente>(clienteService.salvarCliente(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente, @PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(clienteService.editarCliente(cliente, id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Cliente>> buscarCliente(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(clienteService.buscarCliente(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarCliente() throws Exception {
        try {
        return new ResponseEntity<>(clienteService.listarCliente(),HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity DeletarCliente(@PathVariable Long id)throws Exception{
        try {
            clienteService.deletarId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("buscar-por-documento/{doc}")
    public ResponseEntity<Optional<Cliente>> buscarClientePorDocumento(@PathVariable String doc)throws Exception{
        try {
         return   new ResponseEntity<>(clienteService.buscarClientePorDocumento(doc),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
