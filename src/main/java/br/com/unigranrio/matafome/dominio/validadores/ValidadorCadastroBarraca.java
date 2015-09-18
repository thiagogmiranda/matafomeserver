package br.com.unigranrio.matafome.dominio.validadores;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.acoes.Mensagem;
import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.dominio.repositorios.BarracaRepositorio;

public class ValidadorCadastroBarraca {
	private BarracaRepositorio barracaRepositorio;
	
	public ValidadorCadastroBarraca(BarracaRepositorio barracaRepositorio){
		this.barracaRepositorio = barracaRepositorio;
	}
	
	public List<Mensagem> validar(Barraca barraca){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		
		if("".equals(barraca.getNome())){
			mensagens.add(new Mensagem("É obrigatório informar um nome"));
		}
		
		if(barraca.getIdDono() == 0){
			mensagens.add(new Mensagem("Não é possível cadastrar uma barraca sem dono"));
		}
		
		double lat = barraca.getLatitude();
		double lng = barraca.getLongitude();
		
		if(lat == 0 || lng == 0){
			mensagens.add(new Mensagem("Selecione o local da barraca"));
		}
		
		if(lat != 0 && lng != 0){
			Barraca busca = barracaRepositorio.obterPorLatLng(lat, lng);
			
			if(busca != null){
				mensagens.add(new Mensagem("Já existe uma barraca cadastrada neste local"));
			}
		}
		
		return mensagens;
	}
}
