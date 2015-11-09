package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroNegocio;

public class CadastrarNegocio {

	private NegocioRepositorio barracaRepositorio;
	private ValidadorCadastroNegocio validadorCadastro;
	
	public CadastrarNegocio(NegocioRepositorio barracaRepositorio, ValidadorCadastroNegocio validadorCadastro){
		this.barracaRepositorio = barracaRepositorio;
		this.validadorCadastro = validadorCadastro;
	}
	
	public ResultadoAcao<Negocio> executar(Negocio barraca) {
		ResultadoAcao<Negocio> resultado = new ResultadoAcao<Negocio>();
		resultado.adicionarMensagens(validadorCadastro.validar(barraca));
		
		if(resultado.estaValido()){
			barracaRepositorio.salvar(barraca);
		}
		
		return resultado;
	}

}
