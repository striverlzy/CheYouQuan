FROM java
ARG JAR_FILE
COPY ${JAR_FILE} /usr/java/project/gateway.jar
EXPOSE 9012
ENTRYPOINT ["java","-jar","/usr/java/project/gateway.jar"]
