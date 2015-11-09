package br.com.unigranrio.matafome.aplicacao.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.matafome.dominio.acoes.CriarUsuario;
import br.com.unigranrio.matafome.dominio.acoes.EditarUsuario;
import br.com.unigranrio.matafome.dominio.acoes.ResultadoAcao;
import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroUsuario;
import br.com.unigranrio.matafome.infra.repositorios.UsuarioRepositorioImpl;

@RestController()
public class UsuarioController {
	private UsuarioRepositorio usuarioRepositorio;
	private CriarUsuario criarUsuario;
	private EditarUsuario editarUsuario;
	
	public UsuarioController(){
		this.usuarioRepositorio = new UsuarioRepositorioImpl();
		this.criarUsuario = new CriarUsuario(usuarioRepositorio, new ValidadorCadastroUsuario(usuarioRepositorio));
		this.editarUsuario = new EditarUsuario(usuarioRepositorio);
	}
	
	@RequestMapping("/usuario/obter-por-email")
	public Usuario obterPorEmail(@RequestParam(value = "email")String email){
		return usuarioRepositorio.obterPorEmail(email);
	}
	
	@RequestMapping(value = "/usuario/criar", method = RequestMethod.POST)
	public ResultadoAcao<Usuario> criarUsuario(@RequestBody Usuario usuario){
		return criarUsuario.executar(usuario);
	}
	
	@RequestMapping(value = "/usuario/editar", method = RequestMethod.POST)
	public ResultadoAcao<Usuario> editar(@RequestBody Usuario usuario){
		return editarUsuario.executar(usuario);
	}
}
