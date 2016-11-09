# IntegracionAplicaciones-2C2016
TP Integración de Aplicaciones 2C 2016

## Importar Proyecto en Eclipse

Utilizar preferentemente wizard "Importar proyecto existente de Eclipse", no
como Maven project por configuraciones especificas de proyecto de Eclipse.

Configurar en pom de proyecto ejb usuario y password de deploy a wildfly
Tener en cuenta que algunos goals, como wildfly:redeploy pueden requerir
especificarlos como parametros 

```
-Dwildfly.username=username
-Dwildfly.password=password
```

### Datasource: depositoDS

Configurar el datasource con este nombre en la consola de administracion de wildfly

### Logging

Solo inyectar java.util.Logger en donde sea necesario

## Configuración de interfaces

### Entrada

#### Solicitudes de Stock

JMS Queue: ColaSolicitudesArticulos

Crearla en la consola de adm de jboss con este nombre y nombre jndi: 
java:/jboss/exported/jms/queue/ColaSolicitudesArticulos

Luego los clientes remotos podrán accederla con jndi:
/jms/queue/ColaSolicitudesArticulos

#### Para mock fabrica

JMS Queue: java:/jboss/exported/jms/queue/RecepcionCompraQueue

### Salida

TODO
