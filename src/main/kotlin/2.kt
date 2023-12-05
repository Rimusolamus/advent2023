private const val input = "2.txt"

fun init2(): String {
    val listOfGames: List<CubeGame> = Utils.getResourceAsListOfLines(input).map { parseInputLine(it) }
    return listOfGames.sumOf { game ->
        val maxGreen =
            game.roundList.filter { it.cubeColor == CubeColor.green }.maxByOrNull { it.cubeNumber }?.cubeNumber ?: 1
        val maxRed =
            game.roundList.filter { it.cubeColor == CubeColor.red }.maxByOrNull { it.cubeNumber }?.cubeNumber ?: 1
        val maxBlue =
            game.roundList.filter { it.cubeColor == CubeColor.blue }.maxByOrNull { it.cubeNumber }?.cubeNumber ?: 1
        maxGreen * maxRed * maxBlue
    }.toString()
}

private fun parseInputLine(line: String): CubeGame {
    val id = line.substringBefore(":").split(" ")[1].toInt()
    val rounds = line.substringAfter(":").split(";").flatMap { parseRound(it.trim()) }
    return CubeGame(id, rounds)
}

private fun parseRound(game: String): List<Round> {
    return if (game.split(",").isNotEmpty()) {
        game.split(",").map { round ->
            round.trim().split(" ").let { Round(enumValueOf<CubeColor>(it[1]), it[0].toInt()) }
        }
    } else {
        listOf(Round(enumValueOf<CubeColor>(game.substringAfter(" ")), game.substringBefore(" ").toInt()))
    }
}

// we have known 100 games
private data class CubeGame(
    val id: Int,
    val roundList: List<Round>,
    var isFair: Boolean = true
)

// can be 1 to n rounds
private data class Round(
    val cubeColor: CubeColor,
    val cubeNumber: Int
)

private enum class CubeColor {
    green,
    red,
    blue
}