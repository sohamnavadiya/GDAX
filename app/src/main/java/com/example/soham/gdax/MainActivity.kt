package com.example.soham.gdax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import okio.ByteString
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.textView
import java.util.concurrent.TimeUnit
import okio.ByteString.decodeHex



class MainActivity : AppCompatActivity() {

    val listener = object : WebSocketListener(){

        override fun onOpen(webSocket: WebSocket, response: Response) {
            e(response)
            e(response.message())
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            println("MESSAGE: " + text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            System.out.println("MESSAGE: " + bytes.hex())
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(1000, null)
            println("CLOSE: $code $reason")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout{
            textView("Hello")
        }
        e("hello")

        val client = OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build()



        val request = Request.Builder()
                .url("ws://echo.websocket.org")
                .build()

        client.newWebSocket(request, listener)

        client.dispatcher().executorService().shutdown()

    }
}
