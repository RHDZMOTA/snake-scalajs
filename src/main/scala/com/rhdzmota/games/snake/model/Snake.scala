package com.rhdzmota.games.snake.model


case class Snake(body: List[Position], previousMovement: Movement, foodTarget: Food, age: Int, alive: Boolean) {

  def move(movement: Movement): Snake = ???

  def nextPosition(m: Movement): Position = m match {
    case Up => ???
    case Down => ???
    case Right => ???
    case Left => ???
    case Undefined => ???
  }

  def hasEaten(): Boolean = ???

}



object Snake {

  val initialX = 0
  val initialY = 0
  val initialBody: List[Position] = (0 to 3).map((dx: Int) => Position(initialX + dx, initialY)).toList

  def apply(body: List[Position], previousMovement: Movement, foodTarget: Food, age: Int, alive: Boolean): Snake =
    new Snake(body, previousMovement, foodTarget, age, alive)

  def apply(): Snake = new Snake(initialBody, Down, Food(), 0, true)
}