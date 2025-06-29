# LiterAlura: Tu Catálogo de Libros Personal

¡Bienvenido/a a LiterAlura! Este proyecto es un catálogo de libros interactivo que te permite buscar libros en una API externa y gestionarlos en una base de datos local.

## Descripción

LiterAlura es una aplicación de consola desarrollada en Java con Spring Boot y PostgreSQL. Permite a los usuarios buscar libros por título en la API Gutendex (basada en el Proyecto Gutenberg) y almacenarlos en una base de datos local. Además, ofrece funcionalidades para listar los libros, autores y filtrar por idioma o año de nacimiento del autor.

## Funcionalidades Principales

1.  **Buscar libro por título:**
    *   Permite ingresar el título de un libro y buscarlo en la API Gutendex.
    *   Si el libro se encuentra, se muestra la información (título, autor, idioma, número de descargas) y se guarda en la base de datos.
    *   Maneja el caso en que el libro no se encuentra en la API, mostrando un mensaje informativo.
    *   Evita la inserción de libros duplicados en la base de datos.

2.  **Listar libros registrados:**
    *   Muestra todos los libros almacenados en la base de datos.

3.  **Listar autores registrados:**
    *   Muestra todos los autores presentes en la base de datos.

4.  **Listar autores vivos en un año determinado:**
    *   Permite ingresar un año y muestra los autores que estaban vivos en ese año (según la información disponible en la API).

5.  **Listar libros por idioma:**
    *   Permite seleccionar un idioma (ES, EN, FR, PT) y muestra los libros almacenados en la base de datos en ese idioma.

## Tecnologías Utilizadas

*   Java
*   Spring Boot
*   Spring Data JPA
*   PostgreSQL
*   Maven

## Cómo Empezar

1.  **Requisitos Previos:**
    *   Tener instalado JDK 17 o superior.
    *   Tener instalado Maven.
    *   Tener una instancia de PostgreSQL en ejecución.

2.  **Configuración:**
    *   Clonar este repositorio.
    *   Configurar la conexión a la base de datos PostgreSQL en el archivo `application.properties` (o `application.yml`).
    *   Asegurarse de tener las dependencias necesarias (Spring Data JPA y PostgreSQL Driver).

3.  **Ejecución:**
    *   Ejecutar la aplicación desde la línea de comandos utilizando Maven: `mvn spring-boot:run`

## API Utilizada

*   Gutendex: [https://gutendex.com/](https://gutendex.com/)
