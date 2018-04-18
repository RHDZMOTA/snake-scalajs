package com.rhdzmota.games.snake.model

case class Position(x: Int, y: Int) {

  def add(other: Position): Position = Position(x + other.x, y + other.y)
  def neg: Position = Position(-x, -y)
  def +(other: Position): Position = this add other

  def sub(other: Position): Position = this + other.neg
  def -(other: Position): Position = this sub other

  def magnitude: Double = scala.math.sqrt(x*x + y*y)

  def distance(other: Position): Double = (this - other).magnitude

  def equivalent(other: Position): Boolean = (x == other.x) && (y == other.y)
  def closeEnough(other: Position, threshold: Double): Boolean = distance(other) < threshold

  def toList: List[Int] = List(x, y)

}

case object RandomPosition {
  def get(implicit screenSize: ScreenSize): Position = Position(screenSize.getRandomX, screenSize.getRandomY)
}