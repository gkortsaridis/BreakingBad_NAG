package co.uk.gkortsaridis.breakingbad_gan.interfaces

import android.widget.ImageView
import android.widget.TextView
import co.uk.gkortsaridis.breakingbad_gan.entities.BreakingBadCharacter

interface CharacterClickListener {
    fun onCharacterClicked(character: BreakingBadCharacter, characterImageView: ImageView?)
}