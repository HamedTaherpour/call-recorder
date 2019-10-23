package io.hamed.callrecorder.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.hamed.callrecorder.AppController
import io.hamed.callrecorder.ContactEntity
import io.hamed.callrecorder.R
import kotlinx.android.synthetic.main.item_contact.view.*


/**
 * Author: Hamed Taherpour
 * *
 * Created: 10/23/2019
 */
class ContactAdapter : ListAdapter<ContactEntity, ContactAdapter.Holder>(DiffCallback()) {

    var listener: OnItemClickListener? = null

    class DiffCallback : DiffUtil.ItemCallback<ContactEntity>() {
        override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.description == newItem.description &&
                    oldItem.id == newItem.id &&
                    oldItem.favorite === newItem.favorite
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ContactEntity) = with(itemView) {

            itemView.iv_favorite.setImageResource(if (item.favorite) R.drawable.ic_star else R.drawable.ic_star_border)
            itemView.iv_status.setImageResource(if (item.incoming) R.drawable.ic_incoming else R.drawable.ic_outgoing)
            itemView.tv_date.text = "122/122/11"//DateUtility.getMyDate("2012/1/1")
            itemView.tv_description.text = item.description
            itemView.tv_name.text = item.name

            Glide.with(AppController.getAppContext())
                .load(item.date)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.iv_profile)

            setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION)
                    listener?.onItemClick(item)
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: ContactEntity)
    }
}