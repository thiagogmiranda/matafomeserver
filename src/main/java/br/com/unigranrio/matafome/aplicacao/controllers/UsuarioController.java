package br.com.unigranrio.matafome.aplicacao.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;
import br.com.unigranrio.matafome.infra.repositorios.UsuarioRepositorioImpl;

@RestController()
public class UsuarioController {
	private UsuarioRepositorio usuarioRepositorio;
	
	public UsuarioController(){
		this.usuarioRepositorio = new UsuarioRepositorioImpl(); 
	}
	
	@RequestMapping("/usuario/obter-por-email")
	public Usuario obterPorEmail(@RequestParam(value = "email")String email){
		return usuarioRepositorio.obterPorEmail(email);
	}
}
