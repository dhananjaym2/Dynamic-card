package dj.dynamic.card.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dj.dynamic.card.R
import dj.dynamic.card.constant.BundleConstants
import dj.dynamic.card.model.api.CardGroupApiResponse
import dj.dynamic.card.view.adapter.VerticalRecyclerViewAdapter
import dj.dynamic.card.viewmodel.MainViewModel

class CardListFragment : Fragment() {

    companion object {
        fun newInstance() = CardListFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentRootView: View
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var recyclerAdapter: VerticalRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRootView = inflater.inflate(R.layout.main_fragment, container, false)
        initViews(fragmentRootView)
        setupRVAdapter()
        return fragmentRootView
    }

    private fun initViews(view: View) {
        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView)
    }

    private fun setupRVAdapter() {
        val listFromApi =
            arguments?.getParcelable<CardGroupApiResponse>(BundleConstants.cardGroupDataBundleKey)
        activity?.let { activityContext ->
            listFromApi?.card_groups?.let { listData ->
                recyclerAdapter = VerticalRecyclerViewAdapter(activityContext, listData)
                verticalRecyclerView.layoutManager = LinearLayoutManager(activity)
                verticalRecyclerView.setHasFixedSize(false)
                verticalRecyclerView.adapter = recyclerAdapter
            }//TODO error handling
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

}