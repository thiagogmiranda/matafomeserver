package br.com.unigranrio.matafome.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

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
		
		String query = "SELECT * FROM barraca";
		
		try {
			openConnection();
			
			prepareStatement(query);
			
			while (readResults()) {
				Negocio negocio = new Negocio();
				negocio.setNome(resultSet.getString("nome"));
				negocio.setId(resultSet.getLong("id"));
				negocio.setDescricao(resultSet.getString("descricao"));
				negocio.setIdDono(resultSet.getLong("iddono"));
				negocio.setLatitude(resultSet.getFloat("latitude"));
				negocio.setLongitude(resultSet.getFloat("longitude"));
				
				LatLng localNegocio = new LatLng(negocio.getLatitude(), negocio.getLongitude());
				
				double d = distancia(coordenadas, localNegocio);
				
				if(d <= raio){
					barracas.add(negocio);
				}
			}
		} catch (Exception e) {
			
		} finally {
			closeConnection();
		}
		
		return barracas;
	}
	
	private double distancia(LatLng pontoA, LatLng pontoB){		
		double R = 6371;
		double dLat = Math.toRadians(pontoB.lat - pontoA.lat);
        double dLon = Math.toRadians(pontoB.lng - pontoA.lng);
        double latA = Math.toRadians(pontoA.lat);
        double latB = Math.toRadians(pontoB.lat);
 
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(latA) * Math.cos(latB);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
	}

}
