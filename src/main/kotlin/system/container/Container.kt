package system.container

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import system.nodes.Node

class Container(private val node: Node) {

  val position = Vector2()
  private val width = 96f
  private val height = 16f + socketsStreakSize() * 32f

  fun elements() = mapOf(
    "header" to Rectangle(position.x, position.y - 24f, width, 24f),
    "body" to Rectangle(position.x, position.y, width, height)
  )

  fun rectangles() = elements().map { it.value }

  private fun socketsStreakSize() = Math.max(node.inputs().size, node.outputs().size)

}