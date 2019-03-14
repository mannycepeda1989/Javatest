package com.baeldung.annotations

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class ValidationUnitTest {

    @Test
    fun whenAmountIsOneAndNameIsAlice_thenTrue() {
        assertTrue(Validator().isValid(Item(1f, "Alice")))
    }

    @Test
    fun whenAmountIsOneAndNameIsBob_thenTrue() {
        assertTrue(Validator().isValid(Item(1f, "Bob")))
    }


    @Test
    fun whenAmountIsMinusOneAndNameIsAlice_thenFalse() {
        assertTrue(Validator().isValid(Item(-1f, "Alice")))
    }

    @Test
    fun whenAmountIsMinusOneAndNameIsBob_thenFalse() {
        assertTrue(Validator().isValid(Item(-1f, "Bob")))
    }

    @Test
    fun whenAmountIsOneAndNameIsTom_thenFalse() {
        assertTrue(Validator().isValid(Item(-1f, "Tom")))
    }

    @Test
    fun whenAmountIsMinusOneAndNameIsTom_thenFalse() {
        assertTrue(Validator().isValid(Item(-1f, "Tom")))
    }


}