import Utils.indexesOf

private const val input = "1.txt"

private const val ONE = "one"
private const val TWO = "two"
private const val THREE = "three"
private const val FOUR = "four"
private const val FIVE = "five"
private const val SIX = "six"
private const val SEVEN = "seven"
private const val EIGHT = "eight"
private const val NINE = "nine"

fun init(): String {
    val allNumbers: MutableList<Int> = mutableListOf()
    for (line in Utils.getResourceAsListOfLines(input)) {
        val lineWithReplacedStringNumber = replaceStringNumbersWithInts(line)
        val first: String = lineWithReplacedStringNumber.first {
            it.isDigit()
        }.toString()
        val last: String = lineWithReplacedStringNumber.last {
            it.isDigit()
        }.toString()
        println(first + last)
        allNumbers.add((first + last).toInt())
    }

    return allNumbers.sum().toString()
}

private fun replaceStringNumbersWithInts(line: String): String {
    var modifiedLine = line
    findFirstOrLastStringNumberInLine(modifiedLine, true)?.let { firstNumber ->
        modifiedLine.find { it.isDigit() }?.let {
            if (modifiedLine.indexOf(it) < firstNumber.indexInLine) {
                modifiedLine = handleLastNumber(modifiedLine)
            } else {
                modifiedLine = removeNumberFromLine(modifiedLine, firstNumber)
                modifiedLine = handleLastNumber(modifiedLine)
            }
        }
    }

    return modifiedLine
}

private fun handleLastNumber(line: String): String {
    var modifiedLine = line
    findFirstOrLastStringNumberInLine(modifiedLine, false)?.let { secondNumber ->
        modifiedLine = removeNumberFromLine(modifiedLine, secondNumber)
    }
    return modifiedLine
}

private fun removeNumberFromLine(line: String, number: StringNumber): String {
    return line.replaceRange(number.indexInLine, number.indexInLine + number.number.length, number.toRealNumber())
}

private fun StringNumber.toRealNumber(): String {
    return when (this.number) {
        ONE -> "1"
        TWO -> "2"
        THREE -> "3"
        FOUR -> "4"
        FIVE -> "5"
        SIX -> "6"
        SEVEN -> "7"
        EIGHT -> "8"
        NINE -> "9"
        else -> "0"
    }
}

private fun findFirstOrLastStringNumberInLine(line: String, getFirst: Boolean): StringNumber? {
    val stringNumbers = mutableListOf<StringNumber>()
    val numbers = listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE)
    numbers.map { number ->
        line.indexesOf(number).map { index ->
            stringNumbers.add(StringNumber(number, index))
        }
    }
    return if (getFirst) stringNumbers.minByOrNull { it.indexInLine } else stringNumbers.maxByOrNull { it.indexInLine }
}

private data class StringNumber(val number: String, val indexInLine: Int)