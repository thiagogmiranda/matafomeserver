package br.com.unigranrio.matafome.dominio.acoes;

public class Erro {
	private String campo;
	private String mensagem;
	
	public Erro(String campo, String mensagem){
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public Erro(String mensagem){
		this("", mensagem);
	}
	
	public String getCampo(){
		return this.campo;
	}
	
	public String getMensagem(){
		return this.mensagem;
	}
}
