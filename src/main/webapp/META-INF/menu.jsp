<%
//scriptles
    String op = request.getParameter("opcion");
%>
<!-- -->
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link <%=(op.equals("productos") ? "active" : "")%>" 
           href="ProductoControlador">Productos</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(op.equals("clientes") ? "active" : "")%>" 
           href="ClienteControlador">Clientes</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%=(op.equals("ventas")? "active":"")%>" 
           href="VentaControlador">Ventas</a>
    </li>
    <li class="nav-item">
        <a class="nav-link disabled">Disabled</a>
    </li>

</ul>   