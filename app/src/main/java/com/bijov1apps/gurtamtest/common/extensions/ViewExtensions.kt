package com.bijov1apps.gurtamtest.common.extensions

import android.text.Html
import android.widget.TextView

fun TextView.fromHtml(text:String){
    val parsedText = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    setText(parsedText)
}