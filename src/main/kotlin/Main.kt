import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.clearScreen
import ktx.vis.menuItem
import ktx.vis.popupMenu
import ktx.vis.subMenu

class Main {

  val font14 = BitmapFont(Gdx.files.internal("data/calibri-14.fnt"))
  val font12 = BitmapFont(Gdx.files.internal("data/calibri-12.fnt"))
  val background = Texture(Gdx.files.internal("data/background.png"))
  val shape = ShapeRenderer()
  val batch = SpriteBatch()
  val stage = Stage(ScreenViewport())

  fun loop(delta: Float) {
    clearScreen(.125f, .125f, .125f, 1f)

    shape.projectionMatrix.set(stage.camera.combined)
    batch.projectionMatrix.set(stage.camera.combined)

    drawBackground()

    //renderNode("3-ch to RGB", 128f, 128f, 96f, 128f)

    //renderNode("Add", 256f, 192f, 96f, 96f)
    //renderNode("Add", 64f, 64f, 96f, 128f)

    stage.act(delta)
    stage.draw()
  }

  /*private fun renderNode(label: String, x: Float, y: Float, width: Float, height: Float) {
    val names = listOf("RGB", "A", "B", "C")
    batch.begin()
    font14.setColor(.66f, .66f, .66f, 1f)
    font14.draw(batch, label, x, y + height - 5, width, Align.center, false)
    batch.end()
  }*/

  private fun drawBackground() {
    batch.begin()
    batch.draw(background, 0f, 0f)
    batch.end()
  }

}
