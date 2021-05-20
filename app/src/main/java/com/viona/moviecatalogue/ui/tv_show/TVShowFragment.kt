package com.viona.moviecatalogue.ui.tv_show

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowResultsItem
import com.viona.moviecatalogue.databinding.FragmentTvShowBinding
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowActivity
import com.viona.moviecatalogue.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment() {

    private lateinit var fragmentTVShowBinding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TVShowAdapter
    private val tvShowViewModel: TVShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTVShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            tvShowViewModel.getTVShows()

            tvShowViewModel.tvShows.observe(viewLifecycleOwner, { responseTVShows ->
                tvShowAdapter = TVShowAdapter(requireContext()) { tvShow -> gotoResult(tvShow) }
                responseTVShows?.results?.let {
                    ArrayList(it).let { it1 ->
                        tvShowAdapter.setTVShows(
                            it1
                        )
                    }
                }
                fragmentTVShowBinding.progressBars.root.visibility = View.GONE
                with(fragmentTVShowBinding.rvTvShow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }

            })
        }
    }

    private fun gotoResult(tvShow: TVShowResultsItem) {
        val intent = Intent(context, DetailTVShowActivity::class.java)
        intent.putExtra(Constants.EXTRA_TV_SHOW, tvShow.id)
        context?.startActivity(intent)
    }
}