package com.github.seth.devbytes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.seth.devbytes.R
import com.github.seth.devbytes.databinding.FragmentDevByteBinding
import com.github.seth.devbytes.databinding.ItemDevbyteBinding
import com.github.seth.devbytes.domain.Video

class DevByteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //setup databinding with the layout
        val binding: FragmentDevByteBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dev_byte, container, false
        )

        return binding.root
    }
}

/**
 *video click listener with higher-order function constructor
 */
class VideoClick(private val block: (Video) -> Unit) {
    fun onClick(video: Video) = block(video)
}

class DevByteAdapter(val callback: VideoClick) : RecyclerView.Adapter<DevByteViewHolder>() {

    var videos: List<Video> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val viewBinding: ItemDevbyteBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                DevByteViewHolder.LAYOUT,
                parent,
                false
            )
        return DevByteViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
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

