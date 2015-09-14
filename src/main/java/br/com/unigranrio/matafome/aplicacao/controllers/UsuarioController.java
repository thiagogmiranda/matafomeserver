package br.com.unigranrio.matafome.aplicacao.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.CriarUsuario;
import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;
import br.com.unigranrio.matafome.infra.repositorios.UsuarioRepositorioImpl;

@RestController()
public class UsuarioController {
	private UsuarioRepositorio usuarioRepositorio;
	private CriarUsuario criarUsuario;
	
	public UsuarioController(){
		this.usuarioRepositorio = new UsuarioRepositorioImpl();
		this.criarUsuario = new CriarUsuario(usuarioRepositorio);
	}
	
	@RequestMapping("/usuario/obter-por-email")
	public Usuario obterPorEmail(@RequestParam(value = "email")String email){
		return usuarioRepositorio.obterPorEmail(email);
	}
	
	@RequestMapping(value = "/usuario/criar", method = RequestMethod.POST, headers = "Access-Control-Allow-Origin: *")
	public void criarUsuario(@RequestBody Usuario usuario){
		criarUsuario.Executar(usuario);
	}
}
