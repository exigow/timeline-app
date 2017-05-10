import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.clearScreen
import ktx.vis.menuItem
import ktx.vis.popupMenu
import ktx.vis.subMenu

class Main {

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
    Gdx.input.setInputProcessor(stage)
    popup.showMenu(stage, 256f, 256f)
  }

  fun loop(delta: Float) {
    clearScreen(.125f, .125f, .125f, 1f)
    stage.act(delta)
    stage.draw()
  }

  private fun onClick(action: () -> Unit): EventListener {
    return object : ChangeListener() {

      override fun changed(event: ChangeEvent?, actor: Actor?) = action.invoke()

    }
  }

}
