package com.luisfabricio.cadastrodeusuario.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisfabricio.cadastrodeusuario.Dto.AtualizarUsuarioRequest;
import com.luisfabricio.cadastrodeusuario.Dto.IncluirUsuarioRequest;
import com.luisfabricio.cadastrodeusuario.Dto.IncluirUsuarioResponse;
import com.luisfabricio.cadastrodeusuario.model.UsuarioModel;
import com.luisfabricio.cadastrodeusuario.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioModel>> listar() {
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> ler(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usuarioService.getUsuarioModel(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<IncluirUsuarioResponse> incluir(@RequestParam String usuarioData, @RequestParam("file") final MultipartFile file) throws IOException {

        final var incluirUsuarioRequest = mapper.readValue(usuarioData, IncluirUsuarioRequest.class);
        incluirUsuarioRequest.setFoto(file.getInputStream().readAllBytes());

        var usuario = usuarioService.incluir(incluirUsuarioRequest);

        var usuarioResponse = new IncluirUsuarioResponse();
        BeanUtils.copyProperties(usuario, usuarioResponse);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizar(@PathVariable Long id, @RequestBody AtualizarUsuarioRequest atualizarUsuarioRequest, @RequestParam(value = "file", required = false) final MultipartFile file) throws IOException {
        if (file != null) {
            atualizarUsuarioRequest.setFoto(file.getBytes());
        }

        var usuario = usuarioService.atualizar(id, atualizarUsuarioRequest);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


