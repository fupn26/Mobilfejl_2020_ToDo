package hu.unideb.todo.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import hu.unideb.todo.R
import hu.unideb.todo.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var completedStatus: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUpdateBinding>(inflater,
            R.layout.fragment_update,container,false)

        binding.updateButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
            val inputMethodManager = this.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        }

        binding.updateCancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_updateFragment_to_mainFragment)
            val inputMethodManager = this.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        parent?.getItemIdAtPosition(position).also { completedStatus = it?.equals(1)}
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}