package com.example.myapplication3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.repository.CharacterListRepository
import com.example.myapplication3.viewmodel.CharacterListViewModel
import com.example.myapplication3.viewmodel.CharacterListViewModelFactory

class CharacterListFragment : Fragment(), Callback {

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListaAdapter
    lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api= ApiClient().getClient()
        val repository= CharacterListRepository(api)
        viewModel = ViewModelProvider(this, CharacterListViewModelFactory(repository)).get(CharacterListViewModel::class.java)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView= view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        progress= view.findViewById(R.id.progressbar)

        viewModel.characterListData.observe(viewLifecycleOwner) { dataList ->
            adapter = ListaAdapter(dataList.data.results, this@CharacterListFragment)
            recyclerView.adapter = adapter
            progress.visibility = View.GONE
            recyclerView.visibility= View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacterListData()
    }

    override fun onItemClick(id: Int) {
        val b: Bundle= Bundle()
        b.putInt("id",id)
        view?.let {
            Navigation
                .findNavController(it)
                .navigate(R.id.action_charactersListFragment_to_characterDetailFragment,b) }
    }

}