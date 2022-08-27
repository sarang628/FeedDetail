package com.sarang.feed_detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.data.ReviewAndImage
import com.example.torang_core.data.model.Comment
import com.example.torang_core.util.Logger
import com.sarang.feed_detail.holder.TimeLineCommentHolder
import com.sarang.feed_detail.holder.TimeLineDetailHeaderHolder
import java.util.*

/**
 * [AddCommentHolder]
 * [CommentsRvAdt]
 * [CommentsFragment]
 * [ItemCommentBinding]
 * [TimeLineDetailHeaderHolder]
 * [ItemTimeLineDetailHeaderBinding]
 */

class CommentsRvAdt : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class TimeLineDetailItemType {
        FEED,
        COMMENT
    }

    private var feed: ReviewAndImage? = null
    private var comments: ArrayList<Comment> = ArrayList()

    fun addComment(index: Int, comment: Comment) {
        comments.add(0, comment)
        notifyItemInserted(index)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TimeLineDetailItemType.FEED.ordinal
        else TimeLineDetailItemType.COMMENT.ordinal
    }

    fun setComments(list: ArrayList<Comment>?) {
        list?.let {
            Logger.d(list.size)
            if (comments.size == 0 || it.size == comments.size) {
                this.comments = list
                notifyDataSetChanged()
            } else {
                addComment(1, it[0])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) TimeLineDetailHeaderHolder.create(parent)
        else TimeLineCommentHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 0) {
            (holder as TimeLineDetailHeaderHolder).apply {
                val pictureAdapter = PictureAdapter()
                holder.mBinding.recyclerView.adapter = pictureAdapter
                setFeed(feed)
            }
        } else {

        }
    }

    override fun getItemCount(): Int {
        var count = 0
        if (comments != null) count += comments!!.size + 1
        return count
    }

    fun setFeed(feed: ReviewAndImage?) {
        this.feed = feed
        notifyDataSetChanged()
    }

    fun deleteComment(comment: Comment) {
        var deleteIndex = -1
        for (i in comments!!.indices) {
            if (comments!![i].comment_id == comment.comment_id) deleteIndex = i
            break
        }
        if (deleteIndex != -1) {
            comments!!.removeAt(deleteIndex)
            notifyItemRemoved(deleteIndex)
        }
    }
}