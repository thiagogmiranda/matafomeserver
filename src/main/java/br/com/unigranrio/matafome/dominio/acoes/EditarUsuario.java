package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class EditarUsuario {
	private UsuarioRepositorio usuarioRepositorio;
	
	public EditarUsuario(UsuarioRepositorio usuarioRepositorio){
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	public ResultadoAcao executar(Usuario usuario){
		ResultadoAcao resultado = new ResultadoAcao();
		//resultado.adicionarMensagens(validadorCadastro.validar(usuario));
		
		if(resultado.estaValido()){
			Usuario salvo = usuarioRepositorio.obterPorEmail(usuario.getEmail());
			salvo.setTipo(usuario.getTipo());			
			
			usuarioRepositorio.salvar(salvo);
			
			resultado.setData(salvo);
		}
		
		return resultado;
	}
}
