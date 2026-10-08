package com.example.foroom.Helper

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

fun dumpHierarchy(tag: String = "VIEWDUMP") {
    onView(isRoot()).perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> = isRoot()
        override fun getDescription() = "dump view hierarchy"
        override fun perform(uiController: UiController, root: View) {
            fun walk(v: View, depth: Int) {
                val idName = if (v.id != View.NO_ID) {
                    try { v.resources.getResourceName(v.id) } catch (e: Exception) { "id=${v.id}" }
                } else "NO_ID"
                Log.d(
                    tag,
                    "${"  ".repeat(depth)}${v.javaClass.simpleName} | $idName | " +
                            "clickable=${v.isClickable} | desc=${v.contentDescription} | " +
                            "text=${(v as? TextView)?.text}"
                )
                if (v is ViewGroup) for (i in 0 until v.childCount) walk(v.getChildAt(i), depth + 1)
            }
            walk(root, 0)
        }
    })
}