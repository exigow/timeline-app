import system.nodes.AddNode
import system.nodes.MultiplyNode
import system.nodes.ValueNode

object SomeTests {

  @JvmStatic
  fun main(args: Array<String>) {

    val addNode = AddNode()
    addNode.connect("a" to ValueNode(constant = 3f).output())
    addNode.connect("b" to ValueNode(constant = 4f).output())

    val mulNode = MultiplyNode()
    mulNode.connect("a" to addNode.output())
    mulNode.connect("b" to ValueNode(constant = 5f).output())

    println(mulNode.output().decoratedStory())
  }

}