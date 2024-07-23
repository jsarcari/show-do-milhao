<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="showdomilhao.model.Scores" import="java.util.ArrayList" import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/Show_do_milhao.webp" />
<title>Show do Milhão</title>
</head>
<%
    List<Scores> listScores = new ArrayList<Scores>();
    if (request.getAttribute("LIST_SCORES") != null) {
        listScores = (List<Scores>)request.getAttribute("LIST_SCORES");
    }
%>
<body>
    <div>
        <h1>Ranking</h1>
        <% for (Scores score : listScores) { %>
            <div><%=score.getName()%>________________<%=score.getPremium()%></div>
        <% } %>
        <button type="button">Jogar novamente</button>
    </div>
</body>