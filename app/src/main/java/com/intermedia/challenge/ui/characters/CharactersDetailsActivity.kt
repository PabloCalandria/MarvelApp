package com.intermedia.challenge.ui.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.intermedia.challenge.data.models.Character
import com.intermedia.challenge.databinding.ActivityCharacterDetailBinding
import com.intermedia.challenge.ui.comic.ComicsAdapter
import kotlinx.android.synthetic.main.activity_character_detail.*


class CharactersDetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var  character : Character
    private val comicAdapter = ComicsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharacter()

        close()
    }

    private fun setupCharacter (){
        character = intent.getParcelableExtra("intent_character")
        binding.character = character
        recycler_view_character_detail.adapter = comicAdapter
        comicAdapter.update(character.comics.appearances)
        recycler_view_character_detail.layoutManager = LinearLayoutManager(this)
    }

    private fun close(){
        binding.buttonClose.setOnClickListener {
            onBackPressed()
        }
    }
}