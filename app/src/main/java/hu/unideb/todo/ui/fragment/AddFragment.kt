package hu.unideb.todo.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import hu.unideb.todo.R
import hu.unideb.todo.databinding.FragmentAddBinding
import hu.unideb.todo.model.ToDoModel
import hu.unideb.todo.ui.viewmodel.AddViewModel

class AddFragment : Fragment() {

    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddBinding>(inflater,
            R.layout.fragment_add,container,false)

        viewModel = ViewModelProvider(this, AddViewModel.Factory(
            requireNotNull(this.activity).application)).get(AddViewModel::class.java)

        binding.saveButton.setOnClickListener { view: View ->
            viewModel.addToDo(ToDoModel(
                null,
                binding.editTextTextPersonName.text.toString(),
                false
            ))

            view.findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
            val inputMethodManager = this.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
            val inputMethodManager = this.requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return binding.root
    }
}