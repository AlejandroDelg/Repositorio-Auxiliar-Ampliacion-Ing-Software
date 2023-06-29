# AIS-Practica-3-2023

Autor(es): Alejandro Delgado García

-	TBD por grupos grandes
-	Hacer captura de Alice's Adventures in Wonderland / Through the Looking Glass con URL https:///books/OL151411W (sino, es un 0)
-	Si una de las acciones desencadena un workflow, se pondrá un link al resultado de su ejecución.
-	En el caso del workflow de Nightly, se describirá cuándo se lanza, qué hace, un link a la última ejecución del workflow y un link a la última imagen generada
-	Se deben describir los pasos seguidos para incluir la nueva funcionalidad, indicando las acciones realizadas en el repositorio (creación de ramas, pull requests, etc) hasta el despliegue a producción. Para ello, la memoria incluirá qué comandos git se han utilizado para esta parte, y qué hace cada uno de ellos.
-	En el caso de publicar una imagen, se deberá incluir la URL de DockerHub de la misma

[Repositorio](https://github.com/AlejandroDelg/Repositorio-Auxiliar-Ampliacion-Ing-Software/)

[Aplicación Okteto](https://books-reviewer-alejandrodelg.cloud.okteto.net/)

![alt captura libro](https://github.com/AlejandroDelg/Repositorio-Auxiliar-Ampliacion-Ing-Software/blob/master/CapturaLibro.PNG)

## Desarrollo con (GitFlow/TBD)

Una vez creados los workflows y funcionando estos, pasamos a crear la nueva funcionalidad utilizando (Gitflow o TBD):

Lo primero que se tiene que hacer es crear una rama "feature", a partir de la rama master(que será la rama "trunk"). Para ello, creamos la rama featureDesc, que la utilizaremos con una duracion muy reducida y para solo una persona, debido a que estamos utilizando TBD. Además, nos posicionaremos en la nueva rama.
```
$ git checkout -b featureDesc
```
Añadimos la nueva funcionalidad, en nuestro caso asegurarnos que la descripcion no tenga una longitud mayor a 950 caracteres, y si tiene una longitud mayor se añaden 3 puntos al final. Para comprobar qué archivos han sido modificados, utilizamos el siguiente comando de git:

```
$ git status
```
Comprobamos que efectivamente solo se ha modificado el fichero BookDetail, por lo que procedemos a hacer commit de los cambios:

```
$ git commit -a -m "Añadidos puntos suspensivos a descripcion larga"
```
Tenenmos que hacer los cambios para que se puedan ver en remoto, para ello haremos push de nuestra rama y de nuestro commit:
```
$ git commit -a -m "Añadidos puntos suspensivos a descripcion larga"
```





