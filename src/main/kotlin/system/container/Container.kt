package system.container

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import system.nodes.Node

class Container(val node: Node) {

  val position = Vector2()
  private val width = 256f
  private val bodyHeight = 256f
  private val headerHeight = 48f
  private val footerHeight = 16f

  fun elements() = listOf(
    Element("header", Rectangle(position.x, position.y, width, headerHeight)),
    Element("body", Rectangle(position.x, position.y + headerHeight, width, bodyHeight)),
    Element("footer", Rectangle(position.x, position.y + headerHeight + bodyHeight, width, footerHeight)),
    Element("delete", Rectangle(position.x + width - headerHeight, position.y, headerHeight, headerHeight))
  ).reversed()

  fun rectangles() = elements().map { it.rectangle }

  fun rectangle(name: String) = elements().filter { it.name == name }.map { it.rectangle }.first()

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