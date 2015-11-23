package br.com.unigranrio.matafome.dominio.validadores;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.acoes.Mensagem;
import br.com.unigranrio.matafome.dominio.modelo.Avaliacao;
import br.com.unigranrio.matafome.dominio.repositorios.AvaliacaoRepositorio;

public class ValidadorCadastroAvaliacao {
	private AvaliacaoRepositorio repositorio;
	
	public ValidadorCadastroAvaliacao(AvaliacaoRepositorio repositorio){
		this.repositorio = repositorio;
	}
	
	public List<Mensagem> validar(Avaliacao avaliacao){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		
		long idUsuario = avaliacao.getUsuario().getId();
		long idNegocio = avaliacao.getNegocio().getId();
		
		if(repositorio.existeAvaliacaoDoUsuarioParaNegocio(idUsuario, idNegocio)){
			mensagens.add(new Mensagem("Você já avaliou este negócio"));
		} else {
			if(avaliacao.getComentario() == null || "".equals(avaliacao.getComentario())){
				mensagens.add(new Mensagem("Escreva um comentário"));
			}
			
			if(avaliacao.getNota() == 0){
				mensagens.add(new Mensagem("Selecione uma nota"));
			}	
		}
		
		return mensagens;
	}
}
