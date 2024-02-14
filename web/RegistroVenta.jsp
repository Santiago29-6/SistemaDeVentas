<%-- 
    Document   : RegistroVenta
    Created on : 17/01/2024, 01:44:48 PM
    Author     : Santiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" 
              integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    </head>
    <body>
        <table class="table table-striped table-hover table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Id Venta</th>
                    <th>Fecha</th>
                    <th>Numero de Serie</th>
                    <th>Cliente</th>
                    <th>Empleado</th>
                    <th>Total de venta</th>
                    <th>DESCARGA</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="rg" items="${registros}">
                    <tr>
                        <td>${rg.getIdVenta()}</td>
                        <td>${rg.getFecha()}</td>
                        <td>${rg.getnSerie()}</td>
                        <td>${rg.getnCliente()}</td>
                        <td>${rg.getnEmpleado()}</td> 
                        <td>$${rg.getTotal()}</td>
                        <td class="d-flex">
                            <a href="Controlador?menu=RegistroVenta&accion=Descargar&idven=${rg.getIdVenta()}" class="btn btn-success" onclick="alertar()">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
                                <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5"/>
                                <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708z"/>
                                </svg>
                                <script type="text/javascript">
                                    function alertar() {
                                        alert("Ya se descargo, debe de ver en gestor de archivos en descargas");
                                    }
                                </script>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <th>TOTAL:</th>
                    <td>$${total}</td>
                    <td><a onclick="print()" class="btn btn-success">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
                            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5"/>
                            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708z"/>
                            </svg>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
