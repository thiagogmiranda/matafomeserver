package br.com.unigranrio.matafome.aplicacao;

import br.com.unigranrio.matafome.dominio.modelo.Barraca;
import br.com.unigranrio.matafome.infra.repositorios.BarracaRepositorioImpl;

public class Teste {

	public static void main(String[] args) {
		Barraca b = new Barraca();
		b.setIdDono(1);
		b.setLatitude(-22.789628);
		b.setLongitude(-43.306287);
		b.setNome("Podrão da Esquina");
		
		BarracaRepositorioImpl barracaRepositorio = new BarracaRepositorioImpl();
		barracaRepositorio.salvar(b);
	}

}
