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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import hu.unideb.todo.R
import hu.unideb.todo.databinding.FragmentUpdateBinding
import hu.unideb.todo.model.ToDoModel
import hu.unideb.todo.ui.viewmodel.UpdateViewModel

class UpdateFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: UpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentUpdateBinding>(inflater,
            R.layout.fragment_update,container,false)

        val args = UpdateFragmentArgs.fromBundle(requireArguments())
        viewModel = ViewModelProvider(this, UpdateViewModel.Factory(
            requireNotNull(this.activity).application,
            args.toDoId)).get(UpdateViewModel::class.java)

        viewModel.toDo.observe(viewLifecycleOwner, Observer { newToDo ->
            binding.toDo = newToDo
        })

        binding.updateButton.setOnClickListener { view: View ->
            viewModel.updateToDo(ToDoModel(
                binding.toDo!!.toDoId,
                binding.updateTitleText.text.toString(),
                binding.completedSpinner.selectedItemPosition.equals(1)
            ))
            view.findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToMainFragment())
            val inputMethodManager = this.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            binding.completedSpinner.selectedItemPosition
        }

        binding.updateCancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToMainFragment())
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