package system.world

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import system.nodes.Node
import ktx.math.*
import system.sockets.InputSocket

class NodeState(private val node: Node) {

  val position = Vector2()
  
  fun debugRender(shape: ShapeRenderer) {
    val width = 64f
    val height = 64f + socketsStreakSize() * 16f
    shape.rect(position.x, position.y, width, height)
    var ii = 0
    for (input in node.inputs())
      shape.circle(position.x, position.y + ii++ * 16f, 8f)
    var io = 0
    for (input in node.outputs())
      shape.circle(position.x + width, position.y + io++ * 16f, 8f)
  }

  /*fun inputSocketPlaces(): Map<InputSocket, Vector2> {
    val mut = Vector2(0f, 0f)
    return node.inputs().values.associate {
      mut.y += 16f
      it to position + mut
    }
  }*/

  private fun socketsStreakSize() = Math.max(node.inputs().size, node.outputs().size)

}