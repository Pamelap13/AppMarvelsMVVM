package com.example.myapplication3

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.getAll.Result
import com.squareup.picasso.Picasso

class ListaViewHolder(val item: View, val callback: Callback): RecyclerView.ViewHolder(item) {
    private val name: TextView= item.findViewById(R.id.text_marvel)
    private val progressImage : ProgressBar = item.findViewById(R.id.progress_image)
    private val image: ImageView= item.findViewById(R.id.image_marvel)

    fun bind(result: Result){

        val urlimage = result.thumbnail.path.replace("http","https") + "." + result.thumbnail.extension
        Log.i("ListaViewHolder",urlimage)
        name.text= result.name
        Picasso
            .get()
            .load(urlimage)
            .into(image)
        progressImage.visibility= View.GONE
        image.visibility= View.VISIBLE

        this.item.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                callback.onItemClick(result.id)
            }
        })
    }
}