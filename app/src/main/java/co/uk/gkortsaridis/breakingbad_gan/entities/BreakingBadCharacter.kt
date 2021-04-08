package co.uk.gkortsaridis.breakingbad_gan.entities

import java.io.Serializable

data class BreakingBadCharacter(
    val char_id: Int,
    val name: String,
    val birthday: String,
    val occupation: ArrayList<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: ArrayList<Int>,
    val portrayed: String,
    val category: String,
    val better_call_saul_appearances: ArrayList<Int>
): Serializable