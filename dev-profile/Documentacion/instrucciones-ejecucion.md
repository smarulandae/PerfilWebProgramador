# Manual de uso

1. Requisitos:
   - JDK 11+ (JAVA_HOME configurado).
   - Apache Tomcat 9 o 10 (puerto 8080 por defecto).
   - Maven 3.8+.

2. Compilar:
   - mvn clean package
   - Genera target/dev-profile.war.

3. Desplegar:
   - Copia dev-profile.war a TOMCAT_HOME/webapps/.
   - Inicia Tomcat.
   - URL: http://localhost:8080/dev-profile/.

4. Datos:
   - La app creará data i no existe .
   - profile.txt y skills.txt se inicializan si están vacíos.

5. Subida de fotos:
   - A través del formulario en profile.jsp.
   - Se guarda en uploads dentro de la app.
   - La ruta se referencia en profile.txt.


