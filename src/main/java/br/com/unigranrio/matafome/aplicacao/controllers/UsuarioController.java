package br.com.unigranrio.matafome.aplicacao.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

@RestController()
public class UsuarioController {
	private UsuarioRepositorio usuarioRepositorio;
	
	public UsuarioController(){
		//this.usuarioRepositorio = new 
	}
	
	@RequestMapping("/usuario/obter-por-email")
	public Usuario obterPorEmail(@RequestParam(value = "email")String email){
		Usuario usuario = new Usuario();
		usuario.setEmail("thiagogmiranda.br@gmail.com");
		usuario.setNome("Thiago Miranda");
		usuario.setSenha("1234");		
		
		return usuario;
		//return usuarioRepositorio.obterPorEmail(email);
	}
}
