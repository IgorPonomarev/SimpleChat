package com.dviss.simplechat.ui.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MessageItem(
    messageText: String,
    messageAuthor: String,
    messageImg: String
) {
    Row {
        //Image
        Text(text = messageText)
        Text(text = messageAuthor)
    }
}
