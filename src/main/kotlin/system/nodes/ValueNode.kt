package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket

class ValueNode(private val constant: Float) : Node {

  private val outputX = object : OutputSocket {

    override fun value() = constant

  }

  override fun inputs() = emptyMap<String, InputSocket>()

  override fun outputs() = mapOf<String, OutputSocket>("x" to outputX)

}