<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../resources/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/select/1.2.0/css/select.dataTables.min.css">
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/>
<title>Solicitudes de Artículos Pendientes</title>
</head>
<body>
	<h1  style="padding-left: 30px;font-family:'Special Elite',cursive;">Solicitudes de Artículos Pendientes</h1>
	<br />
	<div class="dropdown" style="margin-left: 10%;">
		<button class="btn btn-success dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">Acciones<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li><a id="cmdGenerarSolicitudDeCompra" href="#">Generar Solicitud de Compra (Fábrica)</a></li>
			<li><a id="cmdGenerarEntregaDeArticulos" href="#">Generar Entrega de Artículos (Despachos)</a></li>
		</ul>
	</div>
	<br />
	<table id="solicitudesPendientesTable" class="display" style="width:80%" >
        <thead>
            <tr>
                <th></th>
                <th>Id</th>
                <th>Código Artículo</th>
                <th>Nombre</th>
                <th>Stock actual</th>
                <th>Stock solicitado</th>
                <th>Fecha Creación</th>
            </tr>
        </thead>
    </table>

<script type="text/javascript">

	$(document).ready(function() {
		
		  $("#solicitudesPendientesTable").DataTable({
			   "pageLength": 50,
			    "dom" : 'lrtip',
				"bInfo" : false,
			    "bLengthChange": false,
			    "autoWidth": true,
			    processing: true,
			    "ajax": {
			         url: '/deposito-web/rest/solicitudDeArticulos/pendientes', 
			         "type": "GET",
			         "contentType": "application/json",
			         "bInfo" : false,
		        	 dataFilter: function(data) {
	 		            var result = {};
	 		        	var json = $.parseJSON(data);
	 		        	result.recordsTotal = data.length;
	 		        	result.data = json;
	 		            return JSON.stringify(result);
			 		 }
			    },
			    rowId: 'idSolicitudArticulo',
			    columns: [
					{ defaultContent: "", orderable: false, className: 'select-checkbox', width: "1%" },       
					{ data: "idSolicitudArticulo", width: "5%" },
					{ data: "articulo.codArticulo", width: "15%" },
					{ data: "articulo.nombre", width: "40%" },
					{ data: "articulo.stock", width: "10%" },
					{ data: "cantidad", width: "10%" },
					{ defaultContent: getCurrentDate(), width: "20%" }
					// { data: "fechaCreacion", width: "20%" }
			    ],
				"rowCallback" : function(row, data) {
					if ($.inArray(data.DT_RowId, selectedSP) !== -1) {
						$(row).addClass('selected');
					}
				}
			  });
		
			var selectedSP = [];
			$("#solicitudesPendientesTable tbody").on("click", "tr", function() {
				var id = this.id;
				var index = $.inArray(id, selectedSP);
				if (index === -1) { selectedSP.push(id); } 
				else { selectedSP.splice(index, 1); }
				$(this).toggleClass('selected');
			});
			
			$("#cmdGenerarEntregaDeArticulos").on("click", function() {
				window.location.href = "/deposito-web/GenerarEntregaArticulosWizard" + "?idsSolicitudesOrigen=" + selectedSP.toString(); 
			})
			
			$("#cmdGenerarSolicitudDeCompra").on("click", function() {
				window.location.href = "/deposito-web/GenerarSolicitudDeCompra" + "?idsSolicitudesOrigen=" + selectedSP.toString(); 
			})
			// ~
	});
	
	function getCurrentDate() {
		var date = new Date();
		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();
		return day + "/" + month + "/" + year;
	}
	
</script>

</body>
</html>