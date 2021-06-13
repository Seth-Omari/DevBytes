package com.github.seth.devbytes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.seth.devbytes.R
import com.github.seth.devbytes.databinding.FragmentDevByteBinding
import com.github.seth.devbytes.databinding.ItemDevbyteBinding
import com.github.seth.devbytes.domain.Video
import com.github.seth.devbytes.viewmodels.DevByteViewModel

class DevByteFragment : Fragment() {

    private var devByteAdapter: DevByteAdapter? = null
//    private val viewModel: DevByteViewModel by Lazy {
//        ViewModelProvider(this).get(DevByteViewModel::class.java)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDevByteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dev_byte, container, false)

        /**
         * set a life cycle owner to the data bidning to observe live data from the view model
         */
        binding.lifecycleOwner = viewLifecycleOwner
        // binding.viewModel = viewModel

        devByteAdapter = DevByteAdapter(VideoClick {
            /**
             * when the video is clicked do something here TO DO()
             */
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = devByteAdapter
        }
        return binding.root
    }
}

/**
 * creating a video click listener class with a higher-order function/constructor
 * that will take in a lambda block of code for a video click
 */
class VideoClick(val block: (Video) -> Unit) {
    fun onClick(video: Video) = block(video)

}

class DevByteAdapter(val callBack: VideoClick) : RecyclerView.Adapter<DevByteViewHolder>() {

    /**
     * GET the list of videos from the view model
     */
    val videos: List<Video> = TODO()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val dataBinding: ItemDevbyteBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevByteViewHolder.LAYOUT,
            parent,
            false
        )
        return DevByteViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callBack
        }

    }

    override fun getItemCount() = videos.size

}

class DevByteViewHolder(val viewDataBinding: ItemDevbyteBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_devbyte
    }
}
