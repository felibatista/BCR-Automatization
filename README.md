
# BCR-Automatization


Aquí podrá correr los casos de prueba de todas las historias de usuario (US01, US02, US04)
de manera automática obteniendo un feedback al acabar cada uno, donde encontrará información cómo:
- Resultado ✔️
- Tiempo de ejecución ⌛
- Informe de errores y otras acciones 📋


## Documentación de las historias de usuario

1️⃣ [Historia de usuario N°1 - US01](https://docs.google.com/document/d/1SvC4JJbQDRDjAhgNqep5h-a2cxDugRIQSvKmdtzk24E/edit?usp=sharing) \
2️⃣ [Historia de usuario N°2 - US02](https://docs.google.com/document/d/1SvC4JJbQDRDjAhgNqep5h-a2cxDugRIQSvKmdtzk24E/edit?usp=sharing) \
3️⃣ [Historia de usuario N°3 - US04](https://docs.google.com/document/d/1edFOk6xzP8QK3FLASnd0HxHBA6rm88eaej3JK-mH4pg/edit?usp=sharing)


## Aclaración antes de utilizarlo

La forma en la que Mercado Libre maqueta las páginas cambia según el tamaño del depositivo, pop-ups inesperados y las dimensiones que este maneje. Esto quiere decir que algunas opciones o botones se muestren distinto a la cual está pensado, dando como error inesperado en algún que otro caso o no pudiendo completar el caso. Tampoco se pueden usar IDs ya que estas cambian constantemente. Por lo que se recomienda realizar las pruebas con la web en Modo Escritorio y en una resolución de 1920x1080 (aunque está última no es 100% necesaria)

También es importante informar que los casos que contengan inicio de sesión, deberán ser asistidos por un usuario para completarse, ya que para el ingreso, se solicita el ingreso de un Captcha y el posterior clickeo al botón de "Continuar" (acciones que no se automatizaron). Hay formas de realizarlos automáticamente pero se dejará como una mejora para el futuro.  
## Requerimientos

Para poder correr de manera correcta los casos, se recomienda el uso de lo siguiente (no fue testeado en otras opciones por falta de tiempo):
- Java 17 o superior
- Google Chrome en una versión estable
- Windows 10 o superior
- Resolución de 1920x1080



## Quickstart - Como compilar la aplicación

Paso 1️⃣: Descargue el repositorio y abralo con su IDE favorito.
```
git clone https://github.com/felibatista/BCR-Automatization.git
```

Paso 2️⃣: Ir hacia Maven -> Lifecycle -> Package. Esto creará un .jar dentro de la carpeta /target en el directorio donde tiene el proyecto.

Paso 3️⃣: Crea un .bat con la siguiente información:
```
java -jar TestCases-BCR-1.0-SNAPSHOT-jar-with-dependencies.jar

pause
```

Paso 4️⃣: Abra el .bat creado recientemente y siga las instrucciones de la consola.

Paso 5️⃣: ¡Comenzar a TESTEAR 😎!


