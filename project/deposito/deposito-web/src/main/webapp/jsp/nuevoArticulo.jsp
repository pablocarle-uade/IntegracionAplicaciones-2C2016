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
	<div id="articuloBasePropertiesContainer" class="form-group">
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
					<option value="1">ELECTRODOMESTICO</option>
					<option value="2">MODA</option>
					<option value="3">MUEBLE</option>
					<option value="4">NIÑOS</option>
				</select>
			</div>
            <br/>
			<button class="btn waves-effect waves-light btn" id="btnNextStepAfterBaseProperties" type="button">Siguiente</button>
			<br/><br/>
		</div>
	</div>
	<div id="articuloModaPropertiesContainer">
		<div class="col-lg-8">
			<div class="input-group col s12">
				<span class="input-group-addon">Color</span> 
				<input name="color" type="text" class="form-control" />
			</div>
			<div class="input-group col s12">
				<span class="input-group-addon">Talle</span>
				<input name="talle" type="text" class="form-control" />
			</div>
            <br/>
			<button class="btn waves-effect waves-light btn" id="btnNextStepAfterSpecificProperties" type="button">Siguiente</button>
		</div>
	</div>

<script type="text/javascript">
  $(document).ready(function() {
  	alert("on_load_");
  	$("#btnNextStepAfterBaseProperties").click(function() {
        alert("Next after base => ...")
    });
  });

</script>
	
</body>
</html>