@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */

fun daysInMonth(month: Int, year: Int): Int = when {
    month == 2 && (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) -> 29
    month == 2 -> 28
    month == 4 || month == 6 || month == 9 || month == 11 -> 30
    else -> 31
}

fun dateStrToDigit(str: String): String {
    val parts = str.split(" ").toMutableList()
    if (parts.size != 3) return ""
    try {
        if (Regex("""(\d{2}|\d)""").find(parts[0]) == null) return ""
        if (Regex("""([а-я])""").find(parts[1]) == null) return ""
        if (Regex("""(^0123456789)""").find(parts[2]) != null) return ""
    } catch (e: IndexOutOfBoundsException) {
        return ""
    }
    val day = parts[0].toInt()
    val mounth = parts[1]
    if (parts[1] == "января" && day <= 31) parts[1] = "01"
    if (parts[1] == "февраля" && day <= daysInMonth(2, parts[2].toInt())) parts[1] = "02"
    if (parts[1] == "марта" && day <= 31) parts[1] = "03"
    if (parts[1] == "апреля" && day <= 30) parts[1] = "04"
    if (parts[1] == "мая" && day <= 31) parts[1] = "05"
    if (parts[1] == "июня" && day <= 30) parts[1] = "06"
    if (parts[1] == "июля" && day <= 31) parts[1] = "07"
    if (parts[1] == "августа" && day <= 31) parts[1] = "08"
    if (parts[1] == "сентября" && day <= 30) parts[1] = "09"
    if (parts[1] == "октября" && day <= 31) parts[1] = "10"
    if (parts[1] == "ноября" && day <= 30) parts[1] = "11"
    if (parts[1] == "декабря" && day <= 31) parts[1] = "12"
    if (mounth == parts[1]) return ""
    if (parts[0].length < 2) parts[0] = "0" + parts[0]
    return String.format("%s.%s.%s", parts[0], parts[1], parts[2])
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".").toMutableList()
    if (parts.size != 3) return ""
    if (Regex("""(\d{2})""").find(parts[0]) == null) return ""
    if (Regex("""(\d{2})""").find(parts[1]) == null) return ""
    if (Regex("""([^0123456789])""").find(parts[2]) != null) return ""
    if (parts[0].first() == '0') parts[0] = parts[0].substring(1)
    val day = parts[0].toInt()
    val mounth = parts[1]
    if (mounth == "01" && day <= 31) parts[1] = "января"
    if (mounth == "02" && day <= daysInMonth(2, parts[2].toInt())) parts[1] = "февраля"
    if (mounth == "03" && day <= 31) parts[1] = "марта"
    if (mounth == "04" && day <= 30) parts[1] = "апреля"
    if (mounth == "05" && day <= 31) parts[1] = "мая"
    if (mounth == "06" && day <= 30) parts[1] = "июня"
    if (mounth == "07" && day <= 31) parts[1] = "июля"
    if (mounth == "08" && day <= 31) parts[1] = "августа"
    if (mounth == "09" && day <= 30) parts[1] = "сентября"
    if (mounth == "10" && day <= 31) parts[1] = "октября"
    if (mounth == "11" && day <= 30) parts[1] = "ноября"
    if (mounth == "12" && day <= 31) parts[1] = "декабря"
    if (mounth == parts[1]) return ""
    return String.format("%s %s %s", parts[0], parts[1], parts[2])
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    if (Regex("""([^0123456789\- +()])""").find(phone) != null) return ""
    if (Regex("""(\(\))""").find(phone) != null) return ""
    if (Regex("""[0-9]""").find(phone) == null) return ""
    var answ = ""
    for (i in phone.indices) {
        if (phone[i] == '+' && answ.isEmpty()) answ += "+"
        if (Regex("""([0-9])""").find(phone[i].toString()) != null) answ += phone[i]
    }
    return answ
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val parts = jumps.split(" ").toList()
    var max = -1
    for (i in parts.indices) {
        var num = -1
        try {
            num = parts[i].toInt()
        } catch (e: NumberFormatException) {
            if (Regex("""([%\-])""").find(parts[i]) == null) return -1
        }
        if (num > max) max = num
    }
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int = TODO()

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val parts = expression.split(" ").toList()
    if (expression.isEmpty()
        || Regex("""([^-+0123456789\s])""").find(expression) != null) throw IllegalArgumentException()
    var res = 0
    var f = false
    for (i in parts.indices) {
        if (Regex("""([-])""").find(parts[i]) != null
            && Regex("""([^-])""").find(parts[i]) != null) throw IllegalArgumentException()
        if (Regex("""([+])""").find(parts[i]) != null
            && Regex("""([^+])""").find(parts[i]) != null) throw IllegalArgumentException()
        if (Regex("""([0123456789])""").find(parts[i]) != null
            && Regex("""([^0123456789])""").find(parts[i]) != null) throw IllegalArgumentException()
        if (i == 0) {
            res += parts[i].toInt()
            f = true
            continue
        }
        if (parts[i] == "+" || parts[i] == "-") {
            if (!f) throw IllegalArgumentException()
            else f = false
        } else {
            if (f) throw IllegalArgumentException()
            else {
                if (parts[i - 1] == "-") res -= parts[i].toInt()
                if (parts[i - 1] == "+") res += parts[i].toInt()
                f = true
            }
        }
    }
    return res
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
