package br.com.unigranrio.matafome.dominio.acoes;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAcao {
	private List<Mensagem> mensagens;
	
	public ResultadoAcao(){
		mensagens = new ArrayList<Mensagem>();
	}
	
	public boolean isSucesso(){
		return mensagens.stream().count() == 0;
	}

	public void adicionarMensagens(List<Mensagem> mensagens) {
		this.mensagens.addAll(mensagens);
	}
}
