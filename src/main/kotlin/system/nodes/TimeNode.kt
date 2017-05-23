package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket


class TimeNode : Node {

  private val output = object : OutputSocket {

    override fun value() = 1f

    override fun story() = "time"

    override fun decoratedStory() = story()

  }

  override fun name() = "Time"

  override fun inputs(): Map<String, InputSocket> = emptyMap()

  override fun outputs(): Map<String, OutputSocket> = mapOf("output" to output)

}