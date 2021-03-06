package com.uz.abumax.uzligabot.bot.main

import com.uz.abumax.uzligabot.utils.*
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class MainMenu {

    fun start(chatId: String): SendMessage {
        val message = SendMessage()
        message.chatId = chatId
        message.text = MAIN_MENU

        val keyboard = ReplyKeyboardMarkup()
        keyboard.keyboard = listOf(
            KeyboardRow().apply {
                add(KeyboardButton(CLUB_LIST))
                add(KeyboardButton(TOUR))
                add(KeyboardButton(GROUPS))
            },
            KeyboardRow().apply {
                add(KeyboardButton(LIVE))
                add(KeyboardButton(ABOUT_US))
            }
        )
        keyboard.resizeKeyboard = true

        message.replyMarkup = keyboard

        return message
    }
}