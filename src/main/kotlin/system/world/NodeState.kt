package system.world

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import system.nodes.Node

class NodeState(private val node: Node) {

  val position = Vector2()

  fun bodyRectangle() = Rectangle(position.x + 8f, position.y, width() - 16f, height())

  fun headerRectangle() = Rectangle(position.x, position.y - 24f, width(), 24f)

  fun inputSocketNamesToRectangles() = calculateSocketNamesToRectangles(node.inputs().map { it.key }, 0f)

  fun outputSocketNamesToRectangles() = calculateSocketNamesToRectangles(node.outputs().map { it.key }, width())

  private fun calculateSocketNamesToRectangles(names: List<String>, additionalWidth: Float): Map<String, Rectangle>  {
    var offset = -1f
    val socketSize = 16f
    return names
      .associate {
        offset += 1f
        it to Rectangle(position.x + additionalWidth - socketSize / 2f, position.y - socketSize / 2f + offset * socketSize * 2 + 24f, socketSize, socketSize)
      }
  }

  private fun width() = 128f

  private fun height() = 16f + socketsStreakSize() * 32f

  private fun socketsStreakSize() = Math.max(node.inputs().size, node.outputs().size)

  fun allRectangles() = listOf(bodyRectangle()) + headerRectangle() + inputSocketNamesToRectangles().map { it.value } + outputSocketNamesToRectangles().map { it.value }

}