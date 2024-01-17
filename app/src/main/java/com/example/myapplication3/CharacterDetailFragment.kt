package com.example.myapplication3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication3.repository.CharacterListRepository
import com.example.myapplication3.viewmodel.CharacterListViewModel
import com.example.myapplication3.viewmodel.CharacterListViewModelFactory
import com.squareup.picasso.Picasso

class CharacterDetailFragment: Fragment() {

    private lateinit var viewModel: CharacterListViewModel
    lateinit var imageView: ImageView
    lateinit var textName: TextView
    lateinit var textDescription: TextView
    lateinit var progressDetail: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_character_detail, container, false)
        imageView= view.findViewById(R.id.image_detail)
        textName= view.findViewById(R.id.text_detail)
        textDescription= view.findViewById(R.id.text_detail_description)
        progressDetail= view.findViewById(R.id.progres_detail)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api= ApiClient().getClient()
        val repository= CharacterListRepository(api)
        viewModel = ViewModelProvider(this, CharacterListViewModelFactory(repository)).get(CharacterListViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        val id = arguments?.getInt("id")
        id?.let { viewModel.getCharacterDetailData(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterDetailData.observe(viewLifecycleOwner){detail ->
            textName.text= detail.data.results.first().name
            textDescription.text= detail.data.results.first().description
            val url= detail.data.results.first().thumbnail.path.replace("http","https") +
                    "." + detail.data.results.first().thumbnail.extension
            Picasso
                .get()
                .load(url)
                .into(imageView)
            progressDetail.visibility= View.GONE
        }
    }
}