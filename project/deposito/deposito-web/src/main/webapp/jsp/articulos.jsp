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

var dataSet = [
               [ "Tiger Nixon", "System Architect", "Edinburgh", "5421", "2011/04/25", "$320,800" ],
               [ "Garrett Winters", "Accountant", "Tokyo", "8422", "2011/07/25", "$170,750" ],
               [ "Ashton Cox", "Junior Technical Author", "San Francisco", "1562", "2009/01/12", "$86,000" ],
               [ "Cedric Kelly", "Senior Javascript Developer", "Edinburgh", "6224", "2012/03/29", "$433,060" ],
               [ "Airi Satou", "Accountant", "Tokyo", "5407", "2008/11/28", "$162,700" ],
               [ "Brielle Williamson", "Integration Specialist", "New York", "4804", "2012/12/02", "$372,000" ],
               [ "Herrod Chandler", "Sales Assistant", "San Francisco", "9608", "2012/08/06", "$137,500" ],
               [ "Rhona Davidson", "Integration Specialist", "Tokyo", "6200", "2010/10/14", "$327,900" ],
               [ "Colleen Hurst", "Javascript Developer", "San Francisco", "2360", "2009/09/15", "$205,500" ],
               [ "Sonya Frost", "Software Engineer", "Edinburgh", "1667", "2008/12/13", "$103,600" ],
               [ "Jena Gaines", "Office Manager", "London", "3814", "2008/12/19", "$90,560" ],
               [ "Quinn Flynn", "Support Lead", "Edinburgh", "9497", "2013/03/03", "$342,000" ],
               [ "Charde Marshall", "Regional Director", "San Francisco", "6741", "2008/10/16", "$470,600" ],
               [ "Haley Kennedy", "Senior Marketing Designer", "London", "3597", "2012/12/18", "$313,500" ],
               [ "Tatyana Fitzpatrick", "Regional Director", "London", "1965", "2010/03/17", "$385,750" ],
               [ "Michael Silva", "Marketing Designer", "London", "1581", "2012/11/27", "$198,500" ],
               [ "Paul Byrd", "Chief Financial Officer (CFO)", "New York", "3059", "2010/06/09", "$725,000" ],
               [ "Gloria Little", "Systems Administrator", "New York", "1721", "2009/04/10", "$237,500" ],
               [ "Bradley Greer", "Software Engineer", "London", "2558", "2012/10/13", "$132,000" ],
               [ "Dai Rios", "Personnel Lead", "Edinburgh", "2290", "2012/09/26", "$217,500" ],
               [ "Jenette Caldwell", "Development Lead", "New York", "1937", "2011/09/03", "$345,000" ],
               [ "Yuri Berry", "Chief Marketing Officer (CMO)", "New York", "6154", "2009/06/25", "$675,000" ],
               [ "Caesar Vance", "Pre-Sales Support", "New York", "8330", "2011/12/12", "$106,450" ],
               [ "Doris Wilder", "Sales Assistant", "Sidney", "3023", "2010/09/20", "$85,600" ],
               [ "Angelica Ramos", "Chief Executive Officer (CEO)", "London", "5797", "2009/10/09", "$1,200,000" ],
               [ "Gavin Joyce", "Developer", "Edinburgh", "8822", "2010/12/22", "$92,575" ],
               [ "Jennifer Chang", "Regional Director", "Singapore", "9239", "2010/11/14", "$357,650" ],
               [ "Brenden Wagner", "Software Engineer", "San Francisco", "1314", "2011/06/07", "$206,850" ],
               [ "Fiona Green", "Chief Operating Officer (COO)", "San Francisco", "2947", "2010/03/11", "$850,000" ],
               [ "Shou Itou", "Regional Marketing", "Tokyo", "8899", "2011/08/14", "$163,000" ],
               [ "Michelle House", "Integration Specialist", "Sidney", "2769", "2011/06/02", "$95,400" ],
               [ "Suki Burks", "Developer", "London", "6832", "2009/10/22", "$114,500" ],
               [ "Prescott Bartlett", "Technical Author", "London", "3606", "2011/05/07", "$145,000" ],
               [ "Gavin Cortez", "Team Leader", "San Francisco", "2860", "2008/10/26", "$235,500" ],
               [ "Martena Mccray", "Post-Sales support", "Edinburgh", "8240", "2011/03/09", "$324,050" ],
               [ "Unity Butler", "Marketing Designer", "San Francisco", "5384", "2009/12/09", "$85,675" ]
           ];

  $(document).ready(function() {
	  var selected = [];
	  
	  $('#example').DataTable( {
		   "dom": 'lrtip',  
		   data: dataSet,
	       "rowCallback": function( row, data ) {
	            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
	                $(row).addClass('selected');
	            }
	        }
	    } );
	  
// 	    $("#example").DataTable({
// 	        "processing": true,
// 	        "serverSide": true,
// 	        "ajax": "scripts/ids-arrays.php",
// 	        "rowCallback": function( row, data ) {
// 	            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
// 	                $(row).addClass('selected');
// 	            }
// 	        }
// 	    });
	 
	    $('#example tbody').on('click', 'tr', function () {
	        var id = this.id;
	        var index = $.inArray(id, selected);
	 
	        if ( index === -1 ) {
	            selected.push( id );
	        } else {
	            selected.splice( index, 1 );
	        }
	 
	        $(this).toggleClass('selected');
	    } );
  });
  
</script>
	
</body>
</html>