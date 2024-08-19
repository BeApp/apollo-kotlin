package com.apollographql.apollo3.network.ws

import com.apollographql.apollo3.annotations.ApolloDeprecatedSince
import com.apollographql.apollo3.annotations.ApolloDeprecatedSince.Version.v3_2_2
import com.apollographql.apollo3.api.http.HttpHeader
import okio.ByteString
import platform.Foundation.NSError
import platform.Foundation.NSURLSessionWebSocketCloseCode
import platform.Foundation.NSURLSessionWebSocketTask

interface WebSocketConnectionListener {
  fun onOpen(webSocket: NSURLSessionWebSocketTask)

  fun onClose(webSocket: NSURLSessionWebSocketTask, code: NSURLSessionWebSocketCloseCode)

  fun onError(error: NSError?)
}


actual class DefaultWebSocketEngine : WebSocketEngine {
  override suspend fun open(url: String, headers: List<HttpHeader>): WebSocketConnection {
    return WebSocketConnectionImpl()
  }

  @Deprecated("Use open(String, List<HttpHeader>) instead.", ReplaceWith("open(url, headers.map { HttpHeader(it.key, it.value })", "com.apollographql.apollo3.api.http.HttpHeader"))
  @ApolloDeprecatedSince(v3_2_2)
  override suspend fun open(
      url: String,
      headers: Map<String, String>,
  ): WebSocketConnection = open(url, headers.map { HttpHeader(it.key, it.value) })
}

private class WebSocketConnectionImpl() : WebSocketConnection {

  override suspend fun receive(): String {
    return ""
  }

  override fun send(data: ByteString) {}

  override fun send(string: String) {}

  override fun close() {}

}
