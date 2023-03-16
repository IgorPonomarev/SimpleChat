package com.dviss.simplechat.ui.chat

sealed class ChatEvent{
    object OnAttachImageClick: ChatEvent()
    data class OnSendMessageClick(val message: String): ChatEvent()
}
