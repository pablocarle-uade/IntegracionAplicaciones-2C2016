# IntegracionAplicaciones-2C2016
TP Integración de Aplicaciones 2C 2016

## Importar Proyecto en Eclipse

Utilizar preferentemente wizard "Importar proyecto existente de Eclipse", no
como Maven project por configuraciones especificas de proyecto de Eclipse.

Configurar en pom de proyecto ejb usuario y password de deploy a wildfly
Tener en cuenta que algunos goals, como wildfly:redeploy pueden requerir
especificarlos como parametros ´´´-Dwildfly.username=username´´´
y ´´´-Dwildfly.password=password´´´

### Datasource: depositoDS

Configurar el datasource con este nombre en la consola de administracion de wildfly

### Logging



## Configuración de interfaces

### Entrada

#### Solicitudes de Stock

JMS Queue: ColaSolicitudesArticulos

### Salida

TODO
