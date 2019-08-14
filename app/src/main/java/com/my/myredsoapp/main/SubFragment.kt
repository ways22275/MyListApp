package com.my.myredsoapp.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.my.myredsoapp.R
import com.my.myredsoapp.data.entity.RedSoEntity

class SubFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    companion object {
        private const val ARG_TITLE = "subFragment_title"

        fun newInstance(title: String) : SubFragment{
            val args = Bundle()
            args.putSerializable(ARG_TITLE, title)
            val fragment = SubFragment()
            fragment.arguments = args
            return fragment
        }
    }

    // region BindViews
    @BindView(R.id.recycleView_list)
    lateinit var mRecyclerView: RecyclerView
    @BindView(R.id.swipeRefreshView)
    lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    // endregion

    private val TAG = javaClass.simpleName
    private lateinit var mUnBinder : Unbinder
    private lateinit var mViewModel : MainViewModel
    private lateinit var mAdapter: RedSoListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sub, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnBinder = ButterKnife.bind(this, view)
        init()
    }

    override fun onRefresh() {
        mViewModel.resetPageCount()
        fetchList()
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
        mViewModel.resetPageCount()
        fetchList()
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        mUnBinder.unbind()
        mViewModel.unregisterLiveData(this)
    }

    private fun init() {
        mSwipeRefreshLayout.setOnRefreshListener(this)

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line, null))
        mRecyclerView.addItemDecoration(itemDecoration)

        mAdapter = RedSoListAdapter()
        mRecyclerView.adapter = mAdapter

        val layoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = layoutManager

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                if(itemPosition == layoutManager.itemCount - 1){
                    fetchList()
                }
            }
        })

        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel.mRedSoList.observe(this, Observer<List<RedSoEntity>> {
                dataList ->
                    mSwipeRefreshLayout.isRefreshing = false
                    if (mViewModel.page == 1) {
                        mAdapter.setData(dataList)
                    } else if (mViewModel.page > 1) {
                        mAdapter.addData(dataList)
                    }
        })

    }

    private fun fetchList() {
        mViewModel.loadDataList(
            context!!,
            arguments?.getString(ARG_TITLE, "")!!)
    }

    private fun log(msg : String) {
        Log.d(TAG, msg)
    }
}
