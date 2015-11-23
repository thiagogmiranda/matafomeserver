package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Avaliacao;
import br.com.unigranrio.matafome.dominio.repositorios.AvaliacaoRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroAvaliacao;

public class CadastrarAvaliacao {
	private AvaliacaoRepositorio repositorio;
	private ValidadorCadastroAvaliacao validador;
	
	public CadastrarAvaliacao(AvaliacaoRepositorio repositorio, ValidadorCadastroAvaliacao validador){
		this.repositorio = repositorio;
		this.validador = validador;
	}
	
	public ResultadoAcao<Avaliacao> executar(Avaliacao avaliacao){
		ResultadoAcao<Avaliacao> resultado = new ResultadoAcao<Avaliacao>();
		resultado.adicionarMensagens(validador.validar(avaliacao));
		
		if(resultado.estaValido()){
			repositorio.salvar(avaliacao);
		}
		
		return resultado;
	}
}
