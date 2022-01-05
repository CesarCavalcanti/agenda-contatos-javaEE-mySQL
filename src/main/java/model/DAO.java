package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
	
	/** Módulo de conexão **/
	
	//Parâmtros de conexão
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?userTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";
	
	//Método de conexão
	
	private Connection conectar() {
		Connection con = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
			
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD CREATE **/
	
	public void inserirContato(Contato contato) {
		String create = "insert into contatos (nome,fone,email) values(?,?,?)";
		try {
			// abrir conexão com o banco
			Connection con = conectar();
			//Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			//Substituir os parametros pelo conteúdo das variáveis.
			pst.setString(1,contato.getNome());
			pst.setString(2,contato.getFone());
			pst.setString(3,contato.getEmail());
			//Executar a query
			pst.executeUpdate();
			//Encerrar a conexão com o banco.
			con.close();
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	

}
