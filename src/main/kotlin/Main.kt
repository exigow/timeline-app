import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import ktx.math.minus
import ktx.math.plus
import playground.Camera
import rendering.ContainerRenderer
import rendering.DebugRenderer
import system.container.Container
import system.container.ContainerManager
import system.nodes.AddNode
import system.nodes.ValueNode

class Main {

  private val manager = ContainerManager()
  private val camera = Camera()
  private val renderer = DebugRenderer()
  private val containerRenderer = ContainerRenderer();

  init {
    Gdx.input.inputProcessor = object : InputAdapter() {

      private var movedContainer: Container? = null
      private val relativePointer = Vector2()

      override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (!manager.isHoveringAny(camera.mousePosition()))
          return true
        val hovered = manager.hoveredContainer(camera.mousePosition())!!
        val hoveredElement = hovered.elements().filter { it.rectangle.contains(camera.mousePosition()) }.first()
        if (hoveredElement.name === "header") {
          movedContainer = hovered
          relativePointer.set(movedContainer!!.position.minus(camera.mousePosition()))
          updateDragged()
        }
        if (hoveredElement.name === "delete") {
          manager.delete(hovered)
        }
        return true
      }

      override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        updateDragged()
        if (movedContainer != null)
          manager.prioritize(movedContainer!!)
        movedContainer = null
        return true
      }

      override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        updateDragged()
        return true
      }

      private fun updateDragged() {
        movedContainer?.position?.set(camera.mousePosition().plus(relativePointer))
      }

    }
  }

  init {
    manager.put(ValueNode(3f), -256f, -128f)
    manager.put(ValueNode(5f), -128f, 128f)
    manager.put(AddNode(), 256f, 0f)
  }

  fun loop() {
    renderer.update(camera)
    containerRenderer.update(camera)
    renderer.clear()
    manager.containers.forEach { containerRenderer.render(it) }

    manager.rectangles().forEach { renderer.renderRectangle(it) }
    if (manager.isHoveringAny(camera.mousePosition()))
      renderer.renderRectangle(manager.hoveredRectangle(camera.mousePosition()).inflate(4f))
  }

  private fun Rectangle.inflate(amount: Float) = Rectangle(x - amount, y - amount, width + amount * 2, height + amount * 2)

}
