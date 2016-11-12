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
<!-- <link href='http://fonts.googleapis.com/css?family=Special+Elite' rel='stylesheet' type='text/css'/> -->
<title>Nuevo Artículo</title>
</head>
<body>
	<h1 style="padding-left: 30px;font-family:'Special Elite',cursive;">Crear nuevo artículo</h1>
	<br>
	<form id="articuloBasePropertiesForm">
		<div id="articuloBasePropertiesContainer" class="form-group basePropertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Código</span>
					 <input name="codArticulo" type="number" class="form-control" required />
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Nombre</span> 
					<input name="nombre" type="text" class="form-control" required />
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
					<span class="input-group-addon">Stock</span> 
					<input name="stock" type="number" class="form-control" required />
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Origen</span> 
					<input name="origen" type="text" class="form-control" required />
				</div>
				<div class="input-group col s12">
					<span class="input-group-addon">Foto (link)</span> 
					<input name="foto" type="text" class="form-control" />
				</div>
				<br>
				<div class="form-group col s12">
					<label for="tipo">Tipo</label> 
					<select name="tipo" id="tipo">
						<option value="Electro">Electro</option>
						<option value="Moda">Moda</option>
						<option value="Mueble">Mueble</option>
						<option value="Niños">Niños</option>
					</select>
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<form id="ModaPropertiesForm">
		<div id="ModaPropertiesContainer" class="propertiesContainer">
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
	<form id="MueblePropertiesForm">
		<div id="MueblePropertiesContainer" class="propertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Material</span> 
					<input name="material" type="text" class="form-control" />
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<form id="NiñosPropertiesForm">
		<div id="NiñosPropertiesContainer" class="propertiesContainer">
			<div class="col-lg-8">
				<div class="input-group col s12">
					<span class="input-group-addon">Edad Recomendada</span> 
					<input name="edadRecomendada" type="number" class="form-control" />
				</div>
				<br/><br/>
			</div>
		</div>
	</form>
	<form id="ElectroPropertiesForm">
		<div id="ElectroPropertiesContainer" class="propertiesContainer">
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
  		loadSpecificProperties(getTipoDeArticulo());
    });
  	  	
  	$(".btnAddProperty").on("click", function() {
  		maxPropertyIndex = maxPropertyIndex + 1;
  		$("#EXTRAPropertiesContainer")
  		    .append("<div class='col-lg-8 extraPropertyContainer' id='extraProperty_" + maxPropertyIndex + "'>" +
  		    		    "<br/>" +
  		    			"<div class='input-group'>" + 
  		    				"<span class='input-group-addon'>Nombre Propiedad</span>" +
		  		    	    "<input type='text' class='form-control propertyName' placeholder='Indique nombre propiedad' required /> " +
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
		// alert("Service payload: " + JSON.stringify(articulo));  
	  	 $.ajax({
             url: '/deposito-web/rest/articulo',
             type: 'post',
             contentType:"application/json; charset=utf-8",
             success: function(response) {
                 window.location.href = "/deposito-web/jsp/articulos.jsp";
             },
             error: function (response) {
                 alert("No se pudo crear el artículo" + response);
             },
             data: JSON.stringify(articulo)
         });	
  }

	function addExtraPropertiesToJsonObject(jsonObject) {
		$(".extraPropertyContainer").each(function() {
			var propertyName = $("#" + $(this).attr("id") + " .propertyName").val();
			var propertyValue = $("#" + $(this).attr("id") + " .propertyValue").val();
			jsonObject[propertyName] = propertyValue;
		});
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
</script>
	
</body>
</html>