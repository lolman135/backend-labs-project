package labs.currencyservice.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class CurrencyTest {

    @Test
    fun `should correctly update currency fields`() {
        val currency = Currency(UUID.randomUUID(), "USD", "Dollar", "$")

        val updated = currency.rename("US Dollar").changeCode("USDT").changeSymbol("$$")

        assertThat(updated.name).isEqualTo("US Dollar")
        assertThat(updated.code).isEqualTo("USDT")
        assertThat(updated.symbol).isEqualTo("$$")
    }

    @Test
    fun `should throw exception when fields are blank`() {
        val currency = Currency(UUID.randomUUID(), "USD", "Dollar", "$")

        assertThrows<IllegalArgumentException> { currency.rename("") }
        assertThrows<IllegalArgumentException> { currency.changeCode(" ") }
        assertThrows<IllegalArgumentException> { currency.changeSymbol("  ") }
    }
}