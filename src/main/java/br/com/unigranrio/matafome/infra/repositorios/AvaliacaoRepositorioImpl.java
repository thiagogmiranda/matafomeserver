package br.com.unigranrio.matafome.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Avaliacao;
import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.modelo.Usuario;
import br.com.unigranrio.matafome.dominio.repositorios.AvaliacaoRepositorio;

public class AvaliacaoRepositorioImpl extends RepositorioAbstrato implements AvaliacaoRepositorio {

	@Override
	public List<Avaliacao> obterAvaliacoesPara(long idVendedor) {
		List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

		String query = "SELECT a.id as idAvaliacao, a.comentario, a.nota, a.idusuario, u.nome, u.email, u.tipo, a.idnegocio, n.Nome as nomeNegocio, n.descricao as descricaoNegocio, n.latitude, n.longitude, n.idusuario as iddono FROM avaliacao a inner join usuario u on u.id = a.idusuario inner join negocio n on n.id = a.idNegocio WHERE n.idusuario = ?";

		try {
			openConnection();

			prepareStatement(query, idVendedor);

			while (readResults()) {
				Avaliacao avaliacao = new Avaliacao(); 

				avaliacao.setId(resultSet.getLong("idavaliacao"));
				avaliacao.setComentario(resultSet.getString("comentario"));
				avaliacao.setNota(resultSet.getInt("nota"));
				
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("idusuario"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setEmail(resultSet.getString("email"));
				usuario.setTipo(resultSet.getString("tipo"));
				
				avaliacao.setUsuario(usuario);
				
				Negocio negocio = new Negocio();
				negocio.setId(resultSet.getLong("idnegocio"));
				negocio.setNome(resultSet.getString("nomenegocio"));
				negocio.setDescricao(resultSet.getString("descricaonegocio"));
				negocio.setLatitude(resultSet.getDouble("latitude"));
				negocio.setLongitude(resultSet.getDouble("longitude"));
				negocio.setIdDono(resultSet.getLong("iddono"));
				
				avaliacao.setNegocio(negocio);
				
				avaliacoes.add(avaliacao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return avaliacoes;
	}
	
	@Override
	public void salvar(Avaliacao avaliacao) {
		String query = "insert into avaliacao(nota, comentario, idUsuario, idNegocio) VALUES (?, ?, ?, ?)";

		try {
			openConnection();

			prepareStatement(query, avaliacao.getNota(), 
						avaliacao.getComentario(), avaliacao.getUsuario().getId(),
						avaliacao.getNegocio().getId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}		
	}

	@Override
	public boolean existeAvaliacaoDoUsuarioParaNegocio(long idUsuario, long idNegocio) {
		boolean existe = false;

		String query = "SELECT COUNT(1) as Total FROM Avaliacao WHERE idUsuario = ? AND idNegocio = ?";

		try {
			openConnection();

			prepareStatement(query, idUsuario, idNegocio);

			int count = 0;

			while (readResults()) {
				count = resultSet.getInt("Total");
				break;
			}

			existe = count > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return existe;
	}

	
	@Override
	public Avaliacao obterAvaliacaoDoUsuario(long idUsuario, long idNegocio) {
		Avaliacao avaliacao = null;
		
		String query = "SELECT * FROM avaliacao WHERE idusuario = ? AND idNegocio = ?";

		try {
			openConnection();

			prepareStatement(query, idUsuario, idNegocio);

			while (readResults()) {
				avaliacao = new Avaliacao();
				avaliacao.setId(resultSet.getLong("id"));
				avaliacao.setComentario(resultSet.getString("comentario"));
				avaliacao.setNota(resultSet.getInt("nota"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		
		return avaliacao;
	}

}
