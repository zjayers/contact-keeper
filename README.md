# Contact-Keeper

[![GitHub Release](https://img.shields.io/github/release/zjayers/contact-keeper.svg?style=flat)](https://github.com/zjayers/contact-keeper/releases)
[![GitHub pull requests](https://img.shields.io/github/issues-pr/zjayers/contact-keeper.svg?style=flat)](https://github.com/zjayers/contact-keeper/pulls)
[![Issues](https://img.shields.io/github/issues-raw/zjayers/contact-keeper.svg?maxAge=25000)](https://github.com/zjayers/contact-keeper/issues)

## Description

- Contact Keeper application

## Features

- Run the Source Code: `mvn spring-boot:run` in the root directory

## Installation

- Unpack the source code to a directory
- `cd` into the directory and run `mvn spring-boot:run`

OR

- `cd` into the directory and run `mvn package`
- run `java -jar target/contact-keeper-0.0.1-SNAPSHOT.jar`

## Usage

- API docs are served at `localhost:8080/swagger-ui/`
- API endpoints are as follows:
    - GET: /api/v1/contacts
    - POST: /api/v1/contacts
    - GET: /api/v1/contacts/{contactId}
    - PUT: /api/v1/contacts/{contactId}
    - DELETE: /api/v1/contacts/{contactId}
- Unit tests for the `ContactController` can be found in the `test` directory and run with `mvn test`
- H2 Console can be accessed at `localhost:8080/h2-console` with user `sa` and NO password. JDBC URL = `jdbc:h2:mem:contact_app`
- Technical planning diagrams can be found as PlantUML files in the `design` directory
