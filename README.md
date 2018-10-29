## REQUISITOSS

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

* Si no se tiene JDK 1.8 se puede utilizar *docker-cli* para contruir una imágen de la aplicación. Ejecutar el siguiente comando sobre el path ms-mutants-ml anterior.

```bash
$ mvn clean install
```
### 3- Ejecutar aplicación:

*  Si se tiene jdk 1.8 se puede ejecutar la aplicación sin docker:
```bash
$ mvn spring-boot:run
```
* O se puede correr la imágen de docker:
```bash
$ docker run esegundorolon/ms-mutants-ml:latest -e <params> 
```

* Los parámetros posibles son:
    1. SERVER_PORT : Puerto donde se ejecuta el servidor Tomcat, por defecto el 8090
    2.  MUTANT_MATCH_FACTOR: Cantidad de bases nitrogenadas consecutivas a encontrar, por defecto 4
    3.  MUTANT_SECUENCES_OCCURENCES: Cantidad de secuencias repetidas para ser considerado mutante, por defecto 2
    4.  MS_CONFIGURATION_DATEFORMAT: Formato de timestamp en la respuesta de error, por defecto yyyy-MM-dd'T'HH:mm:ss'Z'

* Ejemplo: 
```bash
$ docker run esegundorolon/ms-mutants-ml:latest -e SERVER_PORT=1111 -e MUTANT_MATCH_FACTOR=5
```
## API DOC:

**Detectar ADN Mutante**
----
  Devuelve el ADN mutante si aplica.

* **URL**

  /api/v1/mutant

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 
    None

* **Data Params**

  **Required:**
  dna: array de strings que conforman matriz cuadrada, los caracteres aceptados son CGTA
  **Content:** `{ "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }`

* **Success Response:**

  * **Code:** 200 
    **Content:** `{  "dna": [ "ATGCGA","CAGTGC","TTATGT","AGAAGG", "CCCCTA","TCACTG"],"isMutant": true }`
 
* **Error Response:**

  * **Code:** 403 FORBIDDEN >
    **Content:** `{     "message": "Las secuencias de ADN ingresadas no corresponden a un mutante",     "timestamp": "2018-01-29T08:01:25Z",     "field": null }`

  OR

  * **Code:** 422 UNPROCESSABLE ENTITY <br />
    **Content:** `{     "message": "Las secuencias de ADN enviadas contienen caracteres invalidos o no forman una matrix NxN",     "timestamp": "2018-01-29T08:01:28Z",     "field": null }`
    
  OR
  
  * **Code:** 400 BAD REQUEST <br />
      **Content:** ` {     "message": "El body recibido es invalido, se espera un array de Strings",     "timestamp": "2018-01-29T08:01:30Z",     "field": "no puede ser null" }`

**Obtener estadisticas**
----
  Devuelve estadisticas de adn analizado.

* **URL**

  /api/v1/stats

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
    None

* **Data Params**

  **Required:**
    None

* **Success Response:**

  * **Code:** 200 
    **Content:** `{     "count_mutant_dna": 1,     "count_human_dna": 1,     "ratio": 0.5 }`

## TEST:


  * Importar los archivos del directorio ./collections/local.postman_environment.json y ./collections/prod.postman_environment.json
![import_environment](https://user-images.githubusercontent.com/29233071/35500785-66c16cee-04b6-11e8-92cc-57be0e8c79a3.png)
   * Importar la collection de pruebas ./collections/mutant-detector.postman_collection.json
![import_collection](https://user-images.githubusercontent.com/29233071/35500824-878a3e1a-04b6-11e8-8308-d5798e2ef44b.png)
   * Elegir el environment, ya sea desarrollo : **127.0.0.1:8090** o produccion: **35.198.40.169**
![select_enviroment](https://user-images.githubusercontent.com/29233071/35500870-be20b544-04b6-11e8-8887-d15e8dffe09e.png)





