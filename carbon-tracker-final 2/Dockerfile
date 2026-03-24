# ============================================================
# Stage 1 - Build: compila o projeto com Maven
# ============================================================
FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o pom.xml primeiro para aproveitar cache de dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests -B

# ============================================================
# Stage 2 - Runtime: imagem enxuta apenas para executar
# ============================================================
FROM eclipse-temurin:17-jre-alpine

# Cria usuário não-root por segurança
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copia apenas o JAR gerado no build
COPY --from=build /app/target/carbon-tracker.jar app.jar

# Define o usuário não-root
USER appuser

# Expõe a porta da aplicação
EXPOSE 8080

# Health check nativo do Docker
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# Entrypoint otimizado para container
ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-Djava.security.egd=file:/dev/./urandom", \
  "-jar", "app.jar"]
