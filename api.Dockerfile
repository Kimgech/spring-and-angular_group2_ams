FROM eclipse-temurin:17.0.3_7-jre-alpine
ADD ./build/libs/spring-and-angular_group2_ams-0.0.1-SNAPSHOT.jar root.jar
ENTRYPOINT ["java"]
CMD ["-jar", "root.jar"]