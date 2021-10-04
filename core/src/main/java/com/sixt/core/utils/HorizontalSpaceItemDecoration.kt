package com.sixt.core.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(context: Context, @DimenRes val dimen: Int) :
    RecyclerView.ItemDecoration() {

    private val endSize = context.resources.getDimension(dimen).toInt()
    private val isLTR = context.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_LTR

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        if (position != itemCount - 1)
            applyClipping(outRect, endSize)
        else applyZeroClipping(outRect)
    }

    private fun applyZeroClipping(outRect: Rect) {
        applyClipping(outRect, 0)
    }

    private fun applyClipping(outRect: Rect, size: Int) {
        if (isLTR) outRect.right = size
        else outRect.left = size
    }
}