package com.jaehyeon.listadapterroomanditemhelper.util

/**
 * Created by Jaehyeon on 2022/09/14.
 */
interface ItemTouchCallBackInterface {

    fun onItemMove(from: Int, to: Int): Boolean
    fun onItemSwipe(position: Int)

}