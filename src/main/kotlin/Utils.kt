object Utils {
    fun getResourceAsListOfLines(path: String) : List<String> {
        return object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.readLines() ?: emptyList()
    }
    fun String.indexesOf(substr: String, ignoreCase: Boolean = true) : List<Int> =
        (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
            .findAll(this).map { it.range.first }.toList()
}