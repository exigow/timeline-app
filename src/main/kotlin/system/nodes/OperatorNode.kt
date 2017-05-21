package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket


abstract class OperatorNode : Node {

  private val inputA = InputSocket()
  private val inputB = InputSocket()
  private val result = object : OutputSocket {

    override fun value() = operate(inputA.value(), inputB.value())

    override fun story() = "${name()} ${inputA.connectionStory()}, ${inputB.connectionStory()}"

  }

  override fun inputs() = mapOf("a" to inputA, "b" to inputB)

  override fun outputs() = mapOf<String, OutputSocket>("result" to result)

  abstract fun operate(a: Float, b: Float): Float

}