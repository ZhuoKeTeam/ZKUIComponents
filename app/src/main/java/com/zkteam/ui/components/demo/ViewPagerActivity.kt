package com.zkteam.ui.components.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.demo.cards.Card
import com.zkteam.ui.components.demo.cards.CardView
import com.zkteam.ui.components.viewpager.ZKFragmentAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }

    override fun initData(bundle: Bundle?) {
        vp_view.adapter = object : ZKFragmentAdapter(this) {
            override fun getItemCount(): Int {
                return Card.DECK.size
            }

            override fun createFragment(position: Int): Fragment {
//                return CardFragment.create(Card.DECK[position])
                return WQFragment.create(position)
            }
        }
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    override fun initViews(contentView: View) {
        //function
    }

    override fun onDebouncingClick(view: View) {
        //function
    }

    class CardFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val cardView = CardView(layoutInflater, container)
            cardView.bind(Card.fromBundle(arguments!!))
            return cardView.view
        }

        companion object {

            /** Creates a Fragment for a given [Card]  */
            fun create(card: Card): CardFragment {
                val fragment = CardFragment()
                val args = Bundle(1)
                fragment.arguments = args
                return fragment
            }
        }
    }

}