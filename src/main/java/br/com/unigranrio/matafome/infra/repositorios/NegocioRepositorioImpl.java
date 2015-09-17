package br.com.unigranrio.matafome.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.internal.DistanceAdapter;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;

import br.com.unigranrio.matafome.dominio.modelo.Negocio;
import br.com.unigranrio.matafome.dominio.repositorios.NegocioRepositorio;

public class NegocioRepositorioImpl extends RepositorioAbstrato implements NegocioRepositorio {

	@Override
	public void salvar(Negocio negocio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Negocio obterPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Negocio> obterTodosDoUsuario(long idDono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Negocio> obterTodosDentroDoRaio(double raio, LatLng coordenadas) {
		List<Negocio> barracas = new ArrayList<Negocio>();
		
		String query = "SELECT * FROM barraca WHERE ? = public.geodistance(?, ?, latitude, longitude)";
		
		try {
			openConnection();
			
			prepareStatement(query, (raio/1000), coordenadas.lat, coordenadas.lng);
			
			while (readResults()) {
				Negocio negocio = new Negocio();
				negocio.setNome(resultSet.getString("nome"));
				negocio.setId(resultSet.getLong("id"));
				negocio.setDescricao(resultSet.getString("descricao"));
				negocio.setIdDono(resultSet.getLong("iddono"));
				negocio.setLatitude(resultSet.getFloat("latitude"));
				negocio.setLongitude(resultSet.getFloat("longitude"));
				
				barracas.add(negocio);
			}
		} catch (Exception e) {
			
		} finally {
			closeConnection();
		}
		
		return barracas;
	}
}
