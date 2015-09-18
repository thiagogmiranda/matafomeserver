package br.com.unigranrio.matafome.dominio.validadores;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.acoes.Mensagem;
import br.com.unigranrio.matafome.dominio.modelo.Usuario;

public class ValidadorCadastroUsuario {
	public List<Mensagem> validar(Usuario usuario){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		
		if("".equals(usuario.getNome())){
			mensagens.add(new Mensagem("É obrigatório informar um nome"));
		}
		
		if("".equals(usuario.getEmail())){
			mensagens.add(new Mensagem("É obrigatório informar um email"));
		}
		
		if("".equals(usuario.getSenha())){
			mensagens.add(new Mensagem("É obrigatório informar uma senha"));
		}
		
		return mensagens;
	}
}
