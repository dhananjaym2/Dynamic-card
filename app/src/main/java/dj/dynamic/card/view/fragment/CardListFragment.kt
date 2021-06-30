package dj.dynamic.card.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dj.dynamic.card.R
import dj.dynamic.card.constant.BundleConstants
import dj.dynamic.card.model.api.CardGroupApiResponse
import dj.dynamic.card.util.ui.recycler_view.VerticalSpaceItemDecoration
import dj.dynamic.card.view.adapter.VerticalRecyclerViewAdapter
import dj.dynamic.card.viewmodel.MainViewModel

class CardListFragment : Fragment() {

    companion object {
        fun newInstance() = CardListFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentRootView: View
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var messageTextViewOnList: TextView
    private lateinit var recyclerAdapter: VerticalRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRootView = inflater.inflate(R.layout.main_fragment, container, false)
        initViews(fragmentRootView)
        fetchData()
        return fragmentRootView
    }

    private fun initViews(view: View) {
        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpaceItemDecoration(
            resources.getDimension(R.dimen.spacing_between_vertical_items).toInt()
        )
        verticalRecyclerView.addItemDecoration(itemDecoration)
        verticalRecyclerView.setHasFixedSize(false)
        messageTextViewOnList = view.findViewById(R.id.messageTextViewOnList)
        messageTextViewOnList.text = getString(R.string.loadingMessage)
    }

    private fun fetchData() {
        val listFromApi =
            arguments?.getParcelable<CardGroupApiResponse>(BundleConstants.cardGroupDataBundleKey)
        setupRecyclerViewAdapter(listFromApi)
    }

    private fun setupRecyclerViewAdapter(listFromApi: CardGroupApiResponse?) {
        if (listFromApi == null) {
            apiFailedErrorMessage()
        } else if (listFromApi.card_groups.isEmpty()) {
            cardsListEmptyMessage()
        } else {
            messageTextViewOnList.visibility = View.GONE
            verticalRecyclerView.visibility = View.VISIBLE
            activity?.let { activityContext ->
                listFromApi.card_groups.let { listData ->
                    recyclerAdapter = VerticalRecyclerViewAdapter(activityContext, listData)
                    verticalRecyclerView.adapter = recyclerAdapter
                }
            }
        }
    }

    private fun cardsListEmptyMessage() {
        verticalRecyclerView.visibility = View.GONE
        messageTextViewOnList.text = getString(R.string.loadingFinishedNoContentToShow)
        messageTextViewOnList.setTextColor(resources.getColor(R.color.neutralMessageColor, null))
        messageTextViewOnList.visibility = View.VISIBLE
    }

    private fun apiFailedErrorMessage() {
        verticalRecyclerView.visibility = View.GONE
        messageTextViewOnList.text = getString(R.string.errorMessage)
        messageTextViewOnList.setTextColor(resources.getColor(R.color.errorMessageColor, null))
        messageTextViewOnList.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}