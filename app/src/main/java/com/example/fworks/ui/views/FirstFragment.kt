package com.example.fworks.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fworks.data.db.entity.FavouriteGif
import com.example.fworks.data.db.repository.GifDatabase
import com.example.fworks.data.db.repository.RoomLocalRepository
import com.example.fworks.data.model.TredingGifImage
import com.example.fworks.data.remote.repository.ApiRetrofitClient
import com.example.fworks.data.remote.repository.GifPagedRepository
import com.example.fworks.databinding.FragmentFirstBinding
import com.example.fworks.ui.adapter.GifLoadStateAdapter
import com.example.fworks.ui.adapter.TrendingListAdapter
import com.example.fworks.viewmodel.FavouriteViewModel
import com.example.fworks.viewmodel.FavouriteViewModelFactory
import com.example.fworks.viewmodel.GifVMFactory
import com.example.fworks.viewmodel.ViewModelMain

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),  SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentFirstBinding? = null
    lateinit var viewModel: ViewModelMain
    lateinit var gifAdapter : TrendingListAdapter
    lateinit var gifArrayList: ArrayList<TredingGifImage>
    private lateinit var fViewModel: FavouriteViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView()
    {
        activity?.let {
            viewModel = ViewModelProvider(requireActivity(), GifVMFactory(
                GifPagedRepository(
                ApiRetrofitClient.apiClient())
            )
            ).get(ViewModelMain::class.java)

            fViewModel = ViewModelProvider(this, FavouriteViewModelFactory(RoomLocalRepository(
                GifDatabase(requireActivity())
            )))[FavouriteViewModel::class.java]

            observeInput(viewModel)
        }

        gifAdapter = TrendingListAdapter(viewModel)
        binding.rvGif.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = gifAdapter.withLoadStateHeaderAndFooter(
                header = GifLoadStateAdapter{gifAdapter.retry()},
                footer = GifLoadStateAdapter{gifAdapter.retry()}
            )
        }

        binding.btnGo.setOnClickListener{
            viewModel.searchGifs(binding.etSearch.text.toString())
        }

        binding.btnClear.setOnClickListener{
            viewModel.searchGifs("")
            binding.etSearch.setText("")
        }

        binding.pBarLayout.btnRetry.setOnClickListener{
            gifAdapter.retry()
        }

        gifAdapter.addLoadStateListener {loadState->
            binding.apply{
                pBarLayout.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvGif.isVisible = loadState.source.refresh is LoadState.NotLoading
                pBarLayout.btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                pBarLayout.lblLoadingError.isVisible = loadState.source.refresh is LoadState.Error
                if(loadState.source.refresh is LoadState.Loading && loadState.append.endOfPaginationReached && gifAdapter.itemCount<1)
                {
                   rvGif.isVisible = false
                   pBarLayout.lblLoadingError.isVisible = true
                }
                else{
                   pBarLayout.lblLoadingError.isVisible = false
                }
            }
        }
        binding.srGif.setOnRefreshListener(this)
    }

    private fun observeInput(viewModel: ViewModelMain) {
        viewModel.gif.observe(viewLifecycleOwner) {
            gifAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.getSelectedITem().observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {item->
                fViewModel.insert(FavouriteGif(item.id!!, item.title, item.images!!.orignal!!.url!!))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRefresh() {
        if (binding.srGif.isRefreshing) {
            binding.srGif.isRefreshing = false;
        }
        viewModel.searchGifs("")
        binding.etSearch.setText(binding.etSearch.text.toString())
    }
}