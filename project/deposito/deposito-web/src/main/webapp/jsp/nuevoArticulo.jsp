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
<title>Nuevo Artículo</title>
</head>
<body>
	<!-- codigo, descripcion, marca, precio, urlImagen, origen, tipo -->
	<h1>Crear nuevo artículo</h1>
	<br>
	<div id="articuloBasePropertiesContainer" class="form-group basePropertiesContainer">
		<div class="col-lg-8">
			<div class="input-group col s12">
				<span class="input-group-addon">Código</span>
				 <input name="codigo" type="text" class="form-control" />
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Descripción</span>
				<textarea name="descripcion" rows="3" class="form-control"></textarea>
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Marca</span> 
				<input name="marca" type="text" class="form-control" />
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Precio</span> 
				<input name="precio" type="number" class="form-control" />
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Origen</span> 
				<input name="origen" type="text" class="form-control" />
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Imagen</span> 
				<input name="urlImagen" type="text" placeholder="(link)" class="form-control" />
			</div>
			<br>
			<div class="form-group col s12">
				<label for="tipo">Tipo</label> 
				<select name="tipo" id="tipo">
					<option value="ELECTRO">ELECTRO</option>
					<option value="MODA">MODA</option>
					<option value="MUEBLE">MUEBLE</option>
					<option value="NIÑOS">NIÑOS</option>
				</select>
			</div>
			<br/><br/>
		</div>
	</div>
	<div id="MODAPropertiesContainer" class="propertiesContainer">
		<div class="col-lg-8">
			<div class="input-group col s12">
				<span class="input-group-addon">Color</span> 
				<input name="color" type="text" class="form-control" />
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Talle</span>
				<input name="talle" type="text" class="form-control" />
			</div>
			<br/><br/>
		</div>
	</div>
	<div id="MUEBLEPropertiesContainer" class="propertiesContainer">
		<div class="col-lg-8">
			<div class="input-group col s12">
				<span class="input-group-addon">Material</span> 
				<input name="material" type="text" class="form-control" />
			</div>
			<br/><br/>
		</div>
	</div>
	<div id="NIÑOSPropertiesContainer" class="propertiesContainer">
		<div class="col-lg-8">
			<div class="input-group col s12">
				<span class="input-group-addon">Edad Recomendada</span> 
				<input name="edadRecomendada" type="number" class="form-control" />
			</div>
			<br/><br/>
		</div>
	</div>
	<div id="ELECTROPropertiesContainer" class="propertiesContainer">
		<div class="col-lg-8">
			<div class="input-group col s12">
				<span class="input-group-addon">Ficha Técnica</span>
				<textarea name="descripcion" rows="10" class="form-control"></textarea> 
			</div>
			<br/><br/>
		</div>
	</div>
	<div class="col-lg-8">
		<button class="btn waves-effect waves-light btn btnAddProperty" type="button">Agregar Propiedades Extra (opcional)</button>
		<br/><br/>	
	</div>
	<div class="col-lg-8">
		<button class="btn waves-effect waves-light btn btnAddProperty" type="button">Crear</button>
		<br/><br/>	
	</div>	
	
<!-- 	<div id="EXTRAPropertiesContainer" class="form-group"> -->
<!-- 		<div class="col-lg-8"> -->
<!-- 			<div class="input-group"> -->
<!-- 				<input class="input-group-addon" type="text" value="Ficha Técnica"/> -->
<!-- 				<input name="edadRecomendada" type="text" class="form-control" /> -->
<!-- 			</div> -->
<!--             <br/> -->
<!-- 			<button class="btn waves-effect waves-light btn btnNextStepAfterSpecificProperties" type="button">Siguiente</button> -->
<!-- 			<br/><br/> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<div id="EXTRAPropertiesContainer" class="form-group propertiesContainer"></div>	

	<div id="hidden" style="display:none"></div>

<script type="text/javascript">
  $(document).ready(function() {
  	
	init();  
	  
  	$("#btnNextStepAfterBaseProperties").click(function() {
        var tipoArticulo = $("#tipo").val();
        loadSpecificProperties(tipoArticulo);
    });
  	
  	$("#tipo").on("change", function() {
  		var tipoArticulo = this.value;
  		loadSpecificProperties(tipoArticulo);
    });
  	
  	$(".btnAddProperty").on("click", function() {
  		$("#EXTRAPropertiesContainer")
  		    .append("<br/><br/>")
  		    .append("<div class='col-lg-8'>" + 
  		    			"<div class='input-group'>" + 
  		    				"<span class='input-group-addon'>Nombre Propiedad</span>" +
		  		    	    "<input type='text' class='form-control' placeholder='Indique nombre propiedad' /> " +
		  		    	    "<span class='input-group-addon' style='border-left: 0; border-right: 0;'>Valor</span>" +
			  				"<input type='text' class='form-control' placeholder='Indique el valor' /> " +
	  					"</div>" +
			  		"</div>");
  		$("#EXTRAPropertiesContainer").show();
  	});
  	
  });
  
  function init() {
  	$(".propertiesContainer").hide();
  	loadSpecificProperties($("#tipo").val()); 
  }
  
  function loadSpecificProperties(tipoArticulo) {
	    $(".propertiesContainer").hide(); // hide all others
	    var specificPropertiesContainerId = tipoArticulo + "PropertiesContainer";
        $("#" + specificPropertiesContainerId).show();
  }
  
  // APPEND => $("#extraPropertiesContainer").append("<input class='form-control' type='text' value='INSERTED'/><br/><br/>");

</script>
	
</body>
</html>