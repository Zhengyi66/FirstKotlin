package com.zy.fkp.ui.adapter

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by zy
 * Date: 2018/10/31
 * desc:
 */
 
class BaseFragmentAdapter : FragmentPagerAdapter{

    private var fragmentList: List<Fragment>? = ArrayList()
    private var mTitles: List<String>? = null

    constructor(fm :FragmentManager, fragmentList: List<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    constructor(fm: FragmentManager,fragmentList: List<Fragment>,mTitles: List<String>) : super(fm) {
        this.mTitles = mTitles
        setFragments(fm,fragmentList,mTitles)
    }

    //刷新fragment
    @SuppressLint("CommitTransaction")
    private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, mTitles: List<String>){
        this.mTitles = mTitles
        if (this.fragmentList != null) {
            val ft = fm.beginTransaction()
            fragmentList?.forEach {
                ft.remove(it)
            }
            ft?.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles!![position]
    }

}