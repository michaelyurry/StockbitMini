package com.yurry.stockbitmini.ui.watchlist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurry.stockbitmini.R
import com.yurry.stockbitmini.data.Resource
import com.yurry.stockbitmini.databinding.FragmentWatchlistBinding
import com.yurry.stockbitmini.ui.viewBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class WatchlistFragment : Fragment(R.layout.fragment_watchlist) {

    private val binding by viewBinding(FragmentWatchlistBinding::bind)

    private val homeViewModel: WatchlistViewModel by viewModel()

    private val watchlistAdapter by lazy { WatchlistAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeListener()
        initRecyclerView()
        initTopTierVolumeFull()
    }

    private fun initSwipeListener(){
        binding.swipeView.setOnRefreshListener {
            homeViewModel.topCrypto.refresh()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeView.isRefreshing = false
            }, 150)
        }
    }

    private fun initRecyclerView(){
        binding.listRecyclerview.adapter = watchlistAdapter
        binding.listRecyclerview.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
    }

    private fun initTopTierVolumeFull(){
        homeViewModel.topCrypto.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    showList()
                    watchlistAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    showError()
                    if (it.throwable is UnknownHostException) {
                        Toast.makeText(requireContext(), R.string.no_internet, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoading(){
        requireActivity().runOnUiThread {
            binding.animationView.visibility = View.VISIBLE
            binding.listRecyclerview.visibility = View.GONE
            binding.errorMessageView.visibility = View.GONE
        }
    }

    private fun showList(){
        requireActivity().runOnUiThread{
            binding.animationView.visibility = View.GONE
            binding.listRecyclerview.visibility = View.VISIBLE
            binding.errorMessageView.visibility = View.GONE
        }
    }

    private fun showError(){
        requireActivity().runOnUiThread {
            binding.animationView.visibility = View.GONE
            binding.listRecyclerview.visibility = View.GONE
            binding.errorMessageView.visibility = View.VISIBLE
        }
    }
}