package com.dviss.simplechat.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.dviss.simplechat.ui.chat.components.MessageItem

@Composable
fun ChatScreen(
    messageText: String
) {
    Column {
        LazyColumn() {
            //MessageItem()
        }
    }
}