<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Primi due utenti più giovani</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Data di Nascita</th>
			
		</tr>
		<c:forEach var="utente" items="${dueUtentiPiuGiovani}">
			<tr>
				td>${utenteDao.id}</td>
				<td>${utenteDao.nome}</td>
				<td>${utenteDao.cognome}</td>
				<td>${utenteDao.data_nascita}</td>
				
			</tr>
		</c:forEach>
	</table>

	<br />

	<h2>Utenti in sospeso</h2>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Data di Nascita</th>
			
		</tr>
		<c:forEach var="utente" items="${utentiInSospeso}">
			<tr>
				<td>${utenteDao.id}</td>
				<td>${utenteDao.nome}</td>
				<td>${utenteDao.cognome}</td>
				<td>${utenteDao.data_nascita}</td>
			
			</tr>
		</c:forEach>
	</table>
</body>
</html>