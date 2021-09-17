package com.intermedia.challenge.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intermedia.challenge.databinding.FragmentEventsBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val adapter = EventsAdapter()
    private val viewModel: EventsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventsList()
    }

    private fun setupEventsList() {
        binding.listEvents.adapter = adapter
        viewModel.events.observe(viewLifecycleOwner, { events ->
            adapter.addAll(events)
        })
    }
}