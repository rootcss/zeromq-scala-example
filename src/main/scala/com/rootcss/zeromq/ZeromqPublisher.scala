package com.rootcss.zeromq

import org.slf4j.LoggerFactory
import org.zeromq.ZMQ

class ZeromqPublisher(address: String) {
  private val threadCount = 1 // increase the count if data increases (usually 1 GB per thread)
  private val context = ZMQ.context(threadCount)
  private lazy val server = context.socket(ZMQ.PUB)
  private lazy val logger = LoggerFactory.getLogger(this.getClass)

  def serve(): Unit = {
    logger.info(s"Binding the server to ${address}")
    server.bind(address)
  }

  def send(msg: Array[Byte]): Unit = {
    server.send(msg, 0)
  }

  def send(msg: String): Unit = {
    logger.info("Publishing: {}", msg)
    send(msg.getBytes)
  }

  def stop(): Unit = {
    context.term()
  }

}
