package system


class TestNode : Node {

  override fun label() = "Test"

  override fun outputs() = emptyList<Socket>()

  override fun inputs() = emptyList<Socket>()

}