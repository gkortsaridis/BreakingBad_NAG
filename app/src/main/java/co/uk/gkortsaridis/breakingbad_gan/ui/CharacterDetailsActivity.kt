package co.uk.gkortsaridis.breakingbad_gan.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.uk.gkortsaridis.breakingbad_gan.R
import co.uk.gkortsaridis.breakingbad_gan.entities.BreakingBadCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_details.*
import java.lang.StringBuilder

class CharacterDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val character = intent.getSerializableExtra("CHARACTER") as BreakingBadCharacter

        val occupationSB = StringBuilder()
        for(occupation in character.occupation) {
            occupationSB.append(", ")
            occupationSB.append(occupation)
        }

        val seasonsSB = StringBuilder()
        for(season in character.appearance) {
            seasonsSB.append(", ")
            seasonsSB.append(season)
        }

        Picasso.get().load(character.img).into(character_img)
        character_name.text = character.name
        character_nickname.text = "A.k.a ${character.nickname}"
        character_occupation.text = "${occupationSB.toString().substring(2)}"
        character_status.text = "${character.status}"
        character_seasons.text = "${seasonsSB.toString().substring(2)}"
    }
}