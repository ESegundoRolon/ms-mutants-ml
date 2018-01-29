## REQUISITOS

* Docker Windows 7 v17.05 o superior
* Apache Maven 3.5.0 o superior
* Git-cli en cualquier version
* Desarrollado en JDK 1.8.0_161 <Opcional>

## INSTALACIÓN:

### 1- Descargar el proyecto:


```bash
$ cd repositorio-git
$ git clone https://github.com/ESegundoRolon/ms-mutants-ml.git
$ cd ms-mutants-ml
```

### 2- Construir imagen de Docker:

* Utilizar docker-cli sobre el path ms-mutants-ml anterior o el build puede arrojar error

```bash
$ mvn clean install
```

### 3- Ejecutar aplicacion:

```bash
$ docker run esegundorolon/ms-mutants-ml:latest -e <params> 
```

*Los <params> posibles son:
**SERVER_PORT : Puerto donde se ejecuta el servidor Tomcat, por defecto el 8090
**MUTANT_MATCH_FACTOR: Cantidad de bases nitrogenadas consecutivas a encontrar, por defecto 4
**MUTANT_SECUENCES_OCCURENCES: Cantidad de secuencias repetidas para ser considerado mutante, por defecto 2
**MS_CONFIGURATION_DATEFORMAT: Formato de timestamp en la respuesta de error, por defecto yyyy-MM-dd'T'HH:mm:ss'Z'

*Ejemplo: 
```bash
$ docker run esegundorolon/ms-mutants-ml:latest -e SERVER_PORT=1111 -e MUTANT_MATCH_FACTOR=5
```
## API DOC: