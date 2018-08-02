import com.rootcss.zeromq.{ZeromqConsumer, ZeromqPublisher}
import org.joda.time.DateTime
import org.slf4j.LoggerFactory

object RoutingPublisher extends App {
  val publisher = new ZeromqPublisher("tcp://*:5000")
  publisher.serve()
  while (true) {
    publisher.send("Hello World at " + DateTime.now.toString())
    publisher.send("[1] Hello World at " + DateTime.now.toString())
    publisher.send("[2] Hello World at " + DateTime.now.toString())
    Thread.sleep(1000)
  }
}

object RoutingConsumer1 extends App {
  private lazy val logger = LoggerFactory.getLogger(this.getClass)
  val consumer = new ZeromqConsumer("tcp://127.0.0.1:5000")
  consumer.subscribe("[1]")
  while (true) {
    logger.info(consumer.retrieveAsString())
  }
}

object RoutingConsumer2 extends App {
  private lazy val logger = LoggerFactory.getLogger(this.getClass)
  val consumer = new ZeromqConsumer("tcp://127.0.0.1:5000")
  consumer.subscribe("[2]")
  while (true) {
    logger.info(consumer.retrieveAsString())
  }
}