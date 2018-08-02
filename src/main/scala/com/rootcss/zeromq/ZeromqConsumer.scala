package com.rootcss.zeromq

import org.slf4j.LoggerFactory
import org.zeromq.ZMQ

class ZeromqConsumer(address: String) {
  private val threadCount = 1 // increase the count if data increases (usually 1 GB per thread)
  private val context = ZMQ.context(threadCount)
  private lazy val client = context.socket(ZMQ.SUB)
  private lazy val logger = LoggerFactory.getLogger(this.getClass)

  def subscribe(routingPrefix: String = ""): Unit = {
    client.connect(address)
    client.subscribe(routingPrefix.getBytes)
    logger.info(s"Subscribed to ${address} with prefix ${routingPrefix}")
  }

  def retrieve(): Array[Byte] = {
    client.recv(0)
  }

  def retrieveAsString(): String = {
    new String(retrieve(), "UTF-8")
  }

  def stop() {
    context.term()
  }

}
