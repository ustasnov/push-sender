package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(dbUrl)
        .build()

    FirebaseApp.initializeApp(options)
    val fmi = FirebaseMessaging.getInstance()

    fmi.send(Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()
    )

    Thread.sleep(5000)

    fmi.send(Message.builder()
        .putData("action", "ADDNEWPOST")
        .putData("content", """{
          "userName": "Ivan",
          "content": "В Нетологии действует реферальная программа. Это значит, что если вы порекомендуете обучение вашим знакомым или друзьям и поделитесь вашей персональной ссылкой, то ваш друг/знакомый получит скидку в 10% на первое обучение, а вы - один из наших мини-курсов в подарок."
        }""".trimIndent())
        .setToken(token)
        .build()
    )

    Thread.sleep(5000)

    fmi.send(Message.builder()
        .putData("action", "VIEWED")
        .putData("content", """{
          "userId": 1,
          "userName": "Ivan",
          "postId": 2
        }""".trimIndent())
        .setToken(token)
        .build()
    )
}