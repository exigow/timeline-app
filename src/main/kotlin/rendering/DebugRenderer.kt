package rendering

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import ktx.app.clearScreen
import playground.Camera

class DebugRenderer {

  private val shape = ShapeRenderer()

  fun update(camera: Camera) {
    shape.projectionMatrix.set(camera.projectionMatrix())
  }

  fun clear() = clearScreen(.25f, .25f, .25f, 1f)

  fun renderCircle(position: Vector2, radius: Float, color: Color = Color.WHITE, isFilled: Boolean = false)
    = decorate({ shapeCircle(position, radius) }, color, isFilled)

  fun renderRectangle(rect: Rectangle, color: Color = Color.WHITE, isFilled: Boolean = false)
    = decorate( { shapeRectangle(rect) }, color, isFilled)

  private fun decorate(f: () -> Unit, color: Color, isFilled: Boolean) {
    shape.color = color
    shape.begin(resolveType(isFilled))
    f.invoke()
    shape.end()
  }

  private fun shapeRectangle(rect: Rectangle) = shape.rect(rect.x, rect.y, rect.width, rect.height)

  private fun shapeCircle(position: Vector2, radius: Float) = shape.circle(position.x, position.y, radius)

  fun resolveType(isFilled: Boolean): ShapeRenderer.ShapeType {
    if (isFilled)
      return Filled
    return Line
  }

}