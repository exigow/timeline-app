import system.nodes.ValueNode
import system.nodes.operators.AddNode
import system.nodes.operators.MultiplyNode

object Test {

  @JvmStatic
  fun main(args: Array<String>) {

    val add = AddNode()
    add.inputOf("a").connect(ValueNode(constant = 3f).outputOf("x"))
    add.inputOf("b").connect(ValueNode(constant = 4f).outputOf("x"))

    val mul = MultiplyNode()
    mul.inputOf("a").connect(add.outputOf("result"))
    mul.inputOf("b").connect(ValueNode(constant = 5f).outputOf("x"))

    println(mul.outputOf("result").decoratedStory())
  }

}