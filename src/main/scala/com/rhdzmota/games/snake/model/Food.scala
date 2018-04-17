package com.rhdzmota.games.snake.model

case class Food(position: Position, value: Int)

object Food {

  val screenSize: ScreenSize(0, 10, 0, 10)

  def apply(): Food = new Food(Position.random, 1)
}
