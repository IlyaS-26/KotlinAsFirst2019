@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {
    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    companion object MyObject {
        private fun realPart(p: String): Double {
            if (!p.matches(Regex("""-?\d+(\.\d+)?[-+]?\d+(\.\d+)?i"""))) throw IllegalArgumentException()
            return Regex("""-?\d+(\.\d+)?""").findAll(p).elementAt(0).value.toDouble()
        }

        private fun imaginaryPart(p: String): Double {
            if (!p.matches(Regex("""-?\d+(\.\d+)?[-+]?\d+(\.\d+)?i"""))) throw IllegalArgumentException()
            return Regex("""-?\d+(\.\d+)?""").findAll(p).elementAt(1).value.toDouble()
        }
    }

    constructor(s: String) : this(realPart(s), imaginaryPart(s))

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(re.unaryMinus(), im.unaryMinus())

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(
        re.times(other.re) - im.times(other.im),
        re.times(other.im) + im.times(other.re)
    )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re.times(other.re) + im.times(other.im)) / (sqr(other.re) + sqr(other.im)),
        (other.re.times(im) - re.times(other.im)) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && im == other.im && re == other.re

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }


    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        return when {
            im == 0.0 -> "$re"
            re == 0.0 -> "${im}i"
            im < 0 -> "$re${im}i"
            im > 0 -> "$re+${im}i"
            else -> "$re"
        }
    }
}