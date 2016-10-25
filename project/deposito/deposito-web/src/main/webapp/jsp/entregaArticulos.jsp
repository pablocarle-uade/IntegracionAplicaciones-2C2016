<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<jsp:directive.page import="edu.uade.ida.deposito.dto.SolicitudArticuloDTO"/>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page language="java" import="edu.uade.ida.deposito.model.Articulo" />
	<jsp:directive.page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"/>
	<jsp:output doctype-root-element="html"
				doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
				doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
				omit-xml-declaration="true" />
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="./resources/js/jquery-1.11.1.min.js"></script>
		<link rel="stylesheet" href="./resources/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="./resources/css/bootstrap-theme.min.css"/>
		<script src="./resources/js/bootstrap.min.js"></script>
		<link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/>
		<title>Entrega de Articulos</title>
		<SCRIPT type="text/javascript">
	
		</SCRIPT>
	</head>
	<body>
		<jsp:scriptlet>
		<![CDATA[
			List<SolicitudArticuloDTO> solicitudesPendientes = (List<SolicitudArticuloDTO>)request.getAttribute("solicitudesPendientes");
		]]>
		</jsp:scriptlet>>
		<h1 style="padding-left: 30px; font-family: 'Special Elite',cursive;">Articulos pendientes de entrega</h1>
		<main>
			<section>
				<div id="mainList" >
					<form name="solicitudArticulosForm" id="solicitudArticulosForm" action="/EntregaArticulos" method="post">
						<ul>
						<jsp:scriptlet>
							<![CDATA[
								if (solicitudesPendientes != null && !solicitudesPendientes.isEmpty()) {
									for (SolicitudArticuloDTO sad : solicitudesPendientes) {
							]]>
						</jsp:scriptlet>
							<li>
								<input type="checkbox" 
										name=<jsp:expression>"checkEntrega" + sad.getIdSolicitudArticulo()</jsp:expression> 
										id=<jsp:expression>"checkEntrega" + sad.getIdSolicitudArticulo()</jsp:expression> 
								/>
								<div>
									<h3><jsp:expression>sad.getArticulo().getCodArticulo()</jsp:expression></h3>
									<p>
										<jsp:expression>sad.getArticulo().getDescripcion()</jsp:expression>
										<br>
										<jsp:expression>"Pedido por: " + sad.getCantidad()</jsp:expression>
									</p>
								</div>
								<input type="text" 
										name=<jsp:expression>"numStock" + sad.getIdSolicitudArticulo()</jsp:expression> 
										value=<jsp:expression><!-- TODO Obtener cuanto se puede otorgar --></jsp:expression> 
								/>
							</li>
						<jsp:scriptlet>
									}
								}
						</jsp:scriptlet>
						</ul>
						<input type="submit" name="submitBtn" title="Entregar Articulos" value="Entregar Articulos" >Entregar Articulos</input>
					</form>	
				</div>
			</section>
		</main>
	</body>
	</html>
</jsp:root>