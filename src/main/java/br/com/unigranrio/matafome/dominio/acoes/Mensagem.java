package br.com.unigranrio.matafome.dominio.acoes;

public class Mensagem {
	private String texto;

	public Mensagem(String texto){
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
