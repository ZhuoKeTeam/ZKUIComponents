package com.zkteam.ui.components.demo.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.demo.R
import com.zkteam.ui.components.demo.viewpager.cards.Card
import com.zkteam.ui.components.demo.viewpager.cards.CardView
import com.zkteam.ui.components.viewpager.ZKFragmentAdapter
import kotlinx.android.synthetic.main.activity_view_pager_card.*

class ViewPagerCardActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager_card
    }

    override fun initData(bundle: Bundle?) {
        zkViewPager.viewPager.adapter = object : ZKFragmentAdapter(this) {
            override fun getItemCount(): Int {
                return Card.DECK.size
            }

            override fun createFragment(position: Int): Fragment {
                return CardFragment.create(Card.DECK[position])
            }
        }
    }

    override fun initViews(contentView: View) {
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
                fragment.arguments = card.toBundle()
                return fragment
            }
        }
    }

}