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
        allNumbers.add((first + last).toInt())
    }

    return allNumbers.sum().toString()
}

private fun replaceStringNumbersWithInts(line: String): String {
    var modifiedLine = line
    findFirstOrLastStringNumberInLine(line, true)?.let {
        modifiedLine = line.replaceFirst(it.number, it.stringNumber)
    }
    findFirstOrLastStringNumberInLine(line, false)?.let {
        modifiedLine = modifiedLine.reversed().replaceFirst(it.number.reversed(), it.stringNumber).reversed()
    }
    return modifiedLine
}

private fun findFirstOrLastStringNumberInLine(line: String, shouldFindFirst: Boolean): StringNumber? {
    val stringNumbers = mutableListOf<StringNumber>()
    if (line.contains(ONE)) {
        stringNumbers.add(StringNumber(ONE, "1", line.indexOf(ONE)))
    }
    if (line.contains(TWO)) {
        stringNumbers.add(StringNumber(TWO, "2", line.indexOf(TWO)))
    }
    if (line.contains(THREE)) {
        stringNumbers.add(StringNumber(THREE, "3", line.indexOf(THREE)))
    }
    if (line.contains(FOUR)) {
        stringNumbers.add(StringNumber(FOUR, "4", line.indexOf(FOUR)))
    }
    if (line.contains(FIVE)) {
        stringNumbers.add(StringNumber(FIVE, "5", line.indexOf(FIVE)))
    }
    if (line.contains(SIX)) {
        stringNumbers.add(StringNumber(SIX, "6", line.indexOf(SIX)))
    }
    if (line.contains(SEVEN)) {
        stringNumbers.add(StringNumber(SEVEN, "7", line.indexOf(SEVEN)))
    }
    if (line.contains(EIGHT)) {
        stringNumbers.add(StringNumber(EIGHT, "8", line.indexOf(EIGHT)))
    }
    if (line.contains(NINE)) {
        stringNumbers.add(StringNumber(NINE, "9", line.indexOf(NINE)))
    }
    return if (shouldFindFirst) stringNumbers.minByOrNull { it.indexInLine } else stringNumbers.maxByOrNull { it.indexInLine }
}

private data class StringNumber(val number: String, val stringNumber: String, val indexInLine: Int)