package com.uz.abumax.uzligabot.bot

import com.uz.abumax.uzligabot.bot.live.Live
import com.uz.abumax.uzligabot.bot.main.MainMenu
import com.uz.abumax.uzligabot.utils.CONSTANTS
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UzLigaBot : TelegramLongPollingBot() {
    override fun getBotToken() = "5254095921:AAHmDpA4jXg7PN0X2hRBFSeRUrLELqKq1b4"

    override fun getBotUsername() = "UzLigaBot"

    override fun onUpdateReceived(update: Update) {
        println(update)

        val message = update.message
        val chatId = message.chatId

        if (message.hasText()) {
            val text = message.text
            if (text == "/start") {
                startUzLigaBot(chatId, update)
            }
            if (text == "Asosiy menu") {
                startUzLigaBot(chatId, update)
            } else if (text == "Live") {
                val m = SendMessage()
                m.chatId = chatId.toString()
                m.text = "Jamoalar nomini kiriting: (misol: /Aresnal/Chelsea)"
                execute(m)
            } else if (text == "Jamoalar ro'yxati") {
                val m = SendMessage()
                m.chatId = chatId.toString()
                m.text = CONSTANTS.CLUBS
                execute(m)
            } else if (text == "Taqvim") {
                val m = SendMessage()
                m.chatId = chatId.toString()
                m.text = "Jamoalar ro'yhati shaklantirilmoqda"
                execute(m)
            } else if (text.startsWith("/")) {
                val live = Live().startGame(chatId.toString(), update)
                execute(live)
            } else {
                val m = SendMessage()
                m.chatId = chatId.toString()
                m.text = "Mavjud bo'lmagan buyruq \n qaytadan urinib ko'ring"
                execute(m)
            }
        }
    }

    private fun startUzLigaBot(chatId: Long, update: Update) {
        val menu = MainMenu().start(chatId.toString(), update)
        execute(menu)
    }
}