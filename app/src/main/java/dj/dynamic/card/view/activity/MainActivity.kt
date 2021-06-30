package dj.dynamic.card.view.activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dj.dynamic.card.R
import dj.dynamic.card.constant.BundleConstants.cardGroupDataBundleKey
import dj.dynamic.card.model.api.CardGroupApiResponse
import dj.dynamic.card.view.fragment.CardListFragment
import dj.dynamic.card.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var containerFrameLayout: FrameLayout
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initViews()
        initViewModel()
        if (savedInstanceState == null) {
            onRefresh()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun initViews() {
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        containerFrameLayout = findViewById(R.id.containerFrameLayout)
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.fetchCardGroupListData()?.observe(this, Observer { listCardGroupApiResponse ->
            loadFragment(listCardGroupApiResponse)
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun loadFragment(listCardGroupApiResponse: CardGroupApiResponse?) {
        val listFrag = CardListFragment.newInstance()
        if (listCardGroupApiResponse != null) {
            listFrag.arguments = bundleOf(
                Pair<String, Any>(cardGroupDataBundleKey, listCardGroupApiResponse)
            )
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, listFrag)
            .commitNow()
    }
}