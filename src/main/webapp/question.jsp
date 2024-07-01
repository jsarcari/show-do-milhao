<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show do Milhão</title>
</head>
<%
    String question = "Hello!";
    if (request.getAttribute("QUESTION") != null) {
        question = (String)request.getAttribute("QUESTION");
    }
%>
<body>
    <div>${PARTICIPANT}</div>
    <div>Valendo R$ ${VALUE_PREMIUM}, qual a resposta certa?</div>
<h1><%= question %></h1>
<ol>
    <li> ${ANSWER_ONE}</li>
    <li> ${ANSWER_TWO}</li>
    <li> ${ANSWER_THREE}</li>
    <li> ${ANSWER_FOUR}</li>
</ol>
</body>
</html>