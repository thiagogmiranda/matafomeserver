package br.com.unigranrio.matafome.dominio.validadores;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.acoes.Mensagem;
import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;

public class ValidadorEdicaoNegocio {
private NegocioRepositorio negocioRepositorio;
	
	public ValidadorEdicaoNegocio(NegocioRepositorio negocioRepositorio){
		this.negocioRepositorio = negocioRepositorio;
	}
	
	public List<Mensagem> validar(Negocio negocio){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		
		double lat = negocio.getLatitude();
		double lng = negocio.getLongitude();
		
		if(lat == 0 || lng == 0){
			mensagens.add(new Mensagem("Selecione uma localização"));
		}
		
		if(lat != 0 && lng != 0){
			boolean existe = negocioRepositorio.existeNegocioNoLocal(lat, lng);
			
			if(existe){
				mensagens.add(new Mensagem("Já existe um negócio nesta localização"));
			}
		}
		
		return mensagens;
	}
}
