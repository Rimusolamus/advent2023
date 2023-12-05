private const val input = "2.txt"

private const val maxForRed = 12
private const val maxForGreen = 13
private const val maxForBlue = 14

fun init2(): String {
    val listOfGames: List<CubeGame> = Utils.getResourceAsListOfLines(input).map { parseInputLine(it) }
    return listOfGames.sumOf { game ->
        game.roundList.map { round ->
            when (round.cubeColor) {
                CubeColor.red -> {
                    if (round.cubeNumber > maxForRed) {
                        game.isFair = false
                    }
                }

                CubeColor.green -> {
                    if (round.cubeNumber > maxForGreen) {
                        game.isFair = false
                    }
                }

                CubeColor.blue -> {
                    if (round.cubeNumber > maxForBlue) {
                        game.isFair = false
                    }
                }
            }
        }
        if (game.isFair)
            game.id
        else 0
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