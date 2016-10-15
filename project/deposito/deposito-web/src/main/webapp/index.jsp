<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="./resources/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css"/>
<link rel="stylesheet" href="./resources/css/bootstrap-theme.min.css"/>
<script src="./resources/js/bootstrap.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/>
<title>Grupo 1 - Depósito</title>
</head>

<body background="" style="background-attachment:fixed;background-position: top center;background-repeat:no-repeat;z-index:999;">
	
	<h3 style="padding-left: 30px;font-family:'Special Elite',cursive;"> Depósito Grupo 1 (ejemplo rodamientos, modificar)</h3>

	<div style="padding: 20px;">
		<div class="btn-group">
			<button class="btn btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					Admin <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="www.google.com">Cargar maestro de rodamientos</a></li>
					<li><a href="www.google.com">Generar cotizaciones pendientes</a></li>
					<li><a href="www.google.com">Generar órdenes de compra pendientes</a></li>
				</ul>
			</button>
			<button class="btn btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					Proveedores <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="./ingresarDatosDeProveedor.jsp">Crear/Modificar proveedor</a></li>
					<li><a href="./nuevaListaDePrecios.jsp">Ingresar lista de precios</a></li>
					<li><a href="./ingresarRemitoDeProveedor.jsp">Ingresar remito de proveedor</a></li>
				</ul>
			</button>
			<button class="btn btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					Clientes <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="./ingresarDatosDeCliente.jsp">Crear/Modificar cliente</a></li>
					<li><a href="http://localhost:8080/tpads-cpr-server/GenerarRemitosAClientesService">Generar remitos a clientes</a></li>
					<li><a href="http://localhost:8080/tpads-cpr-server/GenerarFacturasService">Generar facturas a clientes</a></li>
				</ul>
			</button>
		</div>
	</div>

	<ul class="nav nav-pills nav-stacked">
	  <li class="active"><a href="#">Home</a></li>
	</ul>
	<ul class="list-group">
	  <li class="list-group-item list-group-item-success"><a href="./comparativaDePreciosSearch.jsp">Listado/comparativa de precios</a></li>
	  <li class="list-group-item list-group-item-danger"><a href="./nuevaSolicitudDeCotizacion.jsp">Ingresar solicitud de cotizacion</a></li>
	  <li class="list-group-item list-group-item-info"><a href="./listarCotizaciones.jsp">Ver cotizaciones</a></li>
	  <li class="list-group-item list-group-item-warning"><a href="./nuevaOrdenDePedido.jsp">Ingresar orden de pedido</a></li>
	  <li class="list-group-item list-group-item-success"><a href="./listarOrdenesDeCompra.jsp">Ver ordenes de compra</a></li>
	  <li class="list-group-item list-group-item-danger"><a href="./listarRemitosAClientes.jsp">Ver remitos a clientes</a></li>
	  <li class="list-group-item list-group-item-info"><a href="./listarFacturas.jsp">Ver facturas a clientes</a></li>

	</ul>
</body>
</html>
