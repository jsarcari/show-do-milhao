<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="showdomilhao.model.Scores" import="java.util.ArrayList" import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/Show_do_milhao.webp" />
<title>Show do Milhão</title>
<link rel="stylesheet" href="css/scores.css" type="text/css" />
</head>
<%
    List<Scores> listScores = new ArrayList<Scores>();
    if (request.getAttribute("LIST_SCORES") != null) {
        listScores = (List<Scores>)request.getAttribute("LIST_SCORES");
    }
%>
<body>
    <div class="container">
        <h1>Top 10</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Prêmio</th>
                </tr>
            </thead>
            <tbody>
            <% for (Scores score : listScores) { %>
                <tr>
                    <td><%=score.getName()%></td><td><%=score.getPremium()%></td>
                </tr>
            <% } %>
            </tbody>
        </table>
        <button type="button" class="button"><a href="/">Jogar novamente</a></button>
    </div>
</body>