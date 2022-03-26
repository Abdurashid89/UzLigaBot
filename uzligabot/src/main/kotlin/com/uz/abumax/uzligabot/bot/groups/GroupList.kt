package com.uz.abumax.uzligabot.bot.groups

import com.uz.abumax.uzligabot.utils.*
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class GroupList {

    fun showButtons(chatId: String): SendMessage {
        val message = SendMessage()
        message.chatId = chatId
        message.text = GROUPS

        val keyboard = ReplyKeyboardMarkup()
        keyboard.keyboard = listOf(
            KeyboardRow().apply {
                add(KeyboardButton("A"))
                add(KeyboardButton("B"))
                add(KeyboardButton("C"))
                add(KeyboardButton("D"))
                add(KeyboardButton(MAIN_MENU))
            }
        )
        keyboard.resizeKeyboard = true

        message.replyMarkup = keyboard

        return message
    }

}