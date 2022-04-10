FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
EXPOSE 8181
ADD application/target/*.jar app.jar
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
ENTRYPOINT ["java","-jar","app.jar"]