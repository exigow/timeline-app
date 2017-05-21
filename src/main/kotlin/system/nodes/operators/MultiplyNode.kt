package system.nodes.operators


class MultiplyNode : OperatorNode() {

  override fun name() = "Multiply"

  override fun operate(a: Float, b: Float) = a * b

}