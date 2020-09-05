# mercadolibre-technical-test

## Descripcion
El microservicio fue desarrollado en **Java 8** con **Spring Boot 2** y trabaja con una base de datos **H2** con la ORM de **Spring Boot JPA**

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
