package com.uz.abumax.uzligabot.bot

import com.uz.abumax.uzligabot.bot.about.About
import com.uz.abumax.uzligabot.bot.live.Live
import com.uz.abumax.uzligabot.bot.main.MainMenu
import com.uz.abumax.uzligabot.utils.CONSTANTS
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


@Service
class UzLigaBot : TelegramLongPollingBot() {
    var countA = 0
    var countB = 0
    var isPermitted = false

    var clubA = ""
    var clubB = ""
    var startedGame = "o'yin boshlandi"

    override fun getBotToken() = CONSTANTS.TOKEN

    override fun getBotUsername() = "UzLigaBot"

    override fun onUpdateReceived(update: Update) {
        println(update)

        if (update.hasMessage()) {
            val message = update.message
            val chatId = message.chatId
            if (message.hasText()) {
                val text = message.text
                if (text == "/start") {
                    startUzLigaBot(chatId, update)
                } else if (text == "Asosiy menu") {
                    startUzLigaBot(chatId, update)
                } else if (text == "Live") {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = "Maxsus kodni kiriting:"
                    execute(m)

                } else if (text == CONSTANTS.CODE) {
                    isPermitted = true
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
                } else if (text == "Start") {
                    if (clubA.isEmpty() or clubB.isEmpty()) {
                        val m = SendMessage()
                        m.chatId = chatId.toString()
                        m.text = "iltimos avval jamoalar nomini kiriting!"
                        execute(m)
                        startUzLigaBot(chatId, update)
                    } else {
//                        val m = SendMessage()
//                        m.chatId = chatId.toString()
//                        m.text = "$clubA $countA : $countB $clubB \n $startedGame"
//                        execute(m)

                        var urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s"

                        val apiToken = CONSTANTS.TOKEN
                        val chatId = CONSTANTS.CHANNEL_ID
                        val text = "$clubA vs $clubB Boshlandi"

                        urlString = String.format(urlString, apiToken, chatId, text)

                        val url = URL(urlString)
                        val conn = url.openConnection()

                        val inputStream = BufferedInputStream(conn.getInputStream())
                        val br = BufferedReader(InputStreamReader(inputStream))

                        val response = br.readText()

                        println(response)

                    }
                } else if (text == "Biz haqimizda") {

                    val aboutStart = About(chatId, update).aboutButtons()
                    execute(aboutStart)
                } else if (text == "Finish game") {
                    startUzLigaBot(chatId, update)
                    var urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s"

                    val apiToken = CONSTANTS.TOKEN
                    val chatId = CONSTANTS.CHANNEL_ID
                    val text = "O'yin tugadi hisob $clubA $countA : $countB $clubB"

                    urlString = String.format(urlString, apiToken, chatId, text)

                    val url = URL(urlString)
                    val conn = url.openConnection()

                    val inputStream = BufferedInputStream(conn.getInputStream())
                    val br = BufferedReader(InputStreamReader(inputStream))

                    val response = br.readText()

                    println(response)

                    clubA = ""
                    clubB = ""
                    countA = 0
                    countB = 0


                } else if (text.length > 7 && text.startsWith("/") && isPermitted) {
                    val split = text.replace(" ", "").split("/")
                    println("split1 $split")
                    clubA = split[1]
                    clubB = split[2]

                    val live = Live().startGame(chatId.toString(), update)
                    execute(live)
                } else if (text == "A +") {
                    if (clubA.isEmpty() or clubB.isEmpty()) {
                        val m = SendMessage()
                        m.chatId = chatId.toString()
                        m.text = "iltimos avval jamoa nomini kiriting!"
                        execute(m)
                        startUzLigaBot(chatId, update)
                    } else {
                        var urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s"
                        countA++

                        val apiToken = CONSTANTS.TOKEN
                        val chatId = CONSTANTS.CHANNEL_ID
                        val text = "$clubA  $countA : $countB $clubB"

                        urlString = String.format(urlString, apiToken, chatId, text)

                        val url = URL(urlString)
                        val conn = url.openConnection()

                        val inputStream = BufferedInputStream(conn.getInputStream())
                        val br = BufferedReader(InputStreamReader(inputStream))

                        val response = br.readText()

                        println(response)
                    }
                } else if (text == "B +") {
                    if (clubA.isEmpty() or clubB.isEmpty()) {
                        val m = SendMessage()
                        m.chatId = chatId.toString()
                        m.text = "iltimos avval jamoa nomini kiriting!"
                        startUzLigaBot(chatId, update)
                        execute(m)
                    } else {
                        var urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s"
                        countB++
                        val apiToken = CONSTANTS.TOKEN
                        val chatId = CONSTANTS.CHANNEL_ID
                        val text = "$clubA  $countA : $countB $clubB"

                        urlString = String.format(urlString, apiToken, chatId, text)

                        val url = URL(urlString)
                        val conn = url.openConnection()

                        val inputStream = BufferedInputStream(conn.getInputStream())
                        val br = BufferedReader(InputStreamReader(inputStream))

                        val response = br.readText()

                        println(response)
                    }
                } else {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = "Mavjud bo'lmagan buyruq \n qaytadan urinib ko'ring"
                    execute(m)
                }
            }
        } else if (update.hasCallbackQuery()) {
            try {
                val sendMessage = SendMessage()
                sendMessage.text = update.callbackQuery.data

                println("Callback ${update.callbackQuery.data}")

                sendMessage.chatId = update.callbackQuery.message.chatId.toString()
                execute(sendMessage)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
    }

    private fun startUzLigaBot(chatId: Long, update: Update) {
        val menu = MainMenu().start(chatId.toString(), update)
        execute(menu)
    }
}