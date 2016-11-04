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
<title>Artículos</title>
</head>
<body>
	<!-- Search panel with basic properties HERE background: #D3D3D3; -->
	<h2>Artículos</h2>
	<form id="searchForm">
		<div style="padding: 10px;border: 5px solid gray;margin: 0;" >
			<div class="row" class="col-sm-12 bg-primary">
				<div class="col-sm-3">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-sm" type="button">Código</button>
						</span> 
						<input name="codArticulo" type="text" class="form-control input-sm searchPanelControl">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-sm" type="button">Nombre</button>
						</span>
						<input name="nombre" type="text" class="form-control input-sm searchPanelControl">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-sm" type="button">Marca</button>
						</span> 
						<input name="marca" type="text" class="form-control input-sm searchPanelControl">
					</div>
				</div>
				<div class="col-sm-1">
					<div class="input-group">
						<select name="tipo" id="tipo" class="select-sm searchPanelControl">
						    <option value="">Todo Tipo</option>
							<option value="Electro">Electro</option>
							<option value="Moda">Moda</option>
							<option value="Mueble">Mueble</option>
							<option value="Niños">Niños</option>
						</select>
					</div>
				</div>
				<div class="col-sm-1">
					<button id="btnCleanSearch" class="btn-default waves-effect waves-light btn" type="button">Limpiar</button>
				</div>	
				<div class="col-sm-1">
					<button id="btnSearch" class="btn-primary waves-effect waves-light btn" type="button">Buscar</button>
				</div>	
			</div>
		</div>
	</form>
	<br />
	<div class="dropdown" style="margin-left: 10%;">
		<button class="btn btn-success dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">Acciones<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li><a href="#">Acción sobre lo seleccionado</a></li>
			<li><a href="#">Otra acción sobre lo seleccionado</a></li>
		</ul>
	</div>
	<br />
	<table id="articulosTable" class="display" style="width:80%" >
        <thead>
            <tr>
                <th></th>
                <th>Id</th>
                <th>Tipo</th>
                <th>Código</th>
                <th>Nombre</th>
                <th>Marca</th>
                <th>Precio</th>
            </tr>
        </thead>
    </table>

<script type="text/javascript">

	$(document).ready(function() {
		
		  $("#articulosTable").DataTable({
			   "pageLength": 50,
			    "dom" : 'lrtip',
				"bInfo" : false,
			    "bLengthChange": false,
			    "autoWidth": true,
			    processing: true,
			    "ajax": {
			         url: '/deposito-web/rest/articulos/search', 
			         "type": "POST",
			         "contentType": "application/json",
			         "bInfo" : false,
			         "data": function (d) { // post search data as json
			        	 var searchParams = {}
			        	 addFormDataPropertiesToJsonObject("searchForm", searchParams)
			        	 return JSON.stringify(searchParams);
			         },
		        	 dataFilter: function(data) {
	 		            var result = {};
	 		        	var json = $.parseJSON(data);
	 		        	result.recordsTotal = data.length;
	 		        	result.data = json;
	 		            return JSON.stringify(result);
			 		 }
			    },
			    rowId: 'id',
			    columns: [
					{ defaultContent: "", orderable: false, className: 'select-checkbox', width: "1%" },       
					{ data: "id", width: "4%" },
					{ data: "tipo", width: "10%" },
					{ data: "codArticulo", width: "15%" },
					{ data: "nombre", width: "40%" },
					{ data: "marca", width: "20%" },
					{ data: "precio", width: "10%", render: function (value) { return "$" + value; }  }
			    ],
				"rowCallback" : function(row, data) {
					if ($.inArray(data.DT_RowId, selected) !== -1) {
						$(row).addClass('selected');
					}
				}
			  });
		
			var selected = [];
			$("#articulosTable tbody").on("click", "tr", function() {
				var id = this.id;
				var index = $.inArray(id, selected);
				if (index === -1) { selected.push(id); } 
				else { selected.splice(index, 1); }
				$(this).toggleClass('selected');
			});
			
			$("#btnSearch").on("click", function() {
				// reload table => search on ajax call
				$("#articulosTable").DataTable().ajax.reload();
			});
			
			$("#btnCleanSearch").on("click", function() {
				$(".searchPanelControl").val("");
			});
			// ~
	});
	
	function addFormDataPropertiesToJsonObject(formId, jsonObject) {
		var formData = $("#" + formId).serializeArray();
		for (i = 0; i < formData.length; i++) {
			var elementName = formData[i].name;
			var elementValue = formData[i].value;
			var $element = $("#" + formId + " [name='" + elementName + "']");
			if ($element.is("input") && ($element.attr("type") == "number")) {
				elementValue = parseFloat(elementValue);
			}
			jsonObject[elementName] = elementValue;
		}
	}
</script>

</body>
</html>