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
                <jsp:param name="opcion" value="productos"/>  
            </jsp:include>
            <br>
            <h1>
                <c:if test="${requestScope.action=='nuevo'}" var="control" scope="request">
                    Nuevo 
                </c:if>
                <c:if test="${requestScope.action=='modificar'}" var="control" scope="request">
                    Modificar
                </c:if>
                registro de productos
            </h1>
            <br>
            <form action="ProductoControlador" method="post">
                <input  type="hidden" name="id" value="${pro.id}">
                <div class="mb-3">
                    <label for="" class="form-label">Nombre</label>
                    <input type="text" class="form-control" name="nombre" 
                           value="${pro.nombre}" placeholder="Escriba el nombre del producto" required>

                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Descripcion</label>
                    <input type="text" class="form-control" name="descripcion"
                           value="${pro.descripcion}"   placeholder="Escriba la descripcion" required>
                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Precio</label>
                    <input type="number" class="form-control" name="precio" 
                           value="${pro.precio}"  placeholder="Escriba el precio" required  step=0.01>
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
            <br>
            <a href="ProductoControlador" class="btn btn-warning btn-sm">Volver</a>
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