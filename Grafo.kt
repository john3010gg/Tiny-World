class Grafo {
    private val adyacencias: MutableMap<CartaMostro, MutableList<CartaMostro>> = mutableMapOf()

    fun agregarVertice(carta: CartaMostro) {
        if (!adyacencias.containsKey(carta)) {
            adyacencias[carta] = mutableListOf()
        }
    }

    fun agregarArista(carta1: CartaMostro, carta2: CartaMostro) {
        if (carta1.comparteExactamenteUnaCaracteristica(carta2)) {
            adyacencias[carta1]?.add(carta2)
            adyacencias[carta2]?.add(carta1)
        }
    }

    fun construirGrafo(cartas: List<CartaMostro>) {
        cartas.forEach { agregarVertice(it) }

        for (i in cartas.indices) {
            for (j in i + 1 until cartas.size) {
                agregarArista(cartas[i], cartas[j])
            }
        }
    }

    fun obtenerVecinos(carta: CartaMostro): List<CartaMostro> {
        return adyacencias[carta] ?: emptyList()
    }

    fun obtenerVertices(): Set<CartaMostro> = adyacencias.keys
}