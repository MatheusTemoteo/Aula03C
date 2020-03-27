<%@pagelanguage="java"contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@pageimport="model.Cliente"%>
<!DOCTYPEhtml>
<html>
<head>
<metahttp-equiv="Content-Type"content="text/html; charset=UTF-8">
<title>Pais</title>

</head>
<body>
<%Pais pais = (Pais)request.getAttribute("pais"); %>
Id: <%=pais.getId() %><br>
Pais: <%=pais.getPais() %><br>
Populacao: <%=pais.getPopulacao() %><br>
Area: <%=pais.getArea() %><br>
</body>
</html>