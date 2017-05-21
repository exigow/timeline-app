import system.nodes.AddNode
import system.nodes.MultiplyNode
import system.nodes.ValueNode

object SomeTests {

  @JvmStatic
  fun main(args: Array<String>) {

    val addNode = AddNode()
    addNode.connect("a" to ValueNode(constant = 3f).get())
    addNode.connect("b" to ValueNode(constant = 4f).get())

    val mulNode = MultiplyNode()
    mulNode.connect("a" to addNode.get())
    mulNode.connect("b" to ValueNode(constant = 5f).get())

    println(mulNode.get().decoratedStory())
  }

}