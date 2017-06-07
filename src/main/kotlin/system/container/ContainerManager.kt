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

  fun delete(container: Container) {
    containers.remove(container)
  }

  fun rectangles() = containers.flatMap { it.rectangles() }

  fun isHoveringAny(pointer: Vector2) = rectangles().filter { rect -> rect.contains(pointer) }.any()

  fun hoveredRectangle(pointer: Vector2) = rectangles().filter { rect -> rect.contains(pointer) }.first()

  fun hoveredContainer(pointer: Vector2) = containers
    .filter { container -> container.rectangles().filter { rect -> rect.contains(pointer) }.any() }
    .firstOrNull()

  fun prioritize(container: Container) {
    val id = containers.indexOf(container)
    containers.removeAt(id)
    containers.add(container)
    containers.reverse()
  }

}