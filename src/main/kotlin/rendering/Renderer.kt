package rendering

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import ktx.app.clearScreen
import playground.Camera

class Renderer {

  private val shape = ShapeRenderer()

  fun update(camera: Camera) {
    shape.projectionMatrix.set(camera.projectionMatrix())
  }

  fun clear() = clearScreen(.125f, .125f, .125f, 1f)

  fun renderCircle(position: Vector2, radius: Float) = decorateLine {
    shape.circle(position.x, position.y, radius)
  }

  fun renderRectangle(rect: Rectangle) = decorateLine {
    shape.rect(rect.x, rect.y, rect.width, rect.height)
  }

  private fun decorateLine(f: () -> Unit) {
    shape.begin(ShapeRenderer.ShapeType.Line)
    f.invoke()
    shape.end()
  }

}