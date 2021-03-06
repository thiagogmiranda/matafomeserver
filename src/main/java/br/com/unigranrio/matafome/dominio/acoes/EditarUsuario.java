package br.com.unigranrio.matafome.dominio.acoes;

import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.UsuarioRepositorio;

public class EditarUsuario {
	private UsuarioRepositorio usuarioRepositorio;
	
	public EditarUsuario(UsuarioRepositorio usuarioRepositorio){
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	public ResultadoAcao<Usuario> executar(Usuario usuario){
		ResultadoAcao<Usuario> resultado = new ResultadoAcao<Usuario>();
		//resultado.adicionarMensagens(validadorCadastro.validar(usuario));
		
		if(resultado.estaValido()){
			Usuario salvo = usuarioRepositorio.obterPorEmail(usuario.getEmail());
			salvo.setTipo(usuario.getTipo());			
			
			usuarioRepositorio.atualizar(salvo);
			
			resultado.setData(salvo);
		}
		
		return resultado;
	}
}
