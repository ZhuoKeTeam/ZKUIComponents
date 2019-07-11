package com.zkteam.ui.components.notification

import androidx.annotation.DrawableRes
import java.io.Serializable

data class ItemDataBean(
    var text: String,
    @DrawableRes
    var imgId: Int,
    var itemId: String
) : Serializable



