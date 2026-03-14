JAR_FILE = MundoChiquito.jar

SOURCES = CartaMostro.kt Grafo.kt MundoChiquito.kt

all: compile run

compile:
	@echo "Compilando el proyecto Mundo Chiquito..."
	kotlinc $(SOURCES) -include-runtime -d $(JAR_FILE)
	@echo "Compilación completada. Archivo generado: $(JAR_FILE)"

run:
	@echo "Ejecutando Mundo Chiquito..."
	@echo "Asegúrate de que el archivo deck.csv existe en el directorio actual"
	java -jar $(JAR_FILE)

clean:
	@echo "Limpiando archivos generados..."
	rm -f $(JAR_FILE)
	@echo "Limpieza completada."
