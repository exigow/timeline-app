package system.nodes


class AddNode : OperatorNode() {

  override fun name() = "Add"

  override fun operate(a: Float, b: Float) = a + b

}