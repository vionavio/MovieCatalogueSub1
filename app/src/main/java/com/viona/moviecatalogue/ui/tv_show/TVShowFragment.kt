package com.viona.moviecatalogue.ui.tv_show

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.data.source.local.entity.TVShowEntity
import com.viona.moviecatalogue.databinding.FragmentTvShowBinding
import com.viona.moviecatalogue.ui.tv_show.detail.DetailTVShowActivity
import com.viona.moviecatalogue.utils.Constants
import com.viona.moviecatalogue.utils.Notification
import com.viona.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVShowFragment : Fragment() {

    private var fragmentTVShowBinding: FragmentTvShowBinding? = null
    private val binding get() = fragmentTVShowBinding

    private val tvShowViewModel: TVShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTVShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvShowAdapter =  TVShowAdapter(requireContext()) { tvShow -> gotoResult(tvShow) }

            tvShowViewModel.getTVShow().observe(viewLifecycleOwner, { responseTVShows ->
                if (responseTVShows != null) {
                    when (responseTVShows.status) {
                        Status.LOADING -> binding?.progressBars?.root?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBars?.root?.visibility = View.GONE

                            tvShowAdapter.submitList(responseTVShows.data)
                        }
                        Status.ERROR -> {
                            binding?.progressBars?.root?.visibility = View.GONE
                            Notification.showToast(context, "Terjadi kesalahan")
                        }
                    }
                }
            })
            with(binding?.rvTvShow) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvShowAdapter
            }
        }
    }

    private fun gotoResult(tvShow: TVShowEntity) {
        val intent = Intent(context, DetailTVShowActivity::class.java)
        intent.putExtra(Constants.EXTRA_TV_SHOW, tvShow.id)
        context?.startActivity(intent)
    }

}