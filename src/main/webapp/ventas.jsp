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
              rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">

        <title>ventas</title>
    </head>
    <body>

        <div class="container">

            <jsp:include page="META-INF/menu.jsp">
                <jsp:param name="opcion" value="ventas"/>  
            </jsp:include>
            <br>
            <h1>Ventas</h1>
            <br>
            <a href="VentaControlador?action=add" class="btn btn-primary btn-sm"><i class="fa-solid fa-circle-plus"></i>Nuevo</a>
            <br>
            <br>
            <table class="table table-info" border="1">
                <tr>
                    <th>Id</th>
                    <th>Producto</th>
                    <th>Nombre cliente</th>
                    <th>Fecha</th>
                    <th>Editar</th>
                    <th>Eliminar</th>

                </tr> 
                <c:forEach var="conten" items="${ven}">
                    <tr>
                        <td>${conten.id}</td>
                        <td>${conten.producto}</td>
                        <td>${conten.cliente}</td>
                        <td>${conten.fecha}</td>
                        <td><a href="VentaControlador?action=edit&id=${conten.id}" 
                               ><i class="fa-solid fa-pencil"></i></a></td>
                        <td><a href="VentaControlador?action=delete&id=${conten.id}"
                               onclick="return(confirm('Esta seguro de eliminar??'))"   ><i class="fa-regular fa-trash-can"></i></a></td>
                            </c:forEach>  
                </tr>
            </table>


            <br>
            <a href="Logout" class="btn btn-danger btn-sm">Cerrar Sesion</a> 

        </div>

        <!-- Java Scripts -->

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    </body>
</html>