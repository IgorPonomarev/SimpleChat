package com.dviss.simplechat.di

import com.dviss.simplechat.usecase.AttachImage
import com.dviss.simplechat.usecase.ChatUseCases
import com.dviss.simplechat.usecase.SendMessage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabaseReference(database: FirebaseDatabase): DatabaseReference {
        return database.reference.child("messages")
    }

//    @Provides
//    @Singleton
//    fun provideMessageChildEventListener(databaseReference: DatabaseReference): ChildEventListener {
//        return MessageChildEventListener(databaseReference)
//    }

    @Provides
    @Singleton
    fun provideChatUseCases(
        messagesDatabaseReference: DatabaseReference
    ): ChatUseCases {
        return ChatUseCases(
            attachImage = AttachImage(messagesDatabaseReference),
            sendMessage = SendMessage((messagesDatabaseReference))
        )
    }
}