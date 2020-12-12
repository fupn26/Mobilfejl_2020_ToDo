package hu.unideb.todo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import hu.unideb.todo.R
import hu.unideb.todo.databinding.FragmentMainBinding
import hu.unideb.todo.ui.viewmodel.MainViewModel
import hu.unideb.todo.util.ToDoAdapter
import hu.unideb.todo.util.ToDoListener
import timber.log.Timber

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "ViewModel access after onActivityCreated()"
        }
        Timber.i("Called ViewModelProvider.get")
        ViewModelProvider(this, MainViewModel.Factory(activity.application))
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main,container,false)

        binding.addButton.setOnClickListener { view: View ->
            view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment())
        }

        val adapter = ToDoAdapter(ToDoListener { toDoId ->
            viewModel.onToDoClicked(toDoId)
        })
        binding.todoList.adapter = adapter
        viewModel.toDoList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        viewModel.navigateToUpdateToDo.observe(this, Observer { toDoId ->
            toDoId?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.
                    actionMainFragmentToUpdateFragment(toDoId))
                viewModel.onSleepDetailNavigated()
            }
        })

        return binding.root;
    }
}