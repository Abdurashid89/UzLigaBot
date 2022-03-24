package com.uz.abumax.uzligabot.bot.main

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class MainMenu {

    fun start(chatId: String, update: Update): SendMessage {
        val message = SendMessage()
        message.chatId = chatId
        message.text = "Asosiy Menu"

        val keyboard = ReplyKeyboardMarkup()
        keyboard.keyboard = listOf(
            KeyboardRow().apply {
                add(KeyboardButton("Jamoalar ro'yxati"))
                add(KeyboardButton("Taqvim"))
                add(KeyboardButton("Live"))
                add(KeyboardButton("Biz haqimizda"))
            }
//            KeyboardRow().apply {
//                add(KeyboardButton("Biz haqimizda"))
//            }
        )
        keyboard.resizeKeyboard = true

        message.replyMarkup = keyboard

        return message
    }
}