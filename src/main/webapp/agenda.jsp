<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.Contato"%>
<%@ page import="java.util.List"%>
<!DOCTYPE>
<html html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%
	List<Contato> contatosLista = (List<Contato>) request.getAttribute("contatos");
	%>

	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo contato</a>
	<a href="report" class="Botao2">Relatorio</a>

	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Fone</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < contatosLista.size(); i++) {
			%>
			<tr>
				<td><%=contatosLista.get(i).getIdcon()%></td>
				<td><%=contatosLista.get(i).getNome()%></td>
				<td><%=contatosLista.get(i).getEmail()%></td>
				<td><%=contatosLista.get(i).getFone()%></td>
				<td><a href="select?idcon=<%=contatosLista.get(i).getIdcon()%>"
					class="Botao1" value="editar">Editar</a> <a
					href="javascript: confirmar(<%=contatosLista.get(i).getIdcon()%>)"
					class="Botao2" value="excluir">Excluir</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>