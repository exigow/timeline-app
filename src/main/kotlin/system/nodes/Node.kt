package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket

interface Node {

  fun inputs(): Map<String, InputSocket>

  fun outputs(): Map<String, OutputSocket>

  fun valueOf(name: String) = outputs()[name]!!.value()

  fun inOf(name: String) = inputs()[name]!!

  fun outOf(name: String) = outputs()[name]!!

}