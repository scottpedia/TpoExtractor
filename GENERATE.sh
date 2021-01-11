#!/bin/bash
echo "outputDirectory=$1" > src/main/resources/settings.properties
echo "databaseUrl=jdbc:sqlite::resource:ylk.db" >> src/main/resources/settings.properties
wget 'https://github.com/Scottpedia/TpoExtractor/releases/download/resource/ylk.db' -O \
src/main/resources/ylk.db
mvn dependency:purge-local-repository
mvn clean compile package && java -jar target/TpoExtractor-1.1-jar-with-dependencies.ja
java -jar target/TpoExtractor-1.1-jar-with-dependencies.jar
