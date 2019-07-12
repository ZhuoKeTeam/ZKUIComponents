package com.zkteam.ui.components.viewpager.animation

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ZKViewPagerTransformerAnimation(private var builder: Builder?,
                                      private var viewPager: ViewPager2) : ViewPager2.PageTransformer {

    companion object {
        private var isScale = false

        private var isRotation = false
        private var mRotation = 0F

        private var isTranslation = false
        private var mTranslationX = 0F
        private var mTranslationY = 0F
    }

    override fun transformPage(page: View, position: Float) {
        val absPos = Math.abs(position)
        page.apply {
            if (builder != null) {
                isRotation = builder!!.isRotation
                mRotation = builder!!.rotation

                mTranslationX = builder!!.translationX
                mTranslationY = builder!!.translationY

                isScale = builder!!.isScale
                isTranslation = builder!!.isTranslation
            }

            if (isTranslation) {
                translationX = if(viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) absPos * mTranslationX else 0f
                translationY = if(viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) absPos * mTranslationY else 0f
            }

            if (isRotation)
                rotation = position * mRotation

            if (isScale) {
                val scale = if (absPos > 1) 0F else 1 - absPos
                scaleX = scale
                scaleY = scale
            } else {
                scaleX = 1f
                scaleY = 1f
            }
        }
    }

    open class Builder {
        var isRotation: Boolean = true
        var rotation: Float = 90F

        var isScale: Boolean = true

        var isTranslation: Boolean = true
        var translationY: Float = 500F
        var translationX: Float = 350F

        fun setRotation(isRotation: Boolean): Builder {
            this.isRotation = isRotation
            return this
        }

        fun setRotationCircle(rotation: Float): Builder {
            this.rotation = rotation
            return this
        }

        fun setTranslationY(translationY: Float): Builder {
            this.translationY = translationY
            return this
        }

        fun setTranslationX(translationX: Float): Builder {
            this.translationX = translationX
            return this
        }

        fun setScale(isScale: Boolean): Builder {
            this.isScale = isScale
            return this
        }

        fun setTranslation(isTranslation: Boolean): Builder {
            this.isTranslation = isTranslation
            return this
        }

    }
}