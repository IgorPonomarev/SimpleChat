package com.dviss.simplechat.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dviss.simplechat.R
import com.dviss.simplechat.ui.chat.components.MessageItem
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state = viewModel.state
    // Used to decide if the keyboard should be shown
    var textFieldFocusState by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = true
        ) {
            items(state.messageList.reversed()) {
                MessageItem(
                    messageText = it.text,
                    messageAuthor = it.name,
                    messageImgUrl = it.photoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = state.messageText,
                onValueChange = { viewModel.onEvent(ChatEvent.OnInputTextChange(it)) },
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = { viewModel.onEvent(ChatEvent.OnAttachImageClick) }) {
                Icon(
                    painter = painterResource(id = R.drawable.image_48px),
                    contentDescription = "attach image"
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = { viewModel.onEvent(ChatEvent.OnSendMessageClick) },
                enabled = state.messageText.isNotBlank()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.send_48px),
                    contentDescription = "attach image"
                )
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun ChatScreenPreview() {
//    ChatScreen(messages = "")
//}