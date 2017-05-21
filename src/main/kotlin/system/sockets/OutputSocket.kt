package system.sockets

interface OutputSocket {

  fun value(): Float

  fun story(): String

  fun decoratedStory(): String = "(${story()} -> ${value()})"

}