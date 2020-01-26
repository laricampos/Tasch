package com.test.testintact.ui.catalog

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.testintact.R

class CatalogListItemDecorator(
    context: Context
) : RecyclerView.ItemDecoration() {

    private val startMargin: Int = context.resources.getDimensionPixelSize(R.dimen.tiny_margin)
    private val spacing: Int = context.resources.getDimensionPixelSize(R.dimen.tiny_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.left = startMargin
        }
        outRect.right = spacing
    }
}