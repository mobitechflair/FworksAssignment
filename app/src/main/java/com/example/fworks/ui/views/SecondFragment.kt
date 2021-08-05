package com.example.fworks.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fworks.data.db.entity.FavouriteGif
import com.example.fworks.data.db.repository.GifDatabase
import com.example.fworks.data.db.repository.RoomLocalRepository
import com.example.fworks.databinding.FragmentSecondBinding
import com.example.fworks.ui.adapter.FavouriteListAdapter
import com.example.fworks.viewmodel.FavouriteViewModel
import com.example.fworks.viewmodel.FavouriteViewModelFactory


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    lateinit var favouriteAdapter :FavouriteListAdapter
    lateinit var favouriteArrayList: MutableList<FavouriteGif>
    lateinit var viewModel: FavouriteViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView()
    {
        viewModel = ViewModelProvider(this, FavouriteViewModelFactory(
            RoomLocalRepository(
                GifDatabase(requireActivity())
            )
        )
        )[FavouriteViewModel::class.java]
        favouriteArrayList = ArrayList()
        favouriteAdapter = FavouriteListAdapter(favouriteArrayList,viewModel)
        binding.rvFavourite.apply {
            adapter = favouriteAdapter
            layoutManager = GridLayoutManager(activity,2)
            setHasFixedSize(true)
        }

        viewModel.getAllGif().observe(viewLifecycleOwner) {
            favouriteArrayList = it
            favouriteAdapter.setDataList(favouriteArrayList)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}