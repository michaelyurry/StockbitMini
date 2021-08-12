package com.yurry.stockbitmini.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yurry.stockbitmini.ui.chat.ChatFragment
import com.yurry.stockbitmini.ui.portofolio.PortfolioFragment
import com.yurry.stockbitmini.ui.search.SearchFragment
import com.yurry.stockbitmini.ui.stream.StreamFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {

    val homeMenuList by lazy {
        listOf(
            MainFragment(),
            StreamFragment(),
            SearchFragment(),
            ChatFragment(),
            PortfolioFragment()
        )
    }

    override fun getItemCount(): Int {
        return homeMenuList.size
    }

    override fun createFragment(position: Int): Fragment {
        return homeMenuList[position]
    }

    inline fun <reified T>getFragmentPosition(): Int{
        for(index in 0..this.homeMenuList.size){
            if(this.homeMenuList[index] is T){
                return index
            }
        }
        return 0
    }
}