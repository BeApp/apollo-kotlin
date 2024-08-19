package com.apollographql.apollo3.mockserver

import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint8Array
import kotlin.coroutines.suspendCoroutine

actual class MockServer actual constructor(override val mockServerHandler: MockServerHandler) : MockServerInterface {

  private val requests = mutableListOf<MockRequest>()

  private var url: String? = null


  override suspend fun url() = url ?: suspendCoroutine { cont ->
    url = "http://localhost:/"
  }

  override fun enqueue(mockResponse: MockResponse) {
    (mockServerHandler as? QueueMockServerHandler)?.enqueue(mockResponse)
        ?: error("Apollo: cannot call MockServer.enqueue() with a custom handler")
  }

  override fun takeRequest(): MockRequest {
    return requests.removeFirst()
  }

  override suspend fun stop() = suspendCoroutine<Unit> { cont ->
  }

  private fun Uint8Array.asByteArray(): ByteArray {
    return Int8Array(buffer, byteOffset, length).unsafeCast<ByteArray>()
  }

}
