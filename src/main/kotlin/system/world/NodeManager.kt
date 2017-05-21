package system.world

import system.nodes.Node
import java.util.*

class NodeManager {

  val states: MutableList<NodeState> = ArrayList()

  fun put(node: Node, x: Float, y: Float) {
    val state = NodeState(node)
    state.position.set(x, y)
    states.add(state)
  }

}