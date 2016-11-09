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
					<li><a href="./EntregaArticulos" title="Gestionar solicitudes de articulos">Entrega de Articulos</a></li>
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
					Tests <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#" onclick="return generarSolicitudArticuloTest();">Generar Solicitud Articulo</a></li>
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
	  <li class="list-group-item list-group-item-success"><a href="./jsp/nuevoArticulo.jsp">Crear nuevo artículo</a></li>
	  <li class="list-group-item list-group-item-danger"><a href="./jsp/articulos.jsp">Artículos</a></li>
	  <li class="list-group-item list-group-item-info"><a href="./jsp/solicitudesDeArticulosPendientes.jsp">Solicitudes de artículos pendientes</a></li>
<!-- 	  <li class="list-group-item list-group-item-warning"><a href="./nuevaOrdenDePedido.jsp">----</a></li> -->
<!-- 	  <li class="list-group-item list-group-item-success"><a href="./listarOrdenesDeCompra.jsp">----</a></li> -->
<!-- 	  <li class="list-group-item list-group-item-danger"><a href="./listarRemitosAClientes.jsp">----</a></li> -->
<!-- 	  <li class="list-group-item list-group-item-info"><a href="./listarFacturas.jsp">----</a></li> -->

	</ul>
	
	<script type="text/javascript">
		generarSolicitudArticuloTest = function() {
			if (!testData) {
				var testData = {
							idDespacho: "G01",
							codArticulo: "",
							cantidad: 5
						};
			}
			
			$.ajax({url: "./rest/articulos/test/crearSolicitudArticulo",
					type: "post",
					dataType: "json",
					contentType: "application/json",
					success: function(data) {
						alert("creada solicitud articulo test");
						},
					data: JSON.stringify(testData),
					failure: function(e) {
						alert("error " + e);
						}
					}
			);
			return true;
		}
	</script>
</body>
</html>
