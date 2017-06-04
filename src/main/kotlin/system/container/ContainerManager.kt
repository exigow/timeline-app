package system.container

import com.badlogic.gdx.math.Vector2
import system.nodes.Node
import java.util.*

class ContainerManager {

  val containers: MutableList<Container> = ArrayList()

  fun put(node: Node, x: Float, y: Float) {
    val state = Container(node)
    state.position.set(x, y)
    containers.add(state)
  }

  fun rectangles() = containers.flatMap { it.rectangles() }

  fun isHoveringAny(pointer: Vector2) = rectangles().filter { rect -> rect.contains(pointer) }.any()

  fun hoveringRectangle(pointer: Vector2) = rectangles().filter { rect -> rect.contains(pointer) }.first()

}