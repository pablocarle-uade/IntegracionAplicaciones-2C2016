<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<jsp:directive.page isELIgnored="false" />
<jsp:directive.page import="edu.uade.ida.deposito.dto.ArticuloDTO"/>
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
		<!-- <link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/> -->
		<title>Modificación de Stock de Articulos</title>
		<script type="text/javascript" src="./resources/js/jquery-1.11.1.min.js">
			//nothing
		</script>
		<script type="text/javascript" src="./resources/js/bootstrap.min.js">
			//nothing
		</script>
	</head>
	<body>
	<h1 style="padding-left: 30px; font-family: 'Special Elite',cursive;">Modificar Stock de Artículos</h1>	
	<div style="padding:5px;border:5px solid green;margin:0;overflow: auto;">
	<form id="entregaDeArticulosRequestForm">		
		<jsp:scriptlet>
			<![CDATA[
				List<ArticuloDTO> articulos = (List<ArticuloDTO>)request.getAttribute("articulosModificacionStock");
			]]>
		</jsp:scriptlet>
		<div id="mainList" class="form-group"  >
					<jsp:scriptlet>
						<![CDATA[
							if (articulos != null && !articulos.isEmpty()) {
								for (ArticuloDTO articulo : articulos) {
						]]>
					</jsp:scriptlet>
						<jsp:scriptlet>
							<![CDATA[
								out.println("<div class='col-md-14 itemContainer' id='" + articulo.getId() + "'>");
							]]>
						</jsp:scriptlet>
							<div class="input-group">
							    <div class="col-md-2">
									<span class="input-group-addon">Cód. Artículo</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + articulo.getCodArticulo() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>									
								</div>
								<div class="col-md-5">
									<span class="input-group-addon">Nombre Artículo</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + articulo.getNombre() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>	
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right:0;">Stock Actual</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='text' class='form-control' value='" + articulo.getStock() + "' readonly='true'></input>");
										]]>
									</jsp:scriptlet>	
								</div>
								<div class="col-md-2">
									<span class="input-group-addon" style="border-left: 0; border-right: 0;">Nuevo Stock</span>
									<jsp:scriptlet>
										<![CDATA[
											out.println("<input type='number' class='form-control nuevoStockValueHolder' value='" + articulo.getStock() + "'></input>");
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
			<button id="btnModificarStockArticulos" class="btn-primary waves-effect waves-light btn" type="button">Modificar Stock</button>
		</div>	
		
		<script type="text/javascript" >
			//<![CDATA[
		
			$(document).ready(function() {

				$("#btnModificarStockArticulos").on("click", function() {
					 var modificarStockArticulosRequest = [];
					 $(".itemContainer").each(function() {
						 var itemModificarStockArticulo = {}
						 var idArticulo = $(this).attr("id");
						 var includeItem = $("#" + idArticulo + " .isIncludedValueHolder").is(":checked");
						 var nuevoStock = $("#" + idArticulo + " .nuevoStockValueHolder").val();
						 if (includeItem) {
							 itemModificarStockArticulo.idArticulo = idArticulo;
							 itemModificarStockArticulo.nuevoStock = nuevoStock;
							 modificarStockArticulosRequest.push(itemModificarStockArticulo);
						 }
					 });
					 // alert(JSON.stringify(modificarStockArticulosRequest));
					 doProcess(modificarStockArticulosRequest);
				});
				
			});
			
			function doProcess(modificarStockArticulosRequest) {
				$.ajax({
		             url: '/deposito-web/rest/articulos/stock',
		             type: 'PUT',
		             contentType:"application/json; charset=utf-8",
		             success: function(response) {
		            	 window.location.href = "/deposito-web/jsp/articulos.jsp";
		             },
		             error: function (response) {
		                 alert("No se pudo procesar modificar stock de artículos" + response);
		             },
		             data: JSON.stringify(modificarStockArticulosRequest)
		         });					
			}
					
			//]]>
		</script>
	</body>
	</html>
</jsp:root>