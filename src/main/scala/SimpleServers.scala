import com.rootcss.zeromq._
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

object Publisher extends App {
  val publisher = new ZeromqPublisher("tcp://*:5000")
  publisher.serve()
  while (true) {
    publisher.send("Hello World at " + DateTime.now.toString())
    Thread.sleep(1000)
  }
}

object Consumer extends App {
  private lazy val logger = LoggerFactory.getLogger(this.getClass)
  val consumer = new ZeromqConsumer("tcp://127.0.0.1:5000")
  consumer.subscribe()
  while (true) {
    logger.info(consumer.retrieveAsString())
  }
}
