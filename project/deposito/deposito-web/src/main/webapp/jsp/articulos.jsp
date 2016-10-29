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
<title>Nuevo Artículo</title>
</head>
<body>
	<!-- codigo, descripcion, marca, precio, urlImagen, origen, tipo -->
	<h4>Artículos</h4>
	<br>
	<table id="example" class="display" cellspacing="0" width="80%">
        <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
    </table>

<script type="text/javascript">

	$(document).ready(function() {
		
		  $('#example').DataTable({
				"dom" : 'lrtip',
				processing: true,
			    // serverSide: true,
			    "ajax": {
			         url: '/deposito-web/rest/demo/jsonDemo', 
		        	 dataFilter: function(data) {
	 		            var result = {};
	 		        	var json = $.parseJSON( data );
	 		        	result.recordsTotal = data.length;
	 		        	result.data = json;
	 		 			// alert(JSON.stringify( result ));
	 		            return JSON.stringify(result);
			 		 }
			    },
			    columns: [
					{ data: 'one' },
					{ data: 'two' },
					{ data: 'three' },
					{ data: 'four' },
					{ data: 'five' },
					{ data: 'six' }
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

			if (index === -1) {
				selected.push(id);
			} else {
				selected.splice(index, 1);
			}

			$(this).toggleClass('selected');
		});
		// ~
	});
</script>

</body>
</html>