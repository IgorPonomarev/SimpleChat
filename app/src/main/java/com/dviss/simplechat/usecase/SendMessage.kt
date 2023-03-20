package com.dviss.simplechat.usecase

import com.dviss.simplechat.domain.model.Message
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class SendMessage(
    private val messagesDatabaseReference: DatabaseReference
) {
    suspend operator fun invoke(
        message: Message
    ) {
        messagesDatabaseReference.push().setValue(message)
    }
}