FROM eclipse-temurin
RUN mkdir /opt/app
COPY ./conference-partner-0.0.1-SNAPSHOT.jar /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/conference-partner-0.0.1-SNAPSHOT.jar"]