package co.uk.gkortsaridis.breakingbad_gan.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.uk.gkortsaridis.breakingbad_gan.R
import co.uk.gkortsaridis.breakingbad_gan.entities.BreakingBadCharacter
import co.uk.gkortsaridis.breakingbad_gan.interfaces.CharacterClickListener
import com.squareup.picasso.Picasso

class CharactersRecyclerViewAdapter(
    private var items: ArrayList<BreakingBadCharacter> = arrayListOf(),
    private val context: Context,
    private var listener: CharacterClickListener? = null
): RecyclerView.Adapter<CharactersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(context).inflate(
            R.layout.character_rv_list_item,
            parent,
            false
        )
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(context, items[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var characterName: TextView? = null
        private var characterImg: ImageView? = null
        private var container: RelativeLayout? = null

        init {
            characterName = itemView.findViewById(R.id.character_name)
            characterImg = itemView.findViewById(R.id.character_img)
            container = itemView.findViewById(R.id.container)
        }

        fun bindItems(context: Context, character: BreakingBadCharacter, listener: CharacterClickListener?) {
            Picasso.get().load(character.img).into(characterImg)
            characterName?.text = character.name

            container?.setOnClickListener { listener?.onCharacterClicked(character, characterImg) }
        }
    }

    fun updateItems(characters: ArrayList<BreakingBadCharacter>) {
        this.items = characters
        notifyDataSetChanged()
    }
}