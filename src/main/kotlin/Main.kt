import com.badlogic.gdx.math.Rectangle
import playground.Camera
import rendering.Renderer
import system.container.ContainerManager
import system.nodes.AddNode
import system.nodes.ValueNode

class Main {

  private val manager = ContainerManager()
  private val camera = Camera()
  private val renderer = Renderer()

  init {
    manager.put(ValueNode(3f), -128f, -128f)
    manager.put(ValueNode(5f), -128f, 128f)
    manager.put(AddNode(), 128f, 0f)
  }

  fun loop() {
    renderer.update(camera)
    renderer.clear()
    renderer.renderCircle(camera.mousePosition(), 4f)
    manager.rectangles().forEach { renderer.renderRectangle(it) }
    if (manager.isHoveringAny(camera.mousePosition()))
      renderer.renderRectangle(manager.hoveredRectangle(camera.mousePosition()).inflate(-4f))
    val hovered = manager.hoveredContainer(camera.mousePosition())
    if (hovered != null) {
      renderer.renderRectangle(hovered.wrappingRectangle().inflate(4f))
      val hoveredElement = hovered.elements().filter { it.rectangle.contains(camera.mousePosition()) }.first()
      println(hovered.node.name() + ", " + hoveredElement.name)
    }
  }

  private fun Rectangle.inflate(amount: Float) = Rectangle(x - amount, y - amount, width + amount * 2, height + amount * 2)

}
