package com.my.myredsoapp.listRedSo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.my.myredsoapp.R
import com.my.myredsoapp.base.BaseViewHolder
import com.my.myredsoapp.data.entity.RedSoEntity
import com.my.myredsoapp.util.GlideApp

class RedSoListAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        private const val VIEW_TYPE_EMPLOYEE = 1
        private const val VIEW_TYPE_BANNER = 2
        private const val TYPE_BANNER = "banner"
        private const val TYPE_EMPLOYEE = "employee"
    }

    private var mList : MutableList<RedSoEntity> = mutableListOf()

    override fun getItemViewType(position: Int): Int {
        return if (mList[position].type == TYPE_EMPLOYEE) VIEW_TYPE_EMPLOYEE else VIEW_TYPE_BANNER
    }

    override fun getItemCount(): Int {
       return if (mList.isEmpty()) 0 else mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
            VIEW_TYPE_EMPLOYEE ->
                ViewHolderEmployee(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false))
            VIEW_TYPE_BANNER ->
                ViewHolderBanner(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
            else ->
                ViewHolderBanner(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is ViewHolderEmployee)
            holder.bind(mList[position])
        else if (holder is ViewHolderBanner)
            holder.bind(mList[position])
    }

    fun setData (list : List<RedSoEntity>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun addData (list : List<RedSoEntity>) {
        mList.addAll(mList.size, list)
        notifyItemRangeInserted(mList.size, list.size)
    }

    inner class ViewHolderEmployee(itemView: View): BaseViewHolder<RedSoEntity>(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.imageView_avatar)
        lateinit var imageViewAvatar: ImageView
        @BindView(R.id.textView_name)
        lateinit var textViewName: TextView
        @BindView(R.id.textView_position)
        lateinit var textViewPosition: TextView
        @BindView(R.id.textView_expertise)
        lateinit var textViewExpertise: TextView

        override fun bind (item : RedSoEntity) {
            GlideApp.with(itemView)
                .load(item.avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(imageViewAvatar)
            textViewName.text = item.name
            textViewPosition.text = item.position
            textViewExpertise.text = item.expertise?.joinToString()
        }

    }

    inner class ViewHolderBanner(itemView: View): BaseViewHolder<RedSoEntity>(itemView) {
        init {
            ButterKnife.bind(this, itemView)
        }

        @BindView(R.id.imageView_banner)
        lateinit var imageViewBanner: ImageView

        override fun bind (item : RedSoEntity) {
            GlideApp.with(itemView)
                .load(item.url)
                .into(imageViewBanner)
        }

    }
}