package br.com.unigranrio.matafome.dominio.modelo;

public class DetalheNegocio {
	private Negocio negocio;
	private Avaliacao avaliacaoUsuario;

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public Avaliacao getAvaliacaoUsuario() {
		return avaliacaoUsuario;
	}

	public void setAvaliacaoUsuario(Avaliacao avaliacaoUsuario) {
		this.avaliacaoUsuario = avaliacaoUsuario;
	}
}
