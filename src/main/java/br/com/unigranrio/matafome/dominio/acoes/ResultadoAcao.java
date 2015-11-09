package br.com.unigranrio.matafome.dominio.acoes;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAcao<T> {
	private List<Mensagem> mensagens;
	private T data;

	public ResultadoAcao() {
		mensagens = new ArrayList<Mensagem>();
	}

	public boolean estaValido() {
		return mensagens.size() == 0;
	}

	public void adicionarMensagens(List<Mensagem> mensagens) {
		this.mensagens.addAll(mensagens);
	}

	public List<Mensagem> getMensagens() {
		return this.mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
