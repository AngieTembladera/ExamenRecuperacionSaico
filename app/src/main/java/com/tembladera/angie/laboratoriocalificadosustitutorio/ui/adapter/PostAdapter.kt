package com.tembladera.angie.laboratoriocalificadosustitutorio.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tembladera.angie.laboratoriocalificadosustitutorio.data.model.Post
import com.tembladera.angie.laboratoriocalificadosustitutorio.databinding.ItemDatosBinding

class PostAdapter(
    private val posts: List<Post>,
    private val clickSimple: (Post) -> Unit,
    private val clickLargo: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemDatosBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickSimple(posts[adapterPosition])
            }
            itemView.setOnLongClickListener {
                clickLargo(posts[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemDatosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.tvTitulo.text = post.title
        holder.binding.tvCuerpo.text = post.body
    }

    override fun getItemCount(): Int = posts.size
}
