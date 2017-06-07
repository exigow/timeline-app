package system.container

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import system.nodes.Node

class Container(val node: Node) {

  val position = Vector2()
  private val width = 128f
  private val height = 128f
  private val headerHeight = 24f

  fun elements() = listOf(
    Element("header", Rectangle(position.x, position.y, width, headerHeight)),
    Element("body", Rectangle(position.x, position.y + headerHeight, width, height)),
    Element("delete", Rectangle(position.x + width - headerHeight, position.y, headerHeight, headerHeight))
  ).reversed()

  fun rectangles() = elements().map { it.rectangle }

  fun wrappingRectangle(): Rectangle {
    val i = rectangles().iterator()
    val result = i.next()
    while (i.hasNext())
      result.merge(i.next())
    return result
  }

  data class Element(
    val name: String,
    val rectangle: Rectangle
  )

}