package com.zkteam.ui.components.widget


import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.zkteam.ui.components.R
import java.util.*

class ZKViewPagerIndicator : View, ViewPager.OnPageChangeListener {
    // private int mSelectColor = Color.parseColor("#E38A7C");
    private var mSelectColor = Color.parseColor("#FFFFFF")
    private var mCirclePaint: Paint? = null
    private var mTextPaint: Paint? = null
    private var mCount: Int = 0 // indicator 的数量
    private var mRadius: Int = 0//半径
    private var mStrokeWidth: Int = 0//border
    private var mTextColor: Int = 0// 小圆点中文字的颜色
    private var mDotNormalColor: Int = 0// 小圆点默认颜色
    private var mSpace = 0// 圆点之间的间距
    private var mIndicators: MutableList<Indicator>? = null
    private var mSelectPosition = 0 // 选中的位置
    private var mFillMode = FillMode.NONE// 默认只有小圆点
    private var mViewPager: ViewPager? = null
    private var mOnIndicatorClickListener: OnIndicatorClickListener? = null
    /**
     * 是否允许点击Indicator切换ViewPager
     */
    private var mIsEnableClickSwitch = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        getAttr(context, attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        getAttr(context, attrs)
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        getAttr(context, attrs)
        init()
    }

    private fun init() {

        mCirclePaint = Paint()
        mCirclePaint!!.isDither = true
        mCirclePaint!!.isAntiAlias = true
        mCirclePaint!!.style = Paint.Style.FILL_AND_STROKE

        mTextPaint = Paint()
        mTextPaint!!.isDither = true
        mTextPaint!!.isAntiAlias = true
        // 默认值
        mIndicators = ArrayList()

        initValue()

    }

    private fun initValue() {
        mCirclePaint!!.color = mDotNormalColor
        mCirclePaint!!.strokeWidth = mStrokeWidth.toFloat()

        mTextPaint!!.color = mTextColor
        mTextPaint!!.textSize = mRadius.toFloat()
    }

    /**
     * 获取自定义属性
     *
     * @param context
     * @param attrs
     */
    private fun getAttr(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZKViewPagerIndicator)
        mRadius = typedArray.getDimensionPixelSize(R.styleable.ZKViewPagerIndicator_indicatorRadius, dpToPx(6))
        mStrokeWidth =
            typedArray.getDimensionPixelSize(R.styleable.ZKViewPagerIndicator_indicatorBorderWidth, dpToPx(2))
        mSpace = typedArray.getDimensionPixelSize(R.styleable.ZKViewPagerIndicator_indicatorSpace, dpToPx(5))
        // color
        mTextColor = typedArray.getColor(R.styleable.ZKViewPagerIndicator_indicatorTextColor, Color.BLACK)
        mSelectColor = typedArray.getColor(R.styleable.ZKViewPagerIndicator_indicatorSelectColor, Color.WHITE)
        mDotNormalColor = typedArray.getColor(R.styleable.ZKViewPagerIndicator_indicatorColor, Color.GRAY)

