package br.com.unigranrio.matafome.infra.repositorios;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class RepositorioAbstrato {
	protected Connection connection;

	protected Statement statement;
	private PreparedStatement preparedStatement;

	protected ResultSet resultSet;

	protected void openConnection() throws URISyntaxException {		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String DB_USER = dbUri.getUserInfo().split(":")[0];
	    String DB_PASS = dbUri.getUserInfo().split(":")[1];
	    String DB_URL = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath(); // + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		try {
			Class.forName("org.postgresql.Driver");

			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void prepareStatement(String query, Object... parametros)
			throws Exception {
		preparedStatement = connection.prepareStatement(query);

		if (parametros != null) {
			int indice = 1;
			
			for(Object param : parametros){				
				if(Long.class.isInstance(param)){
					preparedStatement.setLong(indice, Long.parseLong(String.valueOf(param)));
				} else if (Double.class.isInstance(param)){
					preparedStatement.setDouble(indice, Double.parseDouble(String.valueOf(param)));
				} else {
					preparedStatement.setString(indice, String.valueOf(param));
				}
				
				indice++;
			}
			
		preparedStatement.execute();
			
		}
	}

	protected Boolean readResults() throws Exception {
		resultSet = preparedStatement.getResultSet();
		
		if(resultSet != null)
			return resultSet.next();
		
		return false;
	}

	protected void closeConnection() {
		try {
			if (connection != null) {
				if (!connection.isClosed())
					connection.close();
			}
			if (statement != null)
				statement = null;
			if (preparedStatement != null)
				preparedStatement = null;
			if (resultSet != null)
				resultSet = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
