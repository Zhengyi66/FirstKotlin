package com.zy.fkp.activity

import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.zy.fkp.R
import com.zy.fkp.base.BaseActivity
import com.zy.fkp.showToast
import com.zy.fkp.ui.fragment.GirlFragment
import com.zy.fkp.ui.fragment.HomeFragment
import com.zy.fkp.ui.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zy
 * Date: 2018/10/31
 * desc:
 */

class MainActivity : BaseActivity() {

    private var mHomeFragment: HomeFragment? = null
    private var mGirlFragment: GirlFragment? = null
    private var mMyFragment: MyFragment? = null

    private var mIndex = -1

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        switchFragment(0)
    }

    /**
     * 切换fragment
     */
    private fun switchFragment(index: Int){
        if (mIndex == index)return
        val transaction = supportFragmentManager.beginTransaction()
        hideFragment(transaction)
        when(index){
            0 //首页
            -> mHomeFragment?.let { transaction.show(it) } ?: HomeFragment.getInstance().let {
                mHomeFragment = it
                transaction.add(R.id.fl_container,it,"home")
            }
            1 //妹子
            -> mGirlFragment?.let { transaction.show(it) } ?: GirlFragment.getInstance().let {
                mGirlFragment = it
                transaction.add(R.id.fl_container,it,"girl")
            }
            2 //我的
            -> mMyFragment?.let { transaction.show(it) } ?: MyFragment.getInstance().let {
                mMyFragment = it
                transaction.add(R.id.fl_container,it,"my")
            }

            else ->{

            }
        }
        transaction.commitAllowingStateLoss()
        mIndex = index
    }

    /**
     * 隐藏所有的fragment
     */
    private fun hideFragment(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mGirlFragment?.let { transaction.hide(it) }
        mMyFragment?.let { transaction.hide(it) }
    }

    override fun initListener() {
        radio_group.setOnCheckedChangeListener { group, checkedId -> when(checkedId){
            R.id.home_rb -> switchFragment(0)
            R.id.girl_rb -> switchFragment(1)
            R.id.my_rb -> switchFragment(2)
        } }
    }

    override fun loadDate() {
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (System.currentTimeMillis().minus(mExitTime) <= 2000){
                finish()
            }else{
                mExitTime = System.currentTimeMillis()
//                showToast("再按一次退出程序")
            }
        }
        return true
        return super.onKeyDown(keyCode, event)
    }


}