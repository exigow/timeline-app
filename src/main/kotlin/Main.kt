import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ktx.app.clearScreen
import system.nodes.AddNode
import system.nodes.ValueNode
import system.world.NodeManager

class Main {

  private val camera = OrthographicCamera()
  private val shape = ShapeRenderer()
  private val manager = NodeManager()

  init {
    camera.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
  }

  init {
    manager.put(ValueNode(3f), 128f, 128f)
    manager.put(ValueNode(5f), 256f, 128f)
    manager.put(AddNode(), 512f, 128f)
  }

  fun loop(delta: Float) {
    clearScreen(.125f, .125f, .125f, 1f)
    shape.projectionMatrix.set(camera.combined)
    shape.begin(ShapeRenderer.ShapeType.Line)
    shape.circle(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 4f)
    manager.states.forEach { it.debugRender(shape) }
    shape.end()
  }

}
