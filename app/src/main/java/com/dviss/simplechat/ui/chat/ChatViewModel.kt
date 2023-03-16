package com.dviss.simplechat.ui.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dviss.simplechat.model.Message
import com.dviss.simplechat.usecase.AttachImage
import com.dviss.simplechat.usecase.SendMessage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesDatabaseReference: DatabaseReference
//    private val attachImageUseCase: AttachImage,
//    private val sendMessage: SendMessage
) : ViewModel() {

//    @Inject
//    lateinit var database: FirebaseDatabase
//    @Inject
//    lateinit var messagesDatabaseReference: DatabaseReference

    var userName by mutableStateOf("anonymous")
        private set

    var messageText by mutableStateOf("")
        private set

    fun onSendMessageClick() {
        val message = Message(messageText, userName, null)
        viewModelScope.launch {
            messagesDatabaseReference.push().setValue(message)
        }
        messageText = ""
    }

    fun unInputTextChange(newText: String) {
        messageText = newText
    }
}