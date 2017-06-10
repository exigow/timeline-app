package rendering

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import playground.Camera
import system.container.Container

class ContainerRenderer {

  private val batch = SpriteBatch()
  private val tex = Texture(Gdx.files.internal("data/template2.png"))
  private val headerRegion = generateRegion(0, 0, 256, 48)
  private val bodyRegion = generateRegion(0, 48, 256, 256)
  private val footerRegion = generateRegion(0, 304, 256, 16)

  private fun generateRegion(x: Int, y: Int, width: Int, height: Int): TextureRegion {
    val reg = TextureRegion(tex, x, y, width, height)
    reg.flip(false, true)
    return reg
  }

  fun update(camera: Camera) {
    batch.projectionMatrix.set(camera.projectionMatrix())
  }

  fun render(container: Container) {
    val renderCalls = mapOf(
      "header" to headerRegion,
      "body" to bodyRegion,
      "footer" to footerRegion
    )
    batch.begin()
    for ((name, region) in renderCalls) {
      val rect = container.rectangle(name)
      batch.draw(region, rect.x, rect.y, rect.width, rect.height)
    }
    batch.end()
  }

}