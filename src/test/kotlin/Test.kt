import system.nodes.operators.AddNode
import system.sockets.InputSocket
import system.nodes.ValueNode

object Test {

  @JvmStatic
  fun main(args: Array<String>) {


    val valueA = ValueNode(constant = 1f)
    val valueB = ValueNode(constant = 2f)

    val add = AddNode()

    add.inOf("a").connect(valueA.outOf("x"))
    add.inOf("b").connect(valueB.outOf("x"))


    println(add.valueOf("sum"))

  }

}