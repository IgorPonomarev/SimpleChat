package com.dviss.simplechat.ui.chat

sealed class ChatEvent{
    object OnAttachImageClick: ChatEvent()
    object OnSendMessageClick: ChatEvent()
    object OnNewMessageAdded: ChatEvent()
    data class OnInputTextChange(val text: String) : ChatEvent()
}
