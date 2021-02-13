package com.viona.moviecatalogue.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viona.moviecatalogue.data.DataMovie
import com.viona.moviecatalogue.data.DataTVShow
import com.viona.moviecatalogue.databinding.FragmentTvShowBinding

class TVShowFragment : Fragment(), TVShowCallback {

    private lateinit var fragmentTVShowBinding: FragmentTvShowBinding

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

        if (activity != null){
            val viewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
            )[TVShowViewModel::class.java]
            val tvShow = viewModel.getTvShow()
            val tvShowAdapter = TVShowAdapter(this)
            tvShowAdapter.setTVShows(tvShow)

            with(fragmentTVShowBinding.rvTvShow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

        }
    }

    override fun onShareClick(tvShows: DataTVShow) {
        activity?.let {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                /*  .setChooserTitle(getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, movie.title))*/
                .startChooser()
        }
    }
}