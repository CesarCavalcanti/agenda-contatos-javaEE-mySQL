package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.Contato;

@WebServlet(urlPatterns = {"/Controller","/main", "/insert"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DAO dao = new DAO();
       Contato contato = new Contato ();
       
    
    public Controller() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/main")) {
			contatos(request,response);
		} else if(action.equals("/insert")) {
			novoContato(request,response);
		}else{
			response.sendRedirect("index.html");
		}
	}
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Leitura das vari�veis.
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//Invocar o m�todo inserir contato, passando o objeto contato
		dao.inserirContato(contato);
		//Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

}
