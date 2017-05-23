import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.app.clearScreen
import playground.Camera
import system.nodes.AddNode
import system.nodes.ValueNode
import system.world.NodeManager

class Main {

  private val shape = ShapeRenderer()
  private val manager = NodeManager()
  private val camera = Camera()

  init {
    manager.put(ValueNode(3f), 128f, 128f)
    manager.put(ValueNode(5f), 384f, 128f)
    manager.put(AddNode(), 256f, 256f)
  }

  fun loop(delta: Float) {
    clearScreen(.125f, .125f, .125f, 1f)
    shape.projectionMatrix.set(camera.projectionMatrix())
    shape.begin(ShapeRenderer.ShapeType.Line)
    shape.circle(camera.mousePosition().x, camera.mousePosition().y, 4f)
    manager.states.flatMap { it.allRectangles() }.
      forEach { shape.rect(it.x, it.y, it.width, it.height) }
    val hovered = manager.states.flatMap { it.allRectangles() }
      .filter { it.contains(camera.mousePosition()) }
      .firstOrNull()
    if (hovered != null)
      shape.rect(hovered.x - 4f, hovered.y - 4f, hovered.width + 8f, hovered.height + 8f)
    shape.end()
  }

}
