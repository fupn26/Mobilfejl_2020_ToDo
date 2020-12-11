package hu.unideb.todo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import hu.unideb.todo.R
import hu.unideb.todo.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddBinding>(inflater,
            R.layout.fragment_add,container,false)

        binding.saveButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }

        return binding.root
    }
}