package com.example.midterm_section2

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_section2.databinding.PostItemBinding
import com.example.midterm_section2.model.Post

class PostHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        val username = post.user?.username  as String
        binding.tvUsername.text = username
        binding.tvDescription.text = post.description
        binding.tvRelativeTime.text = DateUtils.getRelativeTimeSpanString(post.creationTimeMs)
    }
}

class PostsAdapter(
    private val posts: List<Post>
) : RecyclerView.Adapter<PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val infaltor = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(infaltor, parent, false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }
}
