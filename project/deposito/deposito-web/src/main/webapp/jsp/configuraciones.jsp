<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../resources/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css" />
<script src="../resources/js/bootstrap.min.js"></script>
<!-- <link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/>-->
<title>Configuraciones</title>
</head>
<body>
	<h3 style="padding-left: 30px;font-family:'Special Elite',cursive;">Cargar Configuraciones (edite los valores de ejemplo)</h3>
	<br>
	<form>
		<div>
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Configuración Despacho</span>
					<textarea id="despachosConfig" rows="40" cols="120">
					{
						"servers": [
							{
								"id": "G01",
								"restEndpoint": {
									"url": "http://locahost:8080",
									"resource": "/rest/nuevoArticulo",
									"method": "post"
								},
								"jmsEndpoint": {
									"providerUrl": "providerurl",
									"jmsQueue": "queue",
									"jmsTopic": null,
									"user": "user",
									"password": "pass"
								}
							},
							{
								"id": "G02",
								"restEndpoint": {
									"url": "http://locahost:8080",
									"resource": "/rest/nuevoArticulo",
									"method": "post"
								},
								"jmsEndpoint": {
									"jmsQueue": "queue",
									"jmsTopic": null,
									"user": "user",
									"password": "pass"
								}
							}
						]
					}
					</textarea> 
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<div class="col-lg-8">
		<br/>
		<button id="btnActualizarConfigDespachos" class="btn-primary waves-effect waves-light btn" type="button">Cargar Config Despachos</button>
		<br/><br/>	
	</div>
	
	<form>
		<div>
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Configuración Portales</span>
					<textarea id="portalesConfig" rows="20" cols="120">
					{
						"servers": [
							{
								"id": "G01",
								"jmsEndpoint": {
									"providerUrl": "http-remoting://10.100.56.66:8080",
									"jmsQueue": "jms/queue/deposito",
									"jmsTopic": null,
									"user": "guest",
									"password": "guest"
								}
							}
						]
					}
					</textarea> 
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<div class="col-lg-8">
		<br/>
		<button id="btnActualizarConfigPortales" class="btn-primary waves-effect waves-light btn" type="button">Cargar Config Portales</button>
		<br/><br/>	
	</div>
	
	<form>
		<div>
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Configuración Logística</span>
					<textarea id="logisticaConfig" rows="20" cols="120">
					{
						"servers": [
							{
								"id": "G01",
								"jmsEndpoint": {
									"providerUrl": "http-remoting://10.100.56.66:8080",
									"jmsQueue": "jms/queue/deposito",
									"jmsTopic": null,
									"user": "guest",
									"password": "guest"
								}
							}
						]
					}
					</textarea> 
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<div class="col-lg-8">
		<br/>
		<button id="btnActualizarConfigLogistica" class="btn-primary waves-effect waves-light btn" type="button">Cargar Config Logística</button>
		<br/><br/>	
	</div>
	
<script type="text/javascript">
	
	$(document).ready(function() {
		$("#btnActualizarConfigDespachos").click(function() {
	    	var despachoConfig = jsonFromString($("#despachosConfig").val());
	    	loadNewConfig(despachoConfig, "/deposito-web/rest/admin/loadDespachosConfig");
		});
		
		$("#btnActualizarConfigPortales").click(function() {
	    	var portalesConfig = jsonFromString($("#portalesConfig").val());
	    	loadNewConfig(portalesConfig, "/deposito-web/rest/admin/loadPortalesConfig");
		});
		
	  	$("#btnActualizarConfigLogistica").click(function() {
	    	var logisticaConfig = jsonFromString($("#logisticaConfig").val());
	    	loadNewConfig(logisticaConfig, "/deposito-web/rest/admin/loadLogisticaMonitoreoConfig");
	    });		
		
	});
	
	function jsonFromString(jsonString) {
		return JSON.parse( jsonString.replace("/\r?\n/g", '<br />') );
	}
	
	function loadNewConfig(newConfig, loadConfigUrl) {
		$.ajax({
             url: loadConfigUrl,
             type: 'post',
             contentType:"application/json; charset=utf-8",
             success: function(response) {
            	 alert("Se ha actualizado con éxito la config para : " + loadConfigUrl);
             },
             error: function (response) {
                 alert("No se pudo actualizar con éxito la config para: " + loadConfigUrl);
             },
             data: JSON.stringify(newConfig)
         });					
	}
	
</script>

</body>
</html>