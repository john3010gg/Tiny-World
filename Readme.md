Universidad Simón Bolívar
CI-2693 – Laboratorio de Algoritmos y Estructuras III
Enero-Marzo 2026

Integrantes:
Nazareth Colmenares - 20-10017
John Garrido - 20-10293

Este proyecto implementa un algoritmo en Kotlin que ayuda a los jugadores del JCC (Juego de Cartas Coleccionables) 
"Duelo de cartas de mostro" a encontrar todas las posibles combinaciones de cartas mostro (ternas) que satisfacen las 
condiciones de la carta conjuro Mundo Chiquito.

La estructura principal utiliza un grafo no dirigido implementado con MutableMap(Carta Mostro) donde 
cada vértice (carta) se asocia a una lista de sus vecinos (cartas con las que comparte exactamente una característica).

Complejidad Total del Algoritmo:

Tiempo: O(V^3) en el peor caso, donde V = número de cartas

Espacio: O(V^2) para almacenar el grafo (cuando todas las cartas están conectadas)

Decisiones de Implementación:

Estructura de Datos Principal:

Se eligió un MutableMap(CartaMostro, MutableList(CartaMostro)) para representar el grafo no dirigido porque permite acceso O(1) a la lista 
de vecinos de cada carta, facilita la verificación de existencia de vértices y se integra naturalmente con las características de 
nulabilidad de Kotlin. Al ser un grafo no dirigido, al agregar una arista entre dos cartas, se añade cada carta a la lista de vecinos de la otra, manteniendo la simetría de las conexiones.

Modelado de Cartas:

La clase CartaMostro encapsula las propiedades de las cartas con validaciones en el bloque init para garantizar que los objetos creados cumplan siempre con las reglas del juego: nivel entre 1 y 12, poder múltiplo de 50 y atributo dentro de los valores permitidos.

Comparación de Características:

El método comparteExactamenteUnaCaracteristica implementa un contador de coincidencias que compara nivel, atributo y poder, retornando true solo cuando   exactamente una de estas propiedades es igual entre dos cartas.

Algoritmo de Búsqueda:

El algoritmo sigue la definición de Mundo Chiquito: para cada carta como posible punto de partida, se exploran sus vecinos (cartas que comparten una característica) y, para cada vecino, se exploran sus propios vecinos, verificando que las tres cartas sean distintas y que se cumplan las condiciones de conexión entre la primera y segunda, y entre la segunda y tercera.

