package br.com.unigranrio.matafome.infra.repositorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;

public class NegocioRepositorioImpl extends RepositorioAbstrato implements NegocioRepositorio {

	@Override
	public void salvar(Negocio negocio) {
		String query = "INSERT INTO Negocio(iddono, nome, descricao, latitude, longitude) VALUES (?, ?, ?, ?, ?)";

		try {
			openConnection();

			prepareStatement(query, negocio.getIdDono(), negocio.getNome(), negocio.getDescricao(),
					negocio.getLatitude(), negocio.getLongitude());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	@Override
	public Negocio obterPorId(long id) {
		Negocio negocio = null;

		String query = "SELECT * FROM Negocio WHERE id = ?";

		try {
			openConnection();

			prepareStatement(query, id);

			while (readResults()) {
				negocio = resultSetParaObjeto();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return negocio;
	}

	@Override
	public List<Negocio> obterTodosDoUsuario(String email) {
		List<Negocio> barracas = new ArrayList<Negocio>();

		String query = "SELECT * FROM Negocio WHERE iddono in (select id from Usuario where email = ?)";

		try {
			openConnection();

			prepareStatement(query, email);

			while (readResults()) {
				barracas.add(resultSetParaObjeto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return barracas;
	}

	@Override
	public List<Negocio> obterTodosDentroDoRaio(double raio, double lat, double lng) {
		List<Negocio> negocios = new ArrayList<Negocio>();

		String query = "SELECT * FROM Negocio WHERE ? >= public.geodistance(?, ?, latitude, longitude)";

		try {
			openConnection();

			prepareStatement(query, (double) (raio / 1000.00), lat, lng);

			while (readResults()) {
				negocios.add(resultSetParaObjeto());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return negocios;
	}

	private Negocio resultSetParaObjeto() throws SQLException {
		Negocio negocio = new Negocio();

		negocio.setNome(resultSet.getString("nome"));
		negocio.setId(resultSet.getLong("id"));
		negocio.setDescricao(resultSet.getString("descricao"));
		negocio.setIdDono(resultSet.getLong("iddono"));
		negocio.setLatitude(resultSet.getFloat("latitude"));
		negocio.setLongitude(resultSet.getFloat("longitude"));

		return negocio;
	}

	@Override
	public Negocio obterPorLatLng(double lat, double lng) {
		Negocio negocio = null;

		String query = "SELECT * FROM Negocio WHERE latitude = ? AND longitude = ?";

		try {
			openConnection();

			prepareStatement(query, lat, lng);

			while (readResults()) {
				negocio = resultSetParaObjeto();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return negocio;
	}

	@Override
	public boolean existeNegocioNoLocal(double lat, double lng) {
		boolean existe = false;

		String query = "SELECT COUNT(1) as Total FROM Negocio WHERE latitude = ? AND longitude = ?";

		try {
			openConnection();

			prepareStatement(query, lat, lng);

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
	public Negocio obterNegocioDoDono(long idDono) {
		Negocio negocio = null;

		String query = "SELECT * FROM Negocio WHERE iddono = ?";

		try {
			openConnection();

			prepareStatement(query, idDono);

			while (readResults()) {
				negocio = resultSetParaObjeto();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return negocio;
	}
}