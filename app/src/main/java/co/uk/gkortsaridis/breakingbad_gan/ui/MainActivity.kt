package co.uk.gkortsaridis.breakingbad_gan.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import co.uk.gkortsaridis.breakingbad_gan.R
import co.uk.gkortsaridis.breakingbad_gan.entities.BreakingBadCharacter
import co.uk.gkortsaridis.breakingbad_gan.interfaces.CharacterClickListener
import co.uk.gkortsaridis.breakingbad_gan.interfaces.SeasonsFilterListener
import co.uk.gkortsaridis.breakingbad_gan.utils.GANCodeTestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CharacterClickListener, SeasonsFilterListener {

    private var charactersAdapter: CharactersRecyclerViewAdapter? = null
    private var allCharacters: ArrayList<BreakingBadCharacter> = arrayListOf()
    private var searchQuery = ""
    private var seasonsFilter = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seasonsFilter.add(1)
        seasonsFilter.add(2)
        seasonsFilter.add(3)
        seasonsFilter.add(4)
        seasonsFilter.add(5)

        charactersAdapter = CharactersRecyclerViewAdapter(context = this, listener = this)
        characters_rv.adapter = charactersAdapter
        characters_rv.layoutManager = GridLayoutManager(this, 2)

        character_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchQuery = p0?.trim() ?: ""
                populateData()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchQuery = p0?.trim() ?: ""
                populateData()
                return true
            }

        })

        filter_btn.setOnClickListener {
            BreakingBadAlertDialogs.seasonFilterAlertDialog(activity = this, seasons = seasonsFilter, listener = this).show()
        }

        getBreakingBadData()
    }

    private fun getBreakingBadData() {
        val disposable = GANCodeTestApi.api.getBreakingBadCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { allCharacters = it; populateData() },
                {
                    Toast.makeText(
                        this,
                        "We encountered a problem. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
    }

    private fun populateData() {
        var filtered = allCharacters
        //Filter by search query first
        if (searchQuery != "") {
            filtered = ArrayList(allCharacters.filter { it.name.contains(searchQuery, true) })
        }

        //Filter by filter season second
        filtered = ArrayList(filtered.filter { !Collections.disjoint(it.appearance, seasonsFilter) })

        charactersAdapter?.updateItems(filtered)
    }

    override fun onCharacterClicked(
        character: BreakingBadCharacter,
        characterImageView: ImageView?,
    ) {

        val options = ActivityOptions.makeSceneTransitionAnimation(this, characterImageView, "character_image")
        val intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra("CHARACTER", character)
        startActivity(intent, options.toBundle())
    }

    override fun filterApplied(seasons: ArrayList<Int>) {
        seasonsFilter = seasons
        populateData()
    }

}