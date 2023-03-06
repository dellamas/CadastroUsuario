package com.luisfabricio.cadastrodeusuario.service;

import com.luisfabricio.cadastrodeusuario.Dto.AtualizarUsuarioRequest;
import com.luisfabricio.cadastrodeusuario.Dto.IncluirUsuarioRequest;
import com.luisfabricio.cadastrodeusuario.exceptions.UsuarioDesconhecidoException;
import com.luisfabricio.cadastrodeusuario.model.UsuarioModel;
import com.luisfabricio.cadastrodeusuario.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> listar() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel getUsuarioModel(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioDesconhecidoException("Cliente não encontrado " + id));
    }

    public UsuarioModel incluir(IncluirUsuarioRequest usuarioRequest) {
        var data = Instant.now();

        var usuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRequest, usuario);
        usuario.setDataInclusao(data);
        usuario.setUltimaModificacao(data);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public UsuarioModel atualizar(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) {
        var usuario = usuarioRepository.findById(atualizarUsuarioRequest.getId()).get();

        BeanUtils.copyProperties(atualizarUsuarioRequest, usuario);
        usuario.setUltimaModificacao(Instant.now());
        usuarioRepository.save(usuario);
        return usuario;
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
