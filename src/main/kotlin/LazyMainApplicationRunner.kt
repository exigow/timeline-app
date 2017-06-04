import com.kotcrab.vis.ui.VisUI
import ktx.app.KotlinApplication

class LazyMainApplicationRunner : KotlinApplication() {

  lateinit var main: Main

  override fun create() {
    VisUI.load()
    main = Main()
  }

  override fun render(delta: Float) = main.loop()

}