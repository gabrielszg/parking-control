## Parking Control API

##### Controle de entrada, saída e cálculo do valor total a ser pago com base nas horas em que o carro ficou estacionado.

##### Tecnologias:

* Java: 11 LTS
* Frameworks: Spring Web, Spring Data JPA, Spring Security, Spring Doc (Swagger), Lombok
* Banco de Dados: MariaDB
* Deploy: Heroku

##### Executar o Banco de Dados:

```
docker run --name parking-db -p 3306:3306 -e MARIADB_DATABASE=parking -e MARIADB_USER=root -e MAR
IADB_PASSWORD=admin -e MARIADB_ROOT_PASSWORD=admin -d mariadb:latest
```

