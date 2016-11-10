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
									<input type="text" class="form-control" value="#Artículo1" readonly="true"></input>
								</div>
								<div class="col-md-3">
									<span class="input-group-addon">Nombre Artículo</span>
									<input type="text" class="form-control" value="NombreArtículo1" readonly="true"></input>
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right: 0;">Cantidad Solicitada</span>
									<input type="text" class="form-control" value="8" readonly="true"></input>
								</div>
								<div class="col-md-2">
									<span class="input-group-addon btnDeleteProperty" style="border-left: 0; border-right:0;">Stock Actual</span>
									<input type="text" class="form-control" value="7" readonly="true"></input>									
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right: 0;">Cantidad a Entregar</span>
									<input type="text" class="form-control contidadAEntregarValueHolder" value="7"></input>
								</div>
								<div class="col-md-1">
									<input type="checkbox" class="isIncludedValueHolder">Incluir</input>
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
			<button id="btnEntregaDeArticulosSubmit" class="btn-primary waves-effect waves-light btn" type="button">Entregar Artículos</button>
		</div>	
		
		<script type="text/javascript" >
			//<![CDATA[
		
			$(document).ready(function() {

				$("#btnEntregaDeArticulosSubmit").on("click", function() {
					 var entregaDeArticulosRequest = [];
					 $(".itemEntregaContainer").each(function() {
						 var itemEntregaArticulos = {}
						 var idSolicitudArticulo = $(this).attr("id");
						 var includeItem = $("#" + idSolicitudArticulo + " .isIncludedValueHolder").is(":checked");
						 var cantidadAEntregar = $("#" + idSolicitudArticulo + " .contidadAEntregarValueHolder").val();
						 if (includeItem) {
							 itemEntregaArticulos.idSolicitudArticulo = idSolicitudArticulo;
							 itemEntregaArticulos.cantidadAEntregar = cantidadAEntregar;
							 entregaDeArticulosRequest.push(itemEntregaArticulos);
						 }
					 });
					 alert(JSON.stringify(entregaDeArticulosRequest));
				});
				
			});
			
		    function addFormDataPropertiesToJsonObject(formId, jsonObject) {
				var formData = $("#" + formId).serializeArray();
				var i;
				for(i = 0; i < formData.length; i++) {
					var elementName = formData[i].name;
					var elementValue = formData[i].value;
					var $element = $("#" + formId + " [name='" + elementName + "']");
					if ($element.is("input") && ($element.attr("type") == "number")) {
						elementValue = parseFloat(elementValue);
					}
					jsonObject[elementName] = elementValue;
				}
		    }			
			//]]>
		</script>
	</body>
	</html>
</jsp:root>