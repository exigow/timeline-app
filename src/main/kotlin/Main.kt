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
  val patch = NinePatch(Texture(Gdx.files.internal("data/node-background-shadowed.png")), 16, 16, 16, 16)
  val socket = Texture(Gdx.files.internal("data/socket.png"))
  val background = Texture(Gdx.files.internal("data/background.png"))
  val shape = ShapeRenderer()
  val batch = SpriteBatch()
  val stage = Stage(ScreenViewport())
  val popup = popupMenu {
    menuItem("First Item")
    val i = menuItem("Second Item")
    i.addListener(onClick { print("Clicked Second Item") } )
    menuItem("Third Item") {
      subMenu {
        menuItem("SubMenu Item")
      }
    }
  }

  init {
    Gdx.input.inputProcessor = stage
    stage.addListener(object : InputListener() {

      override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
        if (button != Input.Buttons.RIGHT)
          return false
        popup.showMenu(stage, x, y)
        return true
      }

    })
  }

  fun loop(delta: Float) {
    clearScreen(.125f, .125f, .125f, 1f)

    shape.projectionMatrix.set(stage.camera.combined)
    batch.projectionMatrix.set(stage.camera.combined)

    drawBackground()

    renderNode("3-ch to RGB", 128f, 128f, 96f, 128f)

    renderNode("Add", Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 96f, 96f)
    //renderNode("Add", 64f, 64f, 96f, 128f)

    stage.act(delta)
    stage.draw()
  }

  private fun renderNode(label: String, x: Float, y: Float, width: Float, height: Float) {
    val names = listOf("RGB", "A", "B", "C")
    for (i in 0..2)
      drawSocket(names[i], x + width - 3, y + 8 + ((i + 1) * 24))

    drawNodePatch(x, y, width, height)

    batch.begin()
    font14.setColor(.66f, .66f, .66f, 1f)
    font14.draw(batch, label, x, y + height - 5, width, Align.center, false)
    batch.end()
  }

  fun drawNodePatch(x: Float, y: Float, width: Float, height: Float) {
    batch.begin()
    patch.draw(batch, x, y, width, height)
    batch.end()
  }

  fun drawSocket(symbol: String, x: Float, y: Float) {
    batch.begin()
    batch.draw(socket, x, y - 8)
    font12.setColor(.66f, .66f, .66f, 1f)
    font12.draw(batch, symbol, x + 2, y + 2, 32f, Align.left, false)
    batch.end()
  }

  private fun drawRect(x: Float, y: Float, width: Float, height: Float) {
    shape.begin(ShapeRenderer.ShapeType.Line)
    shape.rect(x, y, width, height)
    shape.end()
  }

  private fun drawBackground() {
    batch.begin()
    batch.draw(background, 0f, 0f)
    batch.end()
  }

  //private fun drawCircle(x: Float, y: Float)

  private fun onClick(action: () -> Unit): EventListener {
    return object : ChangeListener() {

      override fun changed(event: ChangeEvent?, actor: Actor?) = action.invoke()

    }
  }

}
