package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.Contato;

@WebServlet(urlPatterns = {"/Controller","/main", "/insert","/select","/update","/delete","/report"})
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
		} else if(action.equals("/select")) {
			selecionarContato(request,response);
		} else if(action.equals("/update")) {
			editarContato(request,response);
		} else if(action.equals("/delete")) {
			deletarContato(request,response);
		} else if(action.equals("/report")) {
			gerarPdf(request,response);
		}else{
			response.sendRedirect("index.html");
		}
	}
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Contato> listaContatos = dao.findAll();
		request.setAttribute("contatos", listaContatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Leitura das variáveis.
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		//Invocar o método inserir contato, passando o objeto contato
		dao.inserirContato(contato);
		//Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		Contato contato = dao.findById(idcon);
		contato.setIdcon(idcon);
		System.out.println(contato);
		request.setAttribute("contato", contato);
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setEmail(request.getParameter("email"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setIdcon(request.getParameter("idcon"));
		dao.update(contato);
		response.sendRedirect("main");
	}
	
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dao.delete(request.getParameter("idcon"));
		response.sendRedirect("main");
	}
	
	protected void gerarPdf(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document ();
		try {
			//tipo de conteúdo
			response.setContentType("application/pdf");
			//Nome do documento
			response.addHeader("Content-Disposition", "inline; filename="+"contatos.pdf");
			//criar documento
			PdfWriter.getInstance(documento,response.getOutputStream());
			//abrir documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			//Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			//Cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Email"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Fone"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//Popular a tabela
			List<Contato> list = dao.findAll();
			for(int i = 0; i < list.size();i++) {
				tabela.addCell(list.get(i).getNome());
				tabela.addCell(list.get(i).getEmail());
				tabela.addCell(list.get(i).getFone());
				
			}
			documento.add(tabela);
			documento.close();
			
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
