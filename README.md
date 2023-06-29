# AIS-Practica-3-2023

Autor(es): Alejandro Delgado García

-	Si una de las acciones desencadena un workflow, se pondrá un link al resultado de su ejecución.
-	En el caso del workflow de Nightly, se describirá cuándo se lanza, qué hace, un link a la última ejecución del workflow y un link a la última imagen generada
-	Se deben describir los pasos seguidos para incluir la nueva funcionalidad, indicando las acciones realizadas en el repositorio (creación de ramas, pull requests, etc) hasta el despliegue a producción. Para ello, la memoria incluirá qué comandos git se han utilizado para esta parte, y qué hace cada uno de ellos.
-	En el caso de publicar una imagen, se deberá incluir la URL de DockerHub de la misma

[Repositorio](https://github.com/AlejandroDelg/ais-a.delgadog.2019-2023-tbd)

[Aplicación Okteto](https://books-reviewer-alejandrodelg.cloud.okteto.net/)

## Captura del libro ``Alice's Adventures in Wonderland``


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
$ git push --set-upstream origin featureDesc
```
Iniciamos una pull request desde featureDesc a master, lo que desencadena en la ejecucion del workflow 1:[workflow 1 (A CAMBIAR)](https://github.com/AlejandroDelg/Repositorio-Auxiliar-Ampliacion-Ing-Software/actions/runs/5412611699)
Una vez que ha pasado todas las pruebas del workflow 1, Aceptamos el merge de la pull request, lo que desencadena en la ejecucion del workflow 2: [workflow 2 (A CAMBIAR)](https://github.com/AlejandroDelg/Repositorio-Auxiliar-Ampliacion-Ing-Software/actions/runs/5412656890/jobs/9837052320)
Nos situamos en la rama master y nos traemos los cambios actualizados:
```
$ git checkout master
$ git pull
```
Creamos una rama release y nos situamos en ella: 
```
$ git checkout -b release-1
```
hacemos commit de la rama: 
```
$ git commit -a -m "Añadidos puntos suspensivos a descripcion larga"
```
Hacemos push de la rama para poder ver los cambios en remoto: 
```
$ git push --set-upstream origin release-1
```
Como podemos observar, se ejecuta el workflow 3 [workflow 3(cambiar enlace)](https://github.com/AlejandroDelg/Repositorio-Auxiliar-Ampliacion-Ing-Software/actions/runs/5412918368) y se sube la imagen a docker hub con el tag del commit[imagen docker(cambiar enlace)](https://hub.docker.com/layers/alejandrodelg/books-reviewer/39addc19a6f548168aafd613a8ebc9a70fb648d6/images/sha256-3c0de45013ae5c2f2fd3d6dff1d1bd2b8bdb337bf3bf27812ba65521adc51352?context=repo), así como se despliega la aplicación en okteto[enlace](https://books-reviewer-alejandrodelg.cloud.okteto.net/)

## workflow de nightly: 
Este workflow(el workflow 4) se ejecuta todas las noches a las 2 de la madrugada. Concretamente, se lanza desde la rama master, y publicando una imagen en dockerhub con el tag de la fecha actual. Un ejemplo es el siguiente: [imagen docker fecha](https://hub.docker.com/layers/alejandrodelg/books-reviewer/dev-20230629.025722/images/sha256-49b24237e80829849f028750905a3375bb96cfb3cc870f0114eec442a26bc69a?context=repo)

