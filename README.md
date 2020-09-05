# mercadolibre-technical-test

## Descripcion
El microservicio fue desarrollado en **Java 8** con **Spring Boot 2** y trabaja con una base de datos **H2** con la ORM de **Spring Boot JPA**, por ultimo, este fue desplegado en una instancia de la capa gratuita de **Amazon AWS**

## Prerequisitos
* Java 1.8.0_265
* Docker (opcional)

## Endpoint
* **URL:** http://ec2-18-218-108-67.us-east-2.compute.amazonaws.com/coupon
* **HTTP metodo:** POST
* **Request:** 
    ```json
    {
        "amount": 500.00,
        "item_ids": ["MLA1", "MLA2", "MLA3", "MLA4", "MLA5"]
    }
    ```
* **Response:**
    ```json
    {
        "total": 480.0,
        "item_ids": ["MLA2", "MLA4", "MLA5", "MLA1"]
    }
    ```
## Instricciones de puesta en marcha
1. Clonar el proyecto y ubicar se en la raiz de este
2. Compilar el proyecto
    **$ ./gradlew clean build docker** (si no se tienen **docker** instalada quitar lo del comando)
3. Ejecutar el proyecto - en ambos casos se ejecuta sobre el puerto **8080**
    * Sin docker: **$ java -jar build/libs/mercadolibre-technical-test-0.0.1-SNAPSHOT.jar**
    * Con docker: **$ docker run -p 8080:8080 mercadolibre-technical-test**
