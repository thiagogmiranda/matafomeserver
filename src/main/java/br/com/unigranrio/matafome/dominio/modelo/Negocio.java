package br.com.unigranrio.matafome.dominio.modelo;

public class Negocio {
	private long id;
	private long idDono;
	private String nome;
	private String descricao;
	private double latitude;
	private double longitude;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdDono() {
		return idDono;
	}
	public void setIdDono(long idDono) {
		this.idDono = idDono;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
