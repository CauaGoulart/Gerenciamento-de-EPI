# Usar uma imagem base do Maven para construir o aplicativo
FROM maven:3.8.6-jdk-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o pom.xml e o código fonte
COPY pom.xml .
COPY src ./src

# Compila o aplicativo
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK para rodar o aplicativo
FROM openjdk:17-jre-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR construído
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

#expor a porta para executar a aplicação
EXPOSE 8080

# Define o comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
