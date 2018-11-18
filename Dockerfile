FROM java:8-alpine

COPY target/uberjar/valeros.jar /valeros/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/valeros/app.jar"]
