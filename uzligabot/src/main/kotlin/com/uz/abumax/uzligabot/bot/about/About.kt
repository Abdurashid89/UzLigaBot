package com.uz.abumax.uzligabot.bot.about

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton


class About(val chatId: Long, val update: Update) {

    fun aboutButtons(): SendMessage {
        val message = SendMessage()
        val inlineKeyboardMarkup = InlineKeyboardMarkup()

        val inlineKeyboardChannelButton = InlineKeyboardButton()
        inlineKeyboardChannelButton.text = "Kanal"
        inlineKeyboardChannelButton.callbackData = "Kanalga o'tish https://t.me/+1s8K6cLTv24zZWJi"

        val inlineKeyboardGroupButton = InlineKeyboardButton()
        inlineKeyboardGroupButton.text = "Guruh"
        inlineKeyboardGroupButton.callbackData = "Guruhga o'tish https://t.me/+EB0sWTjh4Ts3NDMy"

        val keyboardButtonsRow1: ArrayList<InlineKeyboardButton> = ArrayList()
        val keyboardButtonsRow2: ArrayList<InlineKeyboardButton> = ArrayList()

        keyboardButtonsRow1.add(inlineKeyboardChannelButton)
        keyboardButtonsRow2.add(inlineKeyboardGroupButton)

        val rowList: ArrayList<List<InlineKeyboardButton>> = ArrayList()

        rowList.add(keyboardButtonsRow1)
        rowList.add(keyboardButtonsRow2)
        inlineKeyboardMarkup.keyboard = rowList

        message.chatId = chatId.toString()
        message.text = "Bizning sahifalarimiz"
        message.replyMarkup = inlineKeyboardMarkup

        return message
    }

}