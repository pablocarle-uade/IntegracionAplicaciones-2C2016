<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<jsp:directive.page isELIgnored="false" />
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
	<h1 style="padding-left: 30px; font-family: 'Special Elite',cursive;">Generar Entrega de Artículos</h1>
	<h4 style="padding-left: 30px; font-family: 'Special Elite',cursive;">Puede modificar las cantidades a entregar sugeridas por el sistema</h4>
	
	<div style="padding:5px;border:5px solid green;margin:0;overflow: auto;">
	<form id="entregaDeArticulosRequestForm">		
		<jsp:scriptlet>
			<![CDATA[
				List<SolicitudArticuloDTO> solicitudesPendientes = (List<SolicitudArticuloDTO>)request.getAttribute("solicitudesPendientes");
			]]>
		</jsp:scriptlet>
		<div id="mainList" class="form-group"  >
					<jsp:scriptlet>
						<![CDATA[
							if (solicitudesPendientes != null && !solicitudesPendientes.isEmpty()) {
								for (SolicitudArticuloDTO sad : solicitudesPendientes) {
						]]>
					</jsp:scriptlet>
						<jsp:scriptlet>
							<![CDATA[
								out.println("<div class='col-md-14 itemEntregaContainer' id='" + sad.getIdSolicitudArticulo() + "'>");
							]]>
						</jsp:scriptlet>
							<div class="input-group">
							    <div class="col-md-2">
									<span class="input-group-addon">Cód. Artículo</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + sad.getArticulo().getCodArticulo() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>									
								</div>
								<div class="col-md-3">
									<span class="input-group-addon">Nombre Artículo</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + sad.getArticulo().getNombre() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>	
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right: 0;">Cantidad Solicitada</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + sad.getCantidad() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>									
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right:0;">Stock Actual</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + sad.getArticulo().getStock() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>	
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right: 0;">Cantidad a Entregar</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='number' class='form-control contidadAEntregarValueHolder' value='" + sad.getCantidad() + "'></input>");
										]]>
									</jsp:scriptlet>	
								</div>
								<div class="col-md-1">
									<input type="checkbox" class="isIncludedValueHolder" checked="checked">Incluir</input>
								</div>
							</div>
							<br/>
						</div>
																
					<jsp:scriptlet>
								}
							}
							<![CDATA[
							out.println("</div>");
							]]>
					</jsp:scriptlet>
		</form>	
		</div>
		
		<div class="col-lg-12">
			<br/>
			<button id="btnProcesarEntregaDeArticulos" class="btn-primary waves-effect waves-light btn" type="button">Entregar Artículos</button>
		</div>	
		
		<script type="text/javascript" >
			//<![CDATA[
		
			$(document).ready(function() {

				$("#btnProcesarEntregaDeArticulos").on("click", function() {
					 var entregaDeArticulosRequest = [];
					 $(".itemEntregaContainer").each(function() {
						 var itemEntregaArticulos = {}
						 var idSolicitudArticulo = $(this).attr("id");
						 var includeItem = $("#" + idSolicitudArticulo + " .isIncludedValueHolder").is(":checked");
						 var cantidadAEntregar = $("#" + idSolicitudArticulo + " .contidadAEntregarValueHolder").val();
						 if (includeItem) {
							 itemEntregaArticulos.idSolicitudArticulo = idSolicitudArticulo;
							 itemEntregaArticulos.cantidad = cantidadAEntregar;
							 entregaDeArticulosRequest.push(itemEntregaArticulos);
						 }
					 });
					 // alert(JSON.stringify(entregaDeArticulosRequest));
					 doProcess(entregaDeArticulosRequest);
				});
				
			});
			
			function doProcess(entregaDeArticulosRequest) {
				$.ajax({
		             url: '/deposito-web/rest/entregasArticulo',
		             type: 'post',
		             contentType:"application/json; charset=utf-8",
		             success: function(response) {
		            	 window.location.href = "/deposito-web/jsp/solicitudesArticulosPendientes.jsp";
		             },
		             error: function (response) {
		                 alert("No se pudo procesar entrega de artículos" + response);
		             },
		             data: JSON.stringify(entregaDeArticulosRequest)
		         });					
			}
					
			//]]>
		</script>
	</body>
	</html>
</jsp:root>