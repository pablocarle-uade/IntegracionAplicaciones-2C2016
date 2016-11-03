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
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
<title>Artículos</title>
</head>
<body>
	<!-- Search panel with basic properties HERE background: #D3D3D3; -->
	<h2>Artículos</h2>
<div style="padding: 10px;border: 5px solid gray;margin: 0;" >
	<div class="row" class="col-sm-12 bg-primary">
		<div class="col-sm-3">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-sm" type="button">Código</button>
				</span> 
				<input type="text" class="form-control input-sm">
			</div>
		</div>
		<div class="col-sm-3">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-sm" type="button">Nombre</button>
				</span>
				<input type="text" class="form-control input-sm">
			</div>
		</div>
		<div class="col-sm-3">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-sm" type="button">Marca</button>
				</span> 
				<input type="text" class="form-control input-sm">
			</div>
		</div>
		<div class="col-sm-1">
			<div class="input-group">
				<select name="tipo" id="tipo" class="select-sm">
				    <option value="">Todo Tipo</option>
					<option value="Electro">Electro</option>
					<option value="Moda">Moda</option>
					<option value="Mueble">Mueble</option>
					<option value="Niños">Niños</option>
				</select>
			</div>
		</div>
		<div class="col-sm-1">
			<button id="btnClean" class="btn-default waves-effect waves-light btn" type="button">Limpiar</button>
		</div>	
		<div class="col-sm-1">
			<button id="btnSearch" class="btn-primary waves-effect waves-light btn" type="button">Buscar</button>
		</div>	
	</div>
</div>
	<br/>
	<table id="articulosTable" class="display" style="width:80%" >
        <thead>
            <tr>
                <th>Id</th>
                <th>Código</th>
                <th>Descripción</th>
                <th>Marca</th>
                <th>Precio</th>
                <th>Url Imagen</th>
            </tr>
        </thead>
    </table>

<script type="text/javascript">

	$(document).ready(function() {
		
		  $('#articulosTable').DataTable({
				"dom" : 'lrtip',
				"bInfo" : false,
				"bLengthChange": false,
				processing: true,
			    // serverSide: true,
			    "ajax": {
			         url: '/deposito-web/rest/articulos/search', 
		        	 dataFilter: function(data) {
	 		            var result = {};
	 		        	var json = $.parseJSON( data );
	 		        	result.recordsTotal = data.length;
	 		        	result.data = json;
	 		            return JSON.stringify(result);
			 		 }
			    },
			    columns: [
					{ data: 'id' },
					{ data: 'codArticulo' },
					{ data: 'descripcion' },
					{ data: 'marca' },
					{ data: 'precio' },
					{ data: 'urlImagen' }
			    ],
				"rowCallback" : function(row, data) {
					if ($.inArray(data.DT_RowId, selected) !== -1) {
						$(row).addClass('selected');
					}
				}
			  });
		
		var selected = [];
		$('#articulosTable tbody').on('click', 'tr', function() {
			var id = this.id;
			var index = $.inArray(id, selected);
			if (index === -1) { selected.push(id); } 
			else { selected.splice(index, 1); }
			$(this).toggleClass('selected');
		});
		// ~
	});
</script>

</body>
</html>