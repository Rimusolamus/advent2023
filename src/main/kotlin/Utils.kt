object Utils {
    fun getResourceAsListOfLines(path: String) : List<String> {
        return object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.readLines() ?: emptyList()
    }
}