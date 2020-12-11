package hu.unideb.todo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import hu.unideb.todo.R
import hu.unideb.todo.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUpdateBinding>(inflater,
            R.layout.fragment_update,container,false)

        binding.updateButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        binding.updateCancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.todo_status,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.completedSpinner.adapter = adapter
        }

        return binding.root;
    }
}