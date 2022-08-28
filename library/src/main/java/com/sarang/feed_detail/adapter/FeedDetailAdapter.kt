package com.sarang.feed_detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.util.Logger
import com.sarang.feed_detail.data.usecase.ItemCommentLayoutUseCase
import com.sarang.feed_detail.data.usecase.FeedDetailHeaderLayoutUseCase
import com.sarang.feed_detail.holder.TimeLineCommentHolder
import com.sarang.feed_detail.holder.TimeLineDetailHeaderHolder

class FeedDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class TimeLineDetailItemType {
        FEED,
        COMMENT
    }

    private var headerUseCase: FeedDetailHeaderLayoutUseCase? = null
    private var comments: ArrayList<ItemCommentLayoutUseCase> = ArrayList()

    fun addComment(index: Int, comment: ItemCommentLayoutUseCase) {
        comments.add(0, comment)
        notifyItemInserted(index)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TimeLineDetailItemType.FEED.ordinal
        else TimeLineDetailItemType.COMMENT.ordinal
    }

    fun setComments(list: ArrayList<ItemCommentLayoutUseCase>?) {
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
                setFeed(headerUseCase)
            }
        } else {
            (holder as TimeLineCommentHolder).mBinding.useCase = comments[position - 1]
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        count += comments.size + 1
        return count
    }

    fun setHeader(useCase: FeedDetailHeaderLayoutUseCase?) {
        this.headerUseCase = useCase
        notifyDataSetChanged()
    }

    fun deleteComment(comment: ItemCommentLayoutUseCase) {
        /*var deleteIndex = -1
        for (i in comments!!.indices) {
            if (comments!![i].comment_id == comment.comment_id) deleteIndex = i
            break
        }
        if (deleteIndex != -1) {
            comments!!.removeAt(deleteIndex)
            notifyItemRemoved(deleteIndex)
        }*/
    }
}