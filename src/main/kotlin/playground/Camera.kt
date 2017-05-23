package playground

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.math.*


class Camera {

  val cam: OrthographicCamera
  private val eye = Vector3(0f, 0f, 1f)
  private val target = Vector3(eye)

  init {
    cam = OrthographicCamera()
    cam.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    cam.position.set(0f, 0f, 0f)
    cam.update()
  }

  fun projectionMatrix(): Matrix4 = cam.combined.cpy()

  fun screenMatrix(): Matrix4 {
    val m = OrthographicCamera()
    m.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    return m.combined
  }

  fun update(delta: Float) {
    //target.z = FastMath.clamp(target.z, .5f, 16f) todo
    val planeFactor = 16f
    val zoomFactor = 12f
    fun limit(f: Float) = Math.min(f * delta, 1f)
    eye.x += (target.x - eye.x) * limit(planeFactor)
    eye.y += (target.y - eye.y) * limit(planeFactor)
    eye.z += (target.z - eye.z) * limit(zoomFactor)
    updateCameraPosition()
  }

  fun lookAt(where: Vector2) {
    target.x = where.x
    target.y = where.y
  }

  fun zoomTo(distance: Float) {
    target.z = distance
  }

  fun zoomRelative(amount: Float) {
    target.z += amount
  }

  fun moveRelative(vector: Vector2) {
    target.add(vector.x, vector.y, 0f)
  }

  fun unproject(position: Vector2): Vector2 {
    val ray = cam.getPickRay(position.x, position.y)
    val plane = Plane()
    plane.set(0f, 0f, 1f, 0f)// the xy plane with direction z facing screen
    plane.d=-10f//***** the depth in 3d for the coordinates
    val yourVector3Position = Vector3()
    Intersector.intersectRayPlane(ray, plane, yourVector3Position)
    return Vector2(yourVector3Position.x, yourVector3Position.y)
  }

  private fun updateCameraPosition() {
    cam.position.set(eye.x, eye.y, eye.z * 256f)
    cam.lookAt(eye.x, eye.y, 0f)
    cam.update()
  }

  fun position() = Vector2(target.x, target.y)

  fun positionEye() = Vector2(eye.x, eye.y)

  fun mouseScreenPosition() = Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())

  fun mousePosition() = unproject(mouseScreenPosition())

  fun scaledClickRadius() = 16f * renderingScale() // todo: remove this / inline

  fun renderingScale() = eye.z

  fun normalizedRenderingScale() = Math.max(1f, renderingScale())

  fun worldVisibilityRectangle(border: Float = 0f): Rectangle {
    val z = .32f * eye.z
    val w = cam.viewportWidth + border
    val h = cam.viewportHeight + border
    val x = eye.x - (w / 2f * z)
    val y = eye.y - (h / 2f * z)
    return Rectangle(x, y, w * z, h * z)
  }

}
