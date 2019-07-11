package com.zkteam.ui.components.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.integration.testapp.cards.Card
import com.zkteam.sdk.base.ZKBaseActivity
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }

    override fun initData(bundle: Bundle?) {

        vp_view.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return CardFragment.create(Card.DECK[position])
            }

            override fun getItemCount(): Int {
                return Card.DECK.size
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
            val cardView = androidx.viewpager2.integration.testapp.cards.CardView(layoutInflater, container)
            cardView.bind(Card.fromBundle(arguments!!))
            return cardView.view
        }

        companion object {

            /** Creates a Fragment for a given [Card]  */
            fun create(card: Card): CardFragment {
                val fragment = CardFragment()
                fragment.arguments = card.toBundle()
                return fragment
            }
        }
    }

}