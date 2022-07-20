package com.example.shercofaqapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentGarageBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.utils.*
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.RecyclerViewBikeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GarageFragment : Fragment() {

    lateinit var binding: FragmentGarageBinding
    private val recyclerViewAdapter = RecyclerViewBikeAdapter()
    private val garageFragmentViewModel: GarageFragmentViewModel by viewModels()
    private var bikeList: List<Bike> = listOf()
    private var userName = "UserName"
    private lateinit var itemTouchHelper: ItemTouchHelper
    private val garageItemCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            showAlertDialog(viewHolder)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initDatabase()
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_garage, container, false)

        binding.loadingProgressIndicator.visibility = View.VISIBLE

        garageFragmentViewModel.getBikes()

        setTitle()

        binding.garageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.garageRecyclerView.adapter = recyclerViewAdapter
        binding.garageRecyclerView.setHasFixedSize(true)
        binding.floatingActionButton.setOnClickListener { onAddBike() }

        itemTouchHelper = ItemTouchHelper(garageItemCallback)
        itemTouchHelper.attachToRecyclerView(binding.garageRecyclerView)

        garageFragmentViewModel.bikeList.observe(viewLifecycleOwner, Observer<List<Bike>> { bikes ->
            binding.loadingProgressIndicator.visibility = View.GONE
            bikeList = bikes
            recyclerViewAdapter.addBikeList(bikeList as ArrayList<Bike>)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu_garage, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                findNavController()
                    .navigate(R.id.action_garageFragment_to_accountFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTitle() {
        REF_DATABASE_ROOT.child(USERS_NODE).child(CURRENT_UID).child(USER_NAME_FIELD).get().addOnSuccessListener {
            userName = it.value.toString()
            (activity as AppCompatActivity?)?.supportActionBar?.title = userName
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun onAddBike() {
        val bundle = bundleOf(
            "isUpdate" to false
        )

        //Go to AddBikeFragment
        findNavController()
            .navigate(R.id.action_garageFragment_to_bikeFragment, bundle)
    }

    private fun showAlertDialog(viewHolder: RecyclerView.ViewHolder) {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE ->
                    garageFragmentViewModel.deleteBike(bikeList[viewHolder.absoluteAdapterPosition])
                DialogInterface.BUTTON_NEGATIVE -> {
                    garageFragmentViewModel.getBikes()
                }
            }
        }

        val dialog = AlertDialog.Builder(context)
            .setCancelable(false)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle(getString(R.string.alert_dialog_title))
            .setPositiveButton(R.string.alert_dialog_button_yes, listener)
            .setNegativeButton(R.string.alert_dialog_button_no, listener)
            .create()

        dialog.show()
    }

}