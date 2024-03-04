package com.ashish.syfapplication.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ashish.syfapplication.R
import com.ashish.syfapplication.databinding.ItemCatImageBinding
import com.ashish.syfapplication.model.ImageResponse
import com.ashish.syfapplication.utills.ValidationUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class ImageAdapter(private val itemClickListener: (ImageResponse) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.MainViewHolder>() {
    private var list = mutableListOf<ImageResponse>()

    @SuppressLint("NotifyDataSetChanged")
    fun setImages(catsList: List<ImageResponse>) {
        this.list = catsList.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCatImageBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val imageResponse = list[position]


        // Placeholder loader
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()


        // Glide options
        val requestOptions: RequestOptions = RequestOptions()
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_cat_half_transparent)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
        if (ValidationUtil.validateImage(imageResponse)) {

            Glide.with(holder.itemView.context).load(imageResponse.url).apply(requestOptions)
                .into(holder.binding.ivCatImage)
        }

        holder.bind(imageResponse, onSelect = itemClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }



    inner class MainViewHolder(val binding: ItemCatImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageData: ImageResponse, onSelect: (ImageResponse) -> Unit) {

            // bind your view here
            binding.root.setOnClickListener {
                onSelect(imageData)
            }
        }

    }
}