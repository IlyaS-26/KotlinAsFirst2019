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
    constructor(s: String) : this(
        Regex("""-?\d+(\.\d+)?""").findAll(s).elementAt(0).value.toDouble(),
        Regex("""-?\d+(\.\d+)?""").findAll(s).elementAt(1).value.toDouble()
    )

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

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        if (im < 0) return "$re${im}i"
        if (im > 0) return "$re+${im}i"
        if (im == 0.0) return "$re"
        if (re == 0.0) return "${im}i"
        return "$re"
    }
}