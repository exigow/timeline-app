package system.nodes

import system.sockets.InputSocket
import system.sockets.OutputSocket

interface Node {

  fun name(): String

  fun inputs(): Map<String, InputSocket>

  fun outputs(): Map<String, OutputSocket>

  fun valueOf(name: String) = outputs()[name]!!.value()

  fun get(name: String) = outputs()[name]!!

  fun get(): OutputSocket {
    if (outputs().size == 1)
      return outputs().values.iterator().next()
    throw RuntimeException()
  }

  fun connect(pair: Pair<String, OutputSocket>) = inputs()[pair.first]!!.connect(pair.second)

}