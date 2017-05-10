package system


interface Node {

  fun label(): String

  fun inputs(): Collection<Socket>

  fun outputs(): Collection<Socket>

}