package br.com.unigranrio.matafome.dominio.acoes;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAcao {
	private List<Mensagem> mensagens;
	
	public ResultadoAcao(){
		mensagens = new ArrayList<Mensagem>();
	}
	
	public boolean deuCerto(){
		return mensagens.size() == 0;
	}

	public void adicionarMensagens(List<Mensagem> mensagens) {
		this.mensagens.addAll(mensagens);
	}
	
	public List<Mensagem> getMensagens(){
		return this.mensagens;
	}
	
	public void setMensagens(List<Mensagem> mensagens){
		this.mensagens = mensagens;
	}
}
