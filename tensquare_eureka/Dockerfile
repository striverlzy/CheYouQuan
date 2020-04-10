FROM java
ARG JAR_FILE
COPY ${JAR_FILE} /usr/java/project/eureka.jar
EXPOSE 6868
ENTRYPOINT ["java","-jar","/usr/java/project/eureka.jar"]
