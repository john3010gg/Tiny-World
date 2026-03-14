import java.io.File

class MundoChiquito {
    private val grafo = Grafo()
    private val cartas: MutableList<CartaMostro> = mutableListOf()
    private val ternasEncontradas: MutableSet<Triple<CartaMostro, CartaMostro, CartaMostro>> = mutableSetOf()

    fun leerArchivoCSV(nombreArchivo: String) {
        try {
            val lineas = File(nombreArchivo).readLines()
            
            val primeraLinea = lineas.firstOrNull()
            val inicio = if (primeraLinea?.startsWith("Nombre") == true) 1 else 0
            
            for (i in inicio until lineas.size) {
                val linea = lineas[i].trim()
                if (linea.isNotEmpty()) {
                    val partes = linea.split(",")
                    if (partes.size >= 4) {
                        try {
                            val nombre = partes[0].trim()
                            val nivel = partes[1].trim().toInt()
                            val atributoStr = partes[2].trim().uppercase()
                            val poder = partes[3].trim().toInt()
                            
                            val atributo = when (atributoStr) {
                                "AGUA" -> Atributo.AGUA
                                "FUEGO" -> Atributo.FUEGO
                                "VIENTO" -> Atributo.VIENTO
                                "TIERRA" -> Atributo.TIERRA
                                "LUZ" -> Atributo.LUZ
                                "OSCURIDAD" -> Atributo.OSCURIDAD
                                "DIVINO" -> Atributo.DIVINO
                                else -> throw IllegalArgumentException("Atributo inválido: $atributoStr")
                            }
                            
                            val carta = CartaMostro(nombre, nivel, atributo, poder)
                            cartas.add(carta)
                        } catch (e: Exception) {
                            println("Error al procesar línea: $linea - ${e.message}")
                        }
                    }
                }
            }
            
            println("Se cargaron ${cartas.size} cartas correctamente")
            
        } catch (e: Exception) {
            println("Error al leer el archivo: ${e.message}")
        }
    }

    private fun encontrarTernasParaCartaInicial(cartaInicial: CartaMostro) {
        val vecinosInicial = grafo.obtenerVecinos(cartaInicial)
        
        for (cartaIntermedia in vecinosInicial) {
            val vecinosIntermedia = grafo.obtenerVecinos(cartaIntermedia)
            
            for (cartaFinal in vecinosIntermedia) {
                if (cartaInicial != cartaIntermedia && 
                    cartaInicial != cartaFinal && 
                    cartaIntermedia != cartaFinal) {
                    
                    if (cartaInicial.comparteExactamenteUnaCaracteristica(cartaIntermedia) &&
                        cartaIntermedia.comparteExactamenteUnaCaracteristica(cartaFinal)) {
                        
                        val terna = Triple(cartaInicial, cartaIntermedia, cartaFinal)
                        ternasEncontradas.add(terna)
                    }
                }
            }
        }
    }

    fun encontrarTodasLasTernas() {
        grafo.construirGrafo(cartas)
        cartas.forEach { carta ->
            encontrarTernasParaCartaInicial(carta)
        }
    }

    fun imprimirTernas() {
        if (ternasEncontradas.isEmpty()) {
            println("No se encontraron ternas que cumplan las condiciones")
            return
        }
        
        println("\nTernas encontradas (carta1 carta2 carta3):")
        println("")
        
        ternasEncontradas.forEach { (carta1, carta2, carta3) ->
            println("${carta1.getNombre()} ${carta2.getNombre()} ${carta3.getNombre()}")
        }
        
        println("")
        println("Total de ternas encontradas: ${ternasEncontradas.size}")
    }

    fun imprimirEstadisticas() {
        println("\nESTADÍSTICAS")
        println("Total de cartas cargadas: ${cartas.size}")
        
        println("\nCartas en el sistema:")
        cartas.forEachIndexed { index, carta ->
            println("${index + 1}. ${carta.getNombre()} - Nivel: ${carta.getNivel()}, " +
                    "Atributo: ${carta.getAtributo()}, Poder: ${carta.getPoder()}")
        }
        
        // Mostrar conexiones en el grafo
        println("\nConexiones en el grafo:")
        var hayConexiones = false
        
        for (carta in cartas) {
            val vecinos = grafo.obtenerVecinos(carta)
            if (vecinos.isNotEmpty()) {
                hayConexiones = true
                val nombresVecinos = vecinos.joinToString { it.getNombre() }
                println("  ${carta.getNombre()} → $nombresVecinos")
            }
        }
        
        if (!hayConexiones) {
            println("  No hay conexiones entre las cartas")
        }
    }
}

fun main() {
    println("MUNDO CHIQUITO")
    
    val buscador = MundoChiquito()
    val nombreArchivo = "deck.csv"
    
    // Leer el archivo
    buscador.leerArchivoCSV(nombreArchivo)
    

    println("\nBUSCANDO TERNAS")
    buscador.encontrarTodasLasTernas()
    
    buscador.imprimirEstadisticas()
    
    // Mostrar resultados
    buscador.imprimirTernas()
}