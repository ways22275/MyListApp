package com.my.myredsoapp.listRedSo

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
import com.my.myredsoapp.base.BaseFragment
import com.my.myredsoapp.data.entity.RedSoEntity

class SubFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private const val ARG_TITLE = "subFragment_title"

        fun newInstance(title: String) : SubFragment {
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
    private lateinit var mViewModel : RedSoViewModel
    private lateinit var mAdapter: RedSoListAdapter

    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_sub
    }

    override fun initView() {
        mSwipeRefreshLayout.setOnRefreshListener(this)

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line, null))
        mRecyclerView.addItemDecoration(itemDecoration)

        mAdapter = RedSoListAdapter()
        mRecyclerView.adapter = mAdapter

        val layoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = layoutManager

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var mShouldReload = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = lm.findFirstVisibleItemPosition()
                val visibleItemCount = lm.childCount
                val totalItemCount: Int = lm.itemCount
                mShouldReload = firstVisibleItem + visibleItemCount == totalItemCount && dy > 0
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mShouldReload)
                    fetchList()
            }
        })

        mViewModel = ViewModelProviders.of(this).get(RedSoViewModel::class.java)
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

    override fun onRefresh() {
        mViewModel.resetPageCount()
        fetchList()
    }

    override fun onResume() {
        super.onResume()
        log(TAG, "onResume")
    }

    override fun onStart() {
        super.onStart()
        log(TAG,"onStart")
        mViewModel.resetPageCount()
        fetchList()
    }

    override fun onStop() {
        super.onStop()
        log(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        log(TAG,"onDestroy")
        mViewModel.unregisterLiveData(this)
    }

    private fun fetchList() {
        mViewModel.loadDataList(
            context!!,
            arguments?.getString(ARG_TITLE, "")!!)
    }
}
