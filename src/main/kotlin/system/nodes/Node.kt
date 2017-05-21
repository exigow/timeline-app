package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket

interface Node {

  fun name(): String

  fun inputs(): Map<String, InputSocket>

  fun outputs(): Map<String, OutputSocket>

  fun valueOf(name: String) = outputs()[name]!!.value()

  fun inputOf(name: String) = inputs()[name]!!

  fun outputOf(name: String) = outputs()[name]!!

}