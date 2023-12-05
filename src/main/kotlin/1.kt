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
    return Utils.getResourceAsListOfLines(input).sumOf { line ->
        val lineWithReplacedStringNumber = replaceStringNumbersWithInts(line)
        (lineWithReplacedStringNumber.first {
            it.isDigit()
        }.toString() + lineWithReplacedStringNumber.last {
            it.isDigit()
        }.toString()).toInt()
    }.toString()
}

private fun replaceStringNumbersWithInts(line: String): String {
    var modifiedLine = line
    findFirstOrLastStringNumberInLine(line, true)?.let { firstNumber ->
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
    findFirstOrLastStringNumberInLine(line, false)?.let { secondNumber ->
        return removeNumberFromLine(line, secondNumber)
    }
    return line
}

private fun removeNumberFromLine(line: String, number: StringNumber): String {
    return line.replaceRange(number.indexInLine, number.indexInLine + number.number.length, number.toRealNumber())
}

private fun findFirstOrLastStringNumberInLine(line: String, getFirst: Boolean): StringNumber? {
    val numbers = listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE)
    val stringNumbers: List<StringNumber> = numbers.flatMap { number ->
        line.indexesOf(number).map { index ->
            return@map StringNumber(number, index)
        }
    }
    return if (getFirst) stringNumbers.minByOrNull { it.indexInLine } else stringNumbers.maxByOrNull { it.indexInLine }
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

private data class StringNumber(val number: String, val indexInLine: Int)