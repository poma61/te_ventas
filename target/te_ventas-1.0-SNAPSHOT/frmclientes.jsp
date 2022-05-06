
<%
    if (session.getAttribute("login") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              rel="stylesheet" >
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet" 
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">

        <title>Frm cliente</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="META-INF/menu.jsp">
                <jsp:param name="opcion" value="clientes"/>  
            </jsp:include>
            <br>
            <h1>
                <c:if test="${requestScope.action=='nuevo'}" var="control" scope="request">
                    Nuevo 
                </c:if>
                <c:if test="${requestScope.action=='modificar'}" var="control" scope="request">
                    Modificar
                </c:if>
                registro de cliente
            </h1>
            <br>
            <form action="ClienteControlador" method="post">
                <input  type="hidden" name="id" value="${cliente.id}">
                <div class="mb-3">
                    <label for="" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre" 
                           value="${cliente.nombre}"placeholder="escriba su nombre" required>

                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Correo Electronico</label>
                    <input type="email" class="form-control" name="correo"
                           value="${cliente.correo}"      placeholder="escriba su correo" required>
                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Celular</label>
                    <input type="text" class="form-control" name="celular" 
                           value="${cliente.celular}"  placeholder="escriba su N° de cel." required >
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
            <br>
            <a href="ClienteControlador" class="btn btn-warning btn-sm">Volver</a>
            <br>
            <br>
            <a href="Logout" class="btn btn-danger btn-sm">Cerrar Sesion</a> 
        </div>
        <!-- Java Scripts -->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    </body>
</html>