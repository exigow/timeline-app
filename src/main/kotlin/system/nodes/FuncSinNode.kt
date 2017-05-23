package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket


class FuncSinNode : Node {

  private val input = InputSocket()
  private val output = object : OutputSocket {

    override fun value() = Math.sin(inputVal().toDouble()).toFloat()

    override fun story() = "sin of ${inputVal()}"

  }

  override fun name() = "sin(x) func."

  override fun inputs(): Map<String, InputSocket> = mapOf("x" to input)

  override fun outputs(): Map<String, OutputSocket> = mapOf("output" to output)

  private fun inputVal() = input.value()

}