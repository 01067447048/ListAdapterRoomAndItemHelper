package com.jaehyeon.listadapterroomanditemhelper.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jaehyeon.listadapterroomanditemhelper.R
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person

@BindingAdapter("isEnableState")
fun bindViewIsEnable(view: ImageView, model: Person?) {
    model ?: return

    if (model.mark) {
        view.setImageResource(R.drawable.ic_baseline_star_24)
    }
    else {
        view.setImageResource(R.drawable.ic_baseline_star_border_24)
    }
}