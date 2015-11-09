package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;
import br.com.unigranrio.matafome.dominio.validadores.ValidadorCadastroNegocio;

public class CadastrarNegocio {

	private NegocioRepositorio negocioRepositorio;
	private ValidadorCadastroNegocio validadorCadastro;
	
	public CadastrarNegocio(NegocioRepositorio barracaRepositorio, ValidadorCadastroNegocio validadorCadastro){
		this.negocioRepositorio = barracaRepositorio;
		this.validadorCadastro = validadorCadastro;
	}
	
	public ResultadoAcao<Negocio> executar(Negocio negocio) {
		ResultadoAcao<Negocio> resultado = new ResultadoAcao<Negocio>();
		resultado.adicionarMensagens(validadorCadastro.validar(negocio));
		
		if(resultado.estaValido()){
			negocioRepositorio.salvar(negocio);
			
			Negocio salvo = negocioRepositorio.obterPorLatLng(negocio.getLatitude(), negocio.getLongitude());
			
			resultado.setData(salvo);
		}
		
		return resultado;
	}

}
