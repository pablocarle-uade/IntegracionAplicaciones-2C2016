<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../resources/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/bootstrap-theme.min.css" />
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/jqc-1.12.3/dt-1.10.12/b-1.2.2/r-2.1.0/rr-1.1.2/sc-1.4.2/se-1.2.0/datatables.min.css"/> -->
<!-- <script type="text/javascript" src="https://cdn.datatables.net/v/bs/jqc-1.12.3/dt-1.10.12/b-1.2.2/r-2.1.0/rr-1.1.2/sc-1.4.2/se-1.2.0/datatables.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
<title>Artículos</title>
</head>
<body>
	<!-- Search panel with basic properties HERE -->
	<h2>Artículos</h2>
	<br>
<div>
	<div class="row" class="col-sm-12">
		<div class="col-sm-6">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span> <input type="text" class="form-control" placeholder="Search for...">
			</div>
		</div>
		<div class="col-sm-6">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
		</div>
	</div>
	<br>
	<div class="row" class="col-sm-12">
		<div class="col-sm-6">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span> <input type="text" class="form-control" placeholder="Search for...">
			</div>
		</div>
		<div class="col-sm-6">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for...">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go!</button>
				</span>
			</div>
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
        <tfoot>
            <tr>
                <th>Id</th>
                <th>Código</th>
                <th>Descripción</th>
                <th>Marca</th>
                <th>Precio</th>
                <th>Url Imagen</th>
            </tr>
        </tfoot>
    </table>

<script type="text/javascript">

	$(document).ready(function() {
		
		  $('#articulosTable').DataTable({
				"dom" : 'lrtip',
				processing: true,
			    // serverSide: true,
			    "ajax": {
			         url: '/deposito-web/rest/demo/articulos/search', 
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
		$('#example tbody').on('click', 'tr', function() {
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