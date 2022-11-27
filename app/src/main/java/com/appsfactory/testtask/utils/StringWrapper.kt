package com.appsfactory.testtask.utils

import android.content.Context
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource
import com.appsfactory.testtask.utils.StringWrapper.StringResource
import com.appsfactory.testtask.utils.StringWrapper.Text
import java.io.Serializable

@Keep
sealed class StringWrapper : Serializable {
    open class Text(val text: String) : StringWrapper() {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Text

            if (text != other.text) return false

            return true
        }

        override fun hashCode(): Int {
            return text.hashCode()
        }

        override fun toString(): String {
            return "Text(text=$text)"
        }
    }

    open class StringResource(@StringRes val resource: Int, vararg val args: Any) : StringWrapper() {

        override fun hashCode(): Int {
            var result = resource
            result = 31 * result + args.contentHashCode()
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true

            if (javaClass != other?.javaClass) return false

            other as StringResource

            if (resource != other.resource) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun toString(): String {
            return "StringResource(resource=$resource, args=${args.contentToString()})"
        }
    }

    @Stable
    @Composable
    fun getValue() = when (this) {
        is Text -> text
        is StringResource -> {
            if (args.isNotEmpty()) {
                stringResource(resource, *args)
            } else {
                stringResource(resource)
            }
        }
    }
}

fun StringWrapper.getValue(context: Context): String = when (this) {
    is Text -> text
    is StringResource -> {
        if (args.isEmpty()) {
            context.getString(resource)
        } else {
            context.getString(resource, *args)
        }
    }
}