package com.test.testintact.extensions

import org.junit.Assert
import org.junit.Test

class UnitConverterTest {

    @Test
    fun `Centimeter to inch`() {
        val centimeters = 10f
        val inches = centimeters.centimeterToInch()
        val inchesString = String.format("%.2f", inches)
        Assert.assertEquals("3.94", inchesString)
    }
}