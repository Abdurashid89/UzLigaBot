package com.uz.abumax.uzligabot.bot.live

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.lang.Exception

class Live {


    fun startGame(chatId: String, update: Update): SendMessage {
        val sendMessage = SendMessage()
        try {
            val message = update.message.text
            val split = message.replace(" ", "").split("/")
            println("split $split")
            val clubA = split[1]
            val clubB = split[2]

            sendMessage.chatId = chatId
            sendMessage.text = "Jamoalar nomini kiriting: (misol  Aresnal / CHelsea)"

            val keyboard = ReplyKeyboardMarkup()
            keyboard.keyboard = listOf(
                KeyboardRow().apply {
                    add(KeyboardButton(clubA))
                    add(KeyboardButton(clubB))
                },
                KeyboardRow().apply {
                    add(KeyboardButton("A +"))
                    add(KeyboardButton("B +"))
                },

                KeyboardRow().apply {
                    add(KeyboardButton("Start"))
                    add(KeyboardButton("Finish game"))
                }
            )
            keyboard.resizeKeyboard = true

            sendMessage.replyMarkup = keyboard

            return sendMessage
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sendMessage
    }
}