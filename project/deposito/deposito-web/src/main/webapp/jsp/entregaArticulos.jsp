<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<jsp:directive.page import="edu.uade.ida.deposito.dto.SolicitudArticuloDTO"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page language="java" import="edu.uade.ida.deposito.model.Articulo" />
	<jsp:directive.page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"/>
	<jsp:output doctype-root-element="html"
				doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
				doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
				omit-xml-declaration="true"
				 />
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="./resources/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./resources/css/bootstrap-theme.min.css"/>
		<link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/>
		<title>Entrega de Articulos</title>
		<script type="text/javascript" src="./resources/js/jquery-1.11.1.min.js">
			//nothing
		</script>
		<script type="text/javascript" src="./resources/js/bootstrap.min.js">
			//nothing
		</script>
	</head>
	<body>
		<jsp:scriptlet>
			<![CDATA[
				List<SolicitudArticuloDTO> solicitudesPendientes = (List<SolicitudArticuloDTO>)request.getAttribute("solicitudesPendientes");
			]]>
		</jsp:scriptlet>
		<h1 style="padding-left: 30px; font-family: 'Special Elite',cursive;">Articulos pendientes de entrega</h1>
		<div id="mainList" class="form-group basePropertiesContainer" >
			<form name="solicitudArticulosForm" id="solicitudArticulosForm" action="/EntregaArticulos" method="post">
				<ul>
					<jsp:scriptlet>
						<![CDATA[
							if (solicitudesPendientes != null && !solicitudesPendientes.isEmpty()) {
								for (SolicitudArticuloDTO sad : solicitudesPendientes) {
						]]>
					</jsp:scriptlet>
						<li style="float: left;">
							<input type="checkbox" 
									name='${sad.getIdSolicitudArticulo()}'
									id='${"checkEntrega"}${sad.getIdSolicitudArticulo()}'
							/>
							<div>
								<h3>${sad.getArticulo().getCodArticulo()}</h3>
								<p>
									<jsp:expression>sad.getArticulo().getDescripcion()</jsp:expression>
									<br />
									<jsp:expression>"Pedido por: " + sad.getCantidad()</jsp:expression>
								</p>
							</div>
							<input type="text" 
									name='${"numStock"}${sad.getIdSolicitudArticulo()}'
									value="Obtener cuanto se puede entregar" 
							/>
						</li>
					<jsp:scriptlet>
								}
							}
					</jsp:scriptlet>
				</ul>
				<input type="submit" name="submitBtn" title="Entregar Articulos" value="Entregar Articulos" />
			</form>	
		</div>
		
		<script language="javascript" type="text/javascript" >

		</script>
	</body>
	</html>
</jsp:root>