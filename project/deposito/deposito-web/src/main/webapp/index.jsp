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
	
	<h1 style="padding-left: 30px;font-family:'Special Elite',cursive;"> Depósito Grupo 1</h1>
	<div style="padding: 20px;">
<!-- 		<div class="btn-group"> -->
<!-- 			<button class="btn btn-group"> -->
<!-- 				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!-- 					Admin <span class="caret"></span> -->
<!-- 				</a> -->
<!-- 				<ul class="dropdown-menu"> -->
<!-- 					<li><a href="./jsp/configuraciones.jsp">Cargar Nuevos Valores Config</a></li> -->
<!-- 				</ul> -->
<!-- 			</button> -->
<!-- 		</div> -->
		<div class="btn-group">
			<button class="btn btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					Test <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#" onclick="return generarArticulosTest();">Generar Artículos Test</a></li>
					<li><a href="#" onclick="return generarSolicitudArticuloTest();">Generar Solicitud Artículo Test</a></li>					
				</ul>
			</button>
		</div>
	</div>

	<ul class="nav nav-pills nav-stacked">
	  <li class="active"><a href="#">Home</a></li>
	</ul>
	<ul class="list-group">
	  <li class="list-group-item list-group-item-success"><a href="./jsp/articuloWizard.jsp">Crear nuevo artículo</a></li>
	  <li class="list-group-item list-group-item-danger"><a href="./jsp/articulos.jsp">Artículos</a></li>
	  <li class="list-group-item list-group-item-info"><a href="./jsp/solicitudesArticulosPendientes.jsp">Solicitudes de artículos pendientes</a></li>
<!-- 	  <li class="list-group-item list-group-item-warning"><a href="./.jsp">----</a></li> -->
	</ul>
	
	<script type="text/javascript">
	
		generarArticulosTest = function() {
			$.ajax({url: "./rest/articulos/test/crearArticulos",
				type: "post",
				dataType: "json",
				contentType: "application/json",
				success: function(data) {
					alert("creados artículos test");
					},
				failure: function(e) {
					alert("error " + e);
					}
				}
			);
		}	
		
		generarSolicitudArticuloTest = function() {
			if (!testData) {
				var testData = {
							idDespacho: "G01",
							codArticulo: "3122",
							cantidad: 5
						};
			}
			$.ajax({url: "./rest/articulos/test/crearSolicitudArticulo",
					type: "post",
					dataType: "json",
					contentType: "application/json",
					success: function(data) {
						alert("creada solicitud artículo test");
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
