enum class Atributo {
    AGUA, FUEGO, VIENTO, TIERRA, LUZ, OSCURIDAD, DIVINO
}

class CartaMostro(
    private val nombre: String,
    private val nivel: Int,
    private val atributo: Atributo,
    private val poder: Int
) {
    init {
        require(nivel in 1..12) { "El nivel debe estar entre 1 y 12" }
        require(poder % 50 == 0) { "El poder debe ser múltiplo de 50" }
        require(poder > 0) { "El poder debe ser positivo" }
    }

    fun getNombre(): String = nombre
    fun getNivel(): Int = nivel
    fun getAtributo(): Atributo = atributo
    fun getPoder(): Int = poder

    fun comparteExactamenteUnaCaracteristica(otra: CartaMostro): Boolean {
        var coincidencias = 0
        
        if (this.nivel == otra.nivel) coincidencias++
        if (this.atributo == otra.atributo) coincidencias++
        if (this.poder == otra.poder) coincidencias++
        
        return coincidencias == 1
    }

    override fun toString(): String {
        return "CartaMostro(nombre='$nombre', nivel=$nivel, atributo=$atributo, poder=$poder)"
    }
}