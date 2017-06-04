package system.container

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import system.nodes.Node

class Container(val node: Node) {

  val position = Vector2()
  private val width = 96f
  private val height = 16f + socketsStreakSize() * 32f

  fun elements() = listOf(
    Element("header", Rectangle(position.x, position.y - 24f, width, 24f)),
    Element("body", Rectangle(position.x, position.y, width, height)),
    Element("body", Rectangle(position.x, position.y, width, height))
  )

  fun rectangles() = elements().map { it.rectangle }

  fun wrappingRectangle(): Rectangle {
    val i = rectangles().iterator()
    val result = i.next()
    while (i.hasNext())
      result.merge(i.next())
    return result
  }

  private fun socketsStreakSize() = Math.max(node.inputs().size, node.outputs().size)

  data class Element(
    val name: String,
    val rectangle: Rectangle
  )

}