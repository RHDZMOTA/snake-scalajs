package com.rhdzmota.games.snake.model

case class Position(x: Int, y: Int) {

  def add(other: Position): Position = Position(x + other.x, y + other.y)
  def neg: Position = Position(-x, -y)
  def +(other: Position): Position = this add other

  def sub(other: Position): Position = this + other.neg
  def -(other: Position): Position = this sub other

  def toList: List[Int] = List(x, y)
}


object Position {

  def apply(x: Int, y: Int): Position = new Position(x, y)

  def random(implicit screenSize: ScreenSize): Position =
    new Position(screenSize.getRandomX, screenSize.getRandomY)
}
