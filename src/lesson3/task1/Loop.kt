@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0) return 1
    var c = 0
    var num = n
    while (num != 0) {
        c++
        num /= 10
    }
    return c
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2) return 1
    var n1 = 1
    var n2 = 1
    var count = 2
    while (count != n) {
        count++
        if (n1 <= n2) n1 += n2
        else n2 += n1
    }
    return max(n1, n2)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var m1 = m
    var n1 = n
    while (m1 != 0 && n1 != 0) {
        if (m1 >= n1) m1 %= n1
        else n1 %= m1
    }
    return (m * n) / max(m1, n1)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    val n1 = sqrt(n.toDouble())
    for (i in 2..n1.toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = m * n / lcm(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    return if (sqrt(m.toDouble()) % 1.0 == 0.0 || sqrt(n.toDouble()) % 1.0 == 0.0) true
    else (sqrt(m.toDouble()).toInt() < sqrt(n.toDouble()).toInt())
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var x1 = x
    while (x1 > 1) {
        count++
        if (x1 % 2 == 0) x1 /= 2
        else x1 = 3 * x1 + 1
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутойх, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой содимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var sign = 0
    var lul = 3
    var num = x
    if (num > 2 * PI || num < -2 * PI) num -= (num / (2 * PI)) * (2 * PI)
    var delta = num
    while (delta >= eps) {
        if (sign % 2 == 0) num -= x.pow(lul) / factorial(lul)
        else num += x.pow(lul) / factorial(lul)
        delta = x.pow(lul) / factorial(lul)
        lul += 2
        sign++
    }
    return num
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var sign = 0
    var lul = 2
    var num = 1.0
    var x1 = x
    if (x1 > 2 * PI || x1 < -2 * PI) x1 -= (x1 / (2 * PI)) * (2 * PI)
    var delta = num
    while (delta >= eps) {
        if (sign % 2 == 0) num -= x1.pow(lul) / factorial(lul)
        else num += x1.pow(lul) / factorial(lul)
        delta = x1.pow(lul) / factorial(lul)
        lul += 2
        sign++
    }
    return num
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Long): Long {
    var num = n
    var c = 0
    var res = 0.0
    while (num > 0) {
        num /= 10
        c++
    }
    num = n
    while (num > 0) {
        res += (num % 10) * 10.0.pow(c - 1)
        c--
        num /= 10
    }
    return res.toLong()
}


/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Long): Boolean {
    val x = revert(n)
    println(x)
    return n == x
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var d = n / 10
    var x = n % 10
    while (d > 0) {
        if (d % 10 != x) return true
        x = d % 10
        d /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var num = 1
    var count = 0
    var sq = 1
    var deg = 1.0
    while (count < n) {
        num = sq * sq
        if (num / (10.0.pow(deg)).toInt() == 0) count += deg.toInt()
        else {
            deg += 1
            count += deg.toInt()
        }
        sq++
    }
    return (num / 10.0.pow(count - n).toInt()) % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var num = 1
    var count = 0
    var sq = 1
    var deg = 1.0
    while (count < n) {
        num = fib(sq)
        if (num / (10.0.pow(deg)).toInt() == 0) count += deg.toInt()
        else {
            deg += 1
            count += deg.toInt()
        }
        sq++
    }
    return (num / 10.0.pow(count - n).toInt()) % 10
}
