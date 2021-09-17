package com.intermedia.challenge.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.intermedia.challenge.R
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.databinding.ViewEventItemBinding
import com.intermedia.challenge.ui.base.BaseAdapter
import com.intermedia.challenge.ui.comic.ComicsAdapter
import com.intermedia.challenge.utils.binding.setIsVisible
import kotlinx.android.synthetic.main.view_event_item.view.*


class EventsAdapter : BaseAdapter<Event, EventsAdapter.EventsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            ViewEventItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_event_item,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class EventsViewHolder(
        private val binding: ViewEventItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event) = with(itemView) {
            binding.event = item
            binding.buttonEvent.setOnClickListener {
                val comicsAdapter = ComicsAdapter()
                comicsAdapter.update(item.comics.appearances)
                list_event_comics.adapter = comicsAdapter
                if(comics_event_layout.isVisible){
                    button_event.setBackgroundResource(R.drawable.ic_baseline_expand_more_24)
                    comics_event_layout.setIsVisible(false)
                }else{
                    button_event.setBackgroundResource(R.drawable.ic_baseline_expand_less_24)
                    comics_event_layout.setIsVisible(true)
                }
            }
        }
    }
}