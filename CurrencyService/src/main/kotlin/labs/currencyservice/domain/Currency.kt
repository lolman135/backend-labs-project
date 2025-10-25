package labs.currencyservice.domain

import java.util.UUID

data class Currency(
    val id: UUID,
    val code: String,
    val name: String,
    val symbol: String
) {
    fun changeCode(newCode: String): Currency{
        require(newCode.isNotBlank()){"Code cannot be empty"}
        return copy(code = newCode)
    }

    fun rename(newName: String): Currency{
        require(newName.isNotBlank()){"Name cannot be empty"}
        return copy(name = newName)
    }

    fun changeSymbol(newSymbol: String): Currency{
        require(newSymbol.isNotBlank()){"Symbol cannot be empty"}
        return copy(symbol = newSymbol)
    }
}
