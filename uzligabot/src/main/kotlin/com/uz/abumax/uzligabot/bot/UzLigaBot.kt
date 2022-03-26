package com.uz.abumax.uzligabot.bot

import com.uz.abumax.uzligabot.bot.about.About
import com.uz.abumax.uzligabot.bot.groups.GroupList
import com.uz.abumax.uzligabot.bot.live.Live
import com.uz.abumax.uzligabot.bot.main.MainMenu
import com.uz.abumax.uzligabot.utils.*
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
    var countA = ZERO
    var countB = ZERO
    var isPermitted = NO_PERMITTED

    var clubA = EMPTY
    var clubB = EMPTY

    override fun getBotToken() = TOKEN

    override fun getBotUsername() = NAME

    override fun onUpdateReceived(update: Update) {
        println(update)

        if (update.hasMessage()) {
            val message = update.message
            val chatId = message.chatId
            if (message.hasText()) {
                val text = message.text
                if (text == START) {
                    startUzLigaBot(chatId)
                } else if (text == MAIN_MENU) {
                    startUzLigaBot(chatId)
                } else if (text == LIVE) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = SPECIAL_CODE
                    execute(m)

                } else if (text == CONSTANTS.CODE) {
                    isPermitted = PERMITTED
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = WRITE_CLUB_NAMES
                    execute(m)

                } else if (text == CLUB_LIST) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = CONSTANTS.CLUBS
                    execute(m)
                } else if (text == TOUR) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = TOUR_ONE
                    execute(m)
                } else if (text == GROUPS) {
                    val m = GroupList().showButtons(chatId.toString())
                    execute(m)
                } else if (text == A) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = GROUP_A
                    execute(m)
                } else if (text == B) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = GROUP_B
                    execute(m)
                } else if (text == C) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = GROUP_C
                    execute(m)
                } else if (text == D) {
                    val m = SendMessage()
                    m.chatId = chatId.toString()
                    m.text = GROUP_D
                    execute(m)
                } else if (text == START_GAME) {
                    if (clubA.isEmpty() or clubB.isEmpty()) {
                        val m = SendMessage()
                        m.chatId = chatId.toString()
                        m.text = PLEASE_WRITE_CLUB_NAMES
                        execute(m)
                        startUzLigaBot(chatId)
                    } else {

                        var urlString = BOT_URL

                        urlString = String.format(urlString, TOKEN, CONSTANTS.CHANNEL_ID, "$clubA $VS $clubB $STARTED")

                        val url = URL(urlString)
                        val conn = url.openConnection()

                        val inputStream = BufferedInputStream(conn.getInputStream())
                        val br = BufferedReader(InputStreamReader(inputStream))

                        val response = br.readText()

                        println(response)

                    }
                } else if (text == ABOUT_US) {

                    val aboutStart = About(chatId, update).aboutButtons()
                    execute(aboutStart)
                } else if (text == FINISH_GAME) {
                    startUzLigaBot(chatId)
                    var urlString = BOT_URL

                    urlString = String.format(
                        urlString,
                        TOKEN,
                        CONSTANTS.CHANNEL_ID,
                        "$START_GAME_MESSAGE $clubA $countA : $countB $clubB"
                    )

                    val url = URL(urlString)
                    val conn = url.openConnection()

                    val inputStream = BufferedInputStream(conn.getInputStream())
                    val br = BufferedReader(InputStreamReader(inputStream))

                    val response = br.readText()

                    println(response)

                    clubA = EMPTY
                    clubB = EMPTY
                    countA = ZERO
                    countB = ZERO


                } else if (text.length > 7 && text.startsWith(SLASH) && isPermitted) {
                    val split = text.replace(" ", EMPTY).split(SLASH)
                    println("split1 $split")
                    clubA = split[1]
                    clubB = split[2]

                    val live = Live().startGame(chatId.toString(), update)
                    execute(live)
                } else if (text == A_GOAL) {
                    if (clubA.isEmpty() or clubB.isEmpty()) {
                        val m = SendMessage()
                        m.chatId = chatId.toString()
                        m.text = PLEASE_WRITE_CLUB_NAMES
                        execute(m)
                        startUzLigaBot(chatId)
                    } else {
                        var urlString = BOT_URL
                        countA++

                        urlString =
                            String.format(urlString, TOKEN, CONSTANTS.CHANNEL_ID, "$clubA  $countA : $countB $clubB")

                        val url = URL(urlString)
                        val conn = url.openConnection()

                        val inputStream = BufferedInputStream(conn.getInputStream())
                        val br = BufferedReader(InputStreamReader(inputStream))

                        val response = br.readText()

                        println(response)
                    }
                } else if (text == B_GOAL) {
                    if (clubA.isEmpty() or clubB.isEmpty()) {
                        val m = SendMessage()
                        m.chatId = chatId.toString()
                        m.text = PLEASE_WRITE_CLUB_NAMES
                        startUzLigaBot(chatId)
                        execute(m)
                    } else {
                        var urlString = BOT_URL
                        countB++

                        urlString =
                            String.format(urlString, TOKEN, CONSTANTS.CHANNEL_ID, "$clubA  $countA : $countB $clubB")

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
                    m.text = ERROR_MESSAGE
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

    private fun startUzLigaBot(chatId: Long) {
        val menu = MainMenu().start(chatId.toString())
        execute(menu)
    }
}