package com.dviss.simplechat.ui.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dviss.simplechat.domain.model.Message
import com.dviss.simplechat.usecase.ChatUseCases
import com.google.firebase.database.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesDatabaseReference: DatabaseReference,
    private val chatUseCases: ChatUseCases
) : ViewModel() {

    init {
        addChildEventListener()
    }

    //viewmodel state
    var state by mutableStateOf(ChatState())
        private set

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.OnAttachImageClick -> {
                TODO()
            }
            is ChatEvent.OnSendMessageClick -> {
                val message = Message(state.messageText, state.userName, null)
                viewModelScope.launch {
                    chatUseCases.sendMessage(message)
                }
                state = state.copy(messageText = "")
            }
            is ChatEvent.OnInputTextChange -> {
                state = state.copy(messageText = event.text)
            }
            is ChatEvent.OnNewMessageAdded -> {

            }
        }
    }

    fun addChildEventListener() {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                if (message != null) {
                    state = state.copy(
                        messageList = state.messageList + message
                    )
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        messagesDatabaseReference.addChildEventListener(childEventListener)
    }
}