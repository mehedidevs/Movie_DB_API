package com.ju.myui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class RcvITem : ConstraintLayout {
    private lateinit var mLayout: ConstraintLayout
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var desc: TextView

    private var titleText = ""
    private var descText = ""
    private var imageID = 0

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getResFromXml(context, attrs)
        initView(context)
    }

    private fun initView(context: Context) {
        layoutParams = LayoutParams(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        LayoutInflater
            .from(context)
            .inflate(R.layout.items, this, true)

        mLayout = findViewById(R.id.mLayout)
        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        desc = findViewById(R.id.desc)

        if (titleText.isNotEmpty()) {
            title.text = titleText
        }

        if (descText.isNotEmpty()) {
            desc.text = descText
        }
    }

    private fun getResFromXml(context: Context, attrs: AttributeSet) {
        val data = context.obtainStyledAttributes(attrs, R.styleable.RcvITem)
        titleText = data.getString(R.styleable.RcvITem_Ttext).toString()
        descText = data.getString(R.styleable.RcvITem_Dtext).toString()
        // imageID = data.getInteger(R.styleable.RcvITem_src, 0)

        data.recycle()
    }

    fun setImage(resId: Int) {
        image.setImageResource(resId)
    }
}
