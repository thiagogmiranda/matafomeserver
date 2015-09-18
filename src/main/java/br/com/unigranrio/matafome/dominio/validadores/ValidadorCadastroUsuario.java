package br.com.unigranrio.matafome.dominio.validadores;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.acoes.Mensagem;
import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class ValidadorCadastroUsuario {
	
	private UsuarioRepositorio usuarioRepositorio;
	
	public ValidadorCadastroUsuario(UsuarioRepositorio usuarioRepositorio){
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	public List<Mensagem> validar(Usuario usuario){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		
		if("".equals(usuario.getNome())){
			mensagens.add(new Mensagem("É obrigatório informar um nome"));
		}
		
		String email = usuario.getEmail();
		
		if("".equals(email)){
			mensagens.add(new Mensagem("É obrigatório informar um email"));
		} else {
			Usuario busca = usuarioRepositorio.obterPorEmail(email);
			
			if(busca != null){
				mensagens.add(new Mensagem("Já existe um usuário cadastrado com o email"));
			}
		}
		
		if("".equals(usuario.getSenha())){
			mensagens.add(new Mensagem("É obrigatório informar uma senha"));
		}
		
		return mensagens;
	}
}