        mIsEnableClickSwitch = typedArray.getBoolean(R.styleable.ZKViewPagerIndicator_enableIndicatorSwitch, false)
        val fillMode = typedArray.getInt(R.styleable.ZKViewPagerIndicator_fill_mode, 2)
        if (fillMode == 0) {
            mFillMode = FillMode.LETTER
        } else if (fillMode == 1) {
            mFillMode = FillMode.NUMBER
        } else {
            mFillMode = FillMode.NONE
        }
        typedArray.recycle()
    }

    /**
     * 测量每个圆点的位置
     */
    private fun measureIndicator() {
        mIndicators!!.clear()
        var cx = 0f
        for (i in 0 until mCount) {
            val indicator = Indicator()
            if (i == 0) {
                cx = (mRadius + mStrokeWidth).toFloat()
            } else {
                cx += ((mRadius + mStrokeWidth) * 2 + mSpace).toFloat()
            }

            indicator.cx = cx
            indicator.cy = (measuredHeight / 2).toFloat()

            mIndicators!!.add(indicator)
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val width = (mRadius + mStrokeWidth) * 2 * mCount + mSpace * (mCount - 1)
        val height = mRadius * 2 + mSpace * 2

        setMeasuredDimension(width, height)

        measureIndicator()
    }

    override fun onDraw(canvas: Canvas) {

        for (i in mIndicators!!.indices) {

            val indicator = mIndicators!![i]
            val x = indicator.cx

            val y = indicator.cy

            if (mSelectPosition == i) {
                mCirclePaint!!.style = Paint.Style.FILL
                mCirclePaint!!.color = mSelectColor
            } else {
                mCirclePaint!!.color = mDotNormalColor
                if (mFillMode != FillMode.NONE) {
                    mCirclePaint!!.style = Paint.Style.STROKE
                } else {
                    mCirclePaint!!.style = Paint.Style.FILL

                }
            }
            canvas.drawCircle(x, y, mRadius.toFloat(), mCirclePaint!!)

            // 绘制小圆点中的内容
            if (mFillMode != FillMode.NONE) {
                var text = ""
                if (mFillMode == FillMode.LETTER) {
                    if (i >= 0 && i < LETTER.size) {
                        text = LETTER[i]
                    }
                } else {
                    text = (i + 1).toString()
                }
                val bound = Rect()
                mTextPaint!!.getTextBounds(text, 0, text.length, bound)
                val textWidth = bound.width()
                val textHeight = bound.height()

                val textStartX = x - textWidth / 2
                val textStartY = y + textHeight / 2
                canvas.drawText(text, textStartX, textStartY, mTextPaint!!)
            }

        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var xPoint = 0f
        var yPoint = 0f
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                xPoint = event.x
                yPoint = event.y
                handleActionDown(xPoint, yPoint)
            }
        }

        return super.onTouchEvent(event)
    }

    private fun handleActionDown(xDis: Float, yDis: Float) {
        for (i in mIndicators!!.indices) {
            val indicator = mIndicators!![i]
            if (xDis < indicator.cx + mRadius.toFloat() + mStrokeWidth.toFloat()
                && xDis >= indicator.cx - (mRadius + mStrokeWidth)
                && yDis >= yDis - (indicator.cy + mStrokeWidth)
                && yDis < indicator.cy + mRadius.toFloat() + mStrokeWidth.toFloat()
            ) {
                // 找到了点击的Indicator
                // 是否允许切换ViewPager
                if (mIsEnableClickSwitch) {
                    mViewPager!!.setCurrentItem(i, false)
                }

                // 回调
                if (mOnIndicatorClickListener != null) {
                    mOnIndicatorClickListener!!.onSelected(i)
                }
                break
            }
        }
    }

    fun setOnIndicatorClickListener(onIndicatorClickListener: OnIndicatorClickListener) {
        mOnIndicatorClickListener = onIndicatorClickListener
    }

    private fun setCount(count: Int) {
        mCount = count
        invalidate()
    }

    /**
     * 设置 border
     *
     * @param borderWidth
     */
    fun setBorderWidth(borderWidth: Int) {
        mStrokeWidth = borderWidth
        initValue()
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor
     */
    fun setTextColor(textColor: Int) {
        mTextColor = textColor
        initValue()
    }

    /**
     * 设置选中指示器的颜色
     *
     * @param selectColor
     */
    fun setSelectColor(selectColor: Int) {
        mSelectColor = selectColor
    }

    /**
     * 设置指示器默认颜色
     *
     * @param dotNormalColor
     */
    fun setDotNormalColor(dotNormalColor: Int) {
        mDotNormalColor = dotNormalColor
        initValue()
    }

    /**
     * 设置选中的位置
     *
     * @param selectPosition
     */
    fun setSelectPosition(selectPosition: Int) {
        mSelectPosition = selectPosition
    }

    /**
     * 设置Indicator 模式
     *
     * @param fillMode
     */
    fun setFillMode(fillMode: FillMode) {
        mFillMode = fillMode
    }

    /**
     * 设置Indicator 半径
     *
     * @param radius
     */
    fun setRadius(radius: Int) {
        mRadius = radius
        initValue()
    }

    fun setSpace(space: Int) {
        mSpace = space
    }

    fun setEnableClickSwitch(enableClickSwitch: Boolean) {
        mIsEnableClickSwitch = enableClickSwitch
    }

    /**
     * 与ViewPager 关联
     *
     * @param viewPager
     */
    fun setUpWithViewPager(viewPager: ViewPager?) {
        releaseViewPager()
        if (viewPager == null) {
            return
        }
        mViewPager = viewPager
        mViewPager!!.addOnPageChangeListener(this)
        val count = mViewPager!!.adapter!!.count
        setCount(count)
    }

    /**
     * 重置ViewPager
     */
    private fun releaseViewPager() {
        if (mViewPager != null) {
            mViewPager!!.removeOnPageChangeListener(this)
            mViewPager = null
        }

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        mSelectPosition = position
        invalidate()
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    /**
     * Indicator 点击回调
     */
    interface OnIndicatorClickListener {
        fun onSelected(position: Int)
    }


    class Indicator {
        var cx: Float = 0.toFloat() // 圆心x坐标
        var cy: Float = 0.toFloat() // 圆心y 坐标
    }

    enum class FillMode {
        LETTER,
        NUMBER,
        NONE
    }

    companion object {
        private val LETTER = arrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "G",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )

        fun dpToPx(dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                Resources.getSystem().displayMetrics
            ).toInt()
        }

        fun pxToDp(px: Float): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().displayMetrics)
                .toInt()
        }
    }
}