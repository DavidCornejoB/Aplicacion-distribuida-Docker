# Aplicación Distribuida y Dockerizada
 **Descripción General:**
 
Servidor de base de datos PostgreSQL + PgAdmin.

Servidor de aplicaciones en Java Enterprise Edition.

Servidor web, Frontend en Ionic - Angular

## Servidor de base de datos PostgreSQL + PgAdmin:
Versión PostgreSQL: 14.1

## Servidor de aplicaciones en Java Enterprise Edition.
Versión de JDK: jdk-17.0.5+8.

Versión de Servidor Wildfly: wildfly-24.0.1.Final

### Estructura del proyecto:
![Estructura JEE](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/estructura%20jee.PNG)

### Unidad de persistencia:
![Persistence Unit](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/unidad%20de%20persistencia.PNG)

### Dependencias en Pom.xml:
![Pom.xml](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/pom%20dependencia%201.PNG)

### Plugin adicional en Pom.xml para la construcción automática de los dockerfiles del servidor web y servidor de aplicaciones:
```xml
              <plugin>
                    <groupId>io.fabric8</groupId>
                    <artifactId>docker-maven-plugin</artifactId>
                    <version>0.33.0</version>
                    <configuration>
                        <images>
                            <image>
                                <name>${company}/${project.artifactId}</name>
                                <alias>${project.artifactId}</alias>
                                <build>
                                    <tags>
                                        <tag>latest</tag>
                                    </tags>
                                    <contextDir>${project.basedir}/src/main/docker/backend</contextDir>
                                    <assembly>
                                        <mode>dir</mode>
                                        <name>maven/</name>
                                        <inline>
                                            <files>
                                                <file>
                                                    <source>
                                                        ${project.build.directory}/${project.build.finalName}.${project.packaging}
                                                    </source>
                                                    <outputDirectory>.</outputDirectory>
                                                </file>
                                            </files>
                                        </inline>
                                    </assembly>
                                </build>
                                <run>
                                    <ports>
                                        <port>8080:8080</port>
                                    </ports>
                                </run>
                            </image>
                        </images>
                    </configuration>
                </plugin>
```

**[ver archivo pom.xml](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/Servidor%20de%20Aplicaciones/appdocker/pom.xml)**


### Configuración del datasource en Standalone del Wildfly local:
![Standalone](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/standalone%20local%20datasource.PNG)

### Driver en Standalone local para conexión con PostgreSQL:
![Standalone](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/standalone%20local%20plugin.PNG)

### Servicios a ser consumidos desde Frontend:

#### Para Listar Vehículos:
![Servicio listar](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/servicio%20listar%20vehiculos.PNG)

#### Para Crear Vehículos:
![Servicio crear](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/servicio%20crear%20vehiculos.PNG)

#### Para Eliminar Vehículos:
![Servicio eliminar](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/servicio%20eliminar%20vehiculos.PNG)

**[ver Service.java](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/Servidor%20de%20Aplicaciones/appdocker/src/main/java/ec/edu/ups/distribuidos/appdocker/Service.java)**



## Servidor Web: Ionic + Angular:

Versión de Ionic: 6.20.1

Versión de NPM: 8.5.5

Versión de Node.js: v16.15.0

### Estructura de páginas del proyecto:
![Ionic project](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/estructura%20ionic.PNG)

### Configuración CORS en Ionic. Archivo proxy.conf.json:
![Ionic project](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/configuración%20proxy%20ionic.PNG)

### Especificamos el archivo proxy.conf.json en angular.json
![Ionic project](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/configuracion%20angular%20json.PNG)

**[ver angular.json](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/Servidor%20web/servidorweb/angular.json)**

### Consumo de servicios desde Ionic:

Instalamos dependencia "Axios" en el directorio de nuestro proyecto, con el siguiente comando:

```
$ npm install axios --save
```

E importamos la librería en los scripts .ts en donde vayamos a consumir los servicios:


```ts
import axios from "axios";
```


#### Consumiendo servicio de Crear Vehículos:
![Ionic project](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/consumiendo%20servicio%20crear%20vehiculo.PNG)

**[ver crear.vehiculo.page.ts](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/Servidor%20web/servidorweb/src/app/crear-vehiculo/crear-vehiculo.page.ts)**


#### Consumiendo servicio de Listar Vehículos:
![Ionic project](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/consumiendo%20servicio%20listar%20vehiculos.PNG)

**[ver listar.vehiculos.page.ts](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/Servidor%20web/servidorweb/src/app/listar-vehiculos/listar-vehiculos.page.ts)**


#### Consumiendo servicio de Eliminar Vehículos:
![Ionic project](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/consumiendo%20servicio%20eliminar%20vehiculo.PNG)

**[ver eliminar.vehiculo.page.ts](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/Servidor%20web/servidorweb/src/app/eliminar-vehiculo/eliminar-vehiculo.page.ts)**


#### Comando para correr la aplicación y exponerla de manera externa:

```
$ ionic serve --external
```


### Dockerización:

#### Dockerfile del Servidor de Aplicaciones:
![Dockerfile backend](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/dockerfile%20backend.PNG)

#### Dockerfile del Servidor Web:
![Dockerfile frontend](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/dockerfile%20frontend.PNG)

#### Docker-compose:

- Servidor Web:

![Docker-compose frontend](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/docker-compose%20frontend.PNG)

- Servidor de Aplicaciones:

![Docker-compose backend](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/docker-compose%20backend.PNG)

- Servidor de Base de Datos:

![Docker-compose bd](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/docker-compose%20base%20de%20datos.PNG)

- Servicio PgAdmin:

![Docker-compose pgadmin](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/docker-compose%20pgadmin.PNG)

- Volúmen: Se ha creado un volúmen para el servidor de base de datos. Cada que el servidor de base de datos se detiene, todos sus datos son eliminados, por lo tanto, tenemos que hacer un respaldo mediante un volúmen que comparta los datos de la base de datos en una ubicación de la máquina anfitriona:

![Docker-compose volume](https://github.com/DavidCornejoB/Aplicacion-distribuida-Docker/blob/main/capturas/docker-compose%20volumen.PNG)




### Información Adicional:

Versión Eclipse IDE: 2022-12 (4.26.0)




