package com.dviss.simplechat.ui.chat

import com.dviss.simplechat.domain.model.Message

data class ChatState(
    val userName: String = "anonymous",
    val messageText: String = "",
    val messageList: List<Message> = emptyList()
)
