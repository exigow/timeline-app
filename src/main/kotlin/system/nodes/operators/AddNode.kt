package system.nodes.operators

import system.nodes.Node
import system.sockets.InputSocket
import system.sockets.OutputSocket


class AddNode : Node {

  private val inputA = InputSocket()
  private val inputB = InputSocket()
  private val sum = object : OutputSocket {

    override fun value() = inputA.value() + inputB.value()

  }

  override fun inputs() = mapOf("a" to inputA, "b" to inputB)

  override fun outputs() = mapOf<String, OutputSocket>("sum" to sum)

}