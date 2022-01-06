package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	/** Módulo de conexão **/

	// Parâmtros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?userTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";

	// Método de conexão

	private Connection conectar() {
		Connection con = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
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
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros pelo conteúdo das variáveis.
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o banco.
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/

	public List<Contato> findAll() {
		List<Contato> contatos = new ArrayList<>();
		String findAll = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(findAll);
			ResultSet rs = pst.executeQuery();
				
			while (rs.next()) {
				contatos.add(new Contato(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	/** CRUD UPDATE **/
	
	public Contato findById(String id) {
		String findById = "select * from contatos where idcon = ?";
		Contato contato = new Contato();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(findById);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				contato.setNome(rs.getString(2));
				contato.setEmail(rs.getString(3));
				contato.setFone(rs.getString(4));
			}
			con.close();
			return contato;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public void update (Contato contato) {
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?"; 
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

}
