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
	<h2>Crear nuevo artículo</h2>
	<br>
	<form id="articuloBasePropertiesForm">
		<div id="articuloBasePropertiesContainer" class="form-group basePropertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Código</span>
					 <input name="codArticulo" type="number" class="form-control" required />
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Descripción</span>
					<textarea name="descripcion" rows="3" class="form-control"></textarea>
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Marca</span> 
					<input name="marca" type="text" class="form-control" required />
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Precio</span> 
					<input name="precio" type="number" class="form-control" required />
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Origen</span> 
					<input name="origen" type="text" class="form-control" required />
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
	</form>
	<form id="MODAPropertiesForm">
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
	</form>
	<form id="MUEBLEPropertiesForm">
		<div id="MUEBLEPropertiesContainer" class="propertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Material</span> 
					<input name="material" type="text" class="form-control" />
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<form id="NIÑOSPropertiesForm">
		<div id="NIÑOSPropertiesContainer" class="propertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Edad Recomendada</span> 
					<input name="edadRecomendada" type="number" class="form-control" />
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<form id="ELECTROPropertiesForm">
		<div id="ELECTROPropertiesContainer" class="propertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Ficha Técnica</span>
					<textarea name="fichaTecnica" rows="10" class="form-control"></textarea> 
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<div class="col-lg-8">
		<button class="btn waves-effect waves-light btn btnAddProperty" type="button">Agregar Propiedad/es Extra (opcional)</button>
		<br/><br/>	
	</div>
	
	<form id="EXTRAPropertiesForm"> 
		<div id="EXTRAPropertiesContainer" class="form-group propertiesContainer"></div>
	</form>
	
	<div class="col-lg-8">
		<br/>
		<button id="btnCreateArticulo" class="btn-primary waves-effect waves-light btn" type="button">Crear Artículo</button>
		<br/><br/>	
	</div>	
		
<script type="text/javascript">

  var extraPropertiesCount = 0;
  var maxPropertyIndex = 0;

  $(document).ready(function() {

	init();  
	  
  	$("#btnNextStepAfterBaseProperties").click(function() {
        var tipoArticulo = getTipoDeArticulo();
        loadSpecificProperties(tipoArticulo);
    });
  	
  	$("#tipo").on("change", function() {
  		loadSpecificProperties(this.value);
    });
  	  	
  	$(".btnAddProperty").on("click", function() {
  		maxPropertyIndex = maxPropertyIndex + 1;
  		$("#EXTRAPropertiesContainer")
  		    .append("<div class='col-lg-8 extraPropertyContainer' id='extraProperty_" + maxPropertyIndex + "'>" +
  		    		    "<br/>" +
  		    			"<div class='input-group'>" + 
  		    				"<span class='input-group-addon'>Nombre Propiedad</span>" +
		  		    	    "<input type='text' class='form-control propertyName' placeholder='Indique nombre propiedad' /> " +
		  		    	    "<span class='input-group-addon' style='border-left: 0; border-right: 0;'>Valor</span>" +
			  				"<input type='text' class='form-control propertyValue' placeholder='Indique el valor' /> " +
			  				"<span onclick='removeAdditionalProperty(" + maxPropertyIndex + ")' class='input-group-addon btnDeleteProperty' style='border-left: 0; border-right: 0;'>Eliminar</span>" +
	  					"</div>" +
			  		"</div>");
  		$("#EXTRAPropertiesContainer").show();
  	});
  	
  	$("#btnCreateArticulo").on("click", function() {
  		onCreateArticulo();
  	});
  
  });
  
  function init() {
  	$(".propertiesContainer").hide();
  	loadSpecificProperties(getTipoDeArticulo());
  }
  
  function getTipoDeArticulo() {
	  return $("#tipo").val();
  }
  
  function removeAdditionalProperty(index) {
	  $("#extraProperty_" + index).remove();
  }
  
  function loadSpecificProperties(tipoArticulo) {
	    $(".propertiesContainer").hide(); // hide all others
	    var specificPropertiesContainerId = tipoArticulo + "PropertiesContainer";
        $("#" + specificPropertiesContainerId).show();
  }
  
  function onCreateArticulo() {
	  	var articulo = { datosExtra : {} };
	  	addFormDataPropertiesToJsonObject("articuloBasePropertiesForm", articulo);
	  	addFormDataPropertiesToJsonObject(getTipoDeArticulo() + "PropertiesForm", articulo.datosExtra);
	  	addExtraPropertiesToJsonObject(articulo.datosExtra);

	  	alert("Service payload: " + JSON.stringify(articulo));  	
  }

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
  
  function addExtraPropertiesToJsonObject(jsonObject) {
	  $(".extraPropertyContainer").each(function() {
	  		var propertyName = $("#" + $(this).attr("id") + " .propertyName").val();
	  		var propertyValue = $("#" + $(this).attr("id") + " .propertyValue").val();
			jsonObject[propertyName] = propertyValue;
	  });
  }
  
</script>
	
</body>
</html>