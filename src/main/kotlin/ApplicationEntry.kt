import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

object ApplicationEntry {

  @JvmStatic
  fun main(args: Array<String>) {
    val config = createConfig()
    Lwjgl3Application(LazyMainApplicationRunner(), config)
  }

  private fun createConfig(): Lwjgl3ApplicationConfiguration {
    val config = Lwjgl3ApplicationConfiguration()
    config.setWindowedMode(1280, 768)
    config.useOpenGL3(true, 3, 2)
    config.setResizable(false)
    return config
  }

}