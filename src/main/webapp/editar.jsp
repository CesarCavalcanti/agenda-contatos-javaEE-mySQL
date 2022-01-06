<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Contato"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%
		Contato contato = (Contato) request.getAttribute("contato");
	%>
	<h1>Editar novo contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" value=" <%out.print(contato.getIdcon());%>"
					class="Caixa1" readonly></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" value=" <%out.print(contato.getNome());%>"
					class="Caixa1"></td>
			</tr>
			<tr>
				<td><input type="email" name="email" class="Caixa1" value=" <%out.print(contato.getEmail());%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="Caixa2" value=" <%out.print(contato.getFone());%>"></td>
			</tr>
		</table>
		<input type="button" value="salvar" class="Botao1" onclick="validar()">
	</form>

	<script src="scripts/validador.js"></script>
</body>
</html>