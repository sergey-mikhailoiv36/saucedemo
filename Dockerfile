# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем в контейнер файлы сборщика Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Даём права на запуск скрипта и собираем проект (скачиваем зависимости)
RUN chmod +x gradlew && ./gradlew build -x test

# Копируем исходный код и тесты
COPY src src

# Команда по умолчанию для запуска тестов (можно переопределить в docker-compose)
CMD ["./gradlew", "clean", "test"]