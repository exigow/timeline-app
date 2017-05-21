package system.sockets


class InputSocket(private val whenDisconnectedValue: Float = 0f) {

  private var connectedTo: OutputSocket? = null

  fun connect(other: OutputSocket) {
    connectedTo = other
  }

  fun disconnect() {
    connectedTo = null
  }

  fun value(): Float {
    if (connectedTo == null)
      return whenDisconnectedValue
    return connectedTo!!.value()
  }

  fun connectionStory(): String {
    if (connectedTo == null)
      return "disconnected"
    return connectedTo!!.decoratedStory()
  }

}