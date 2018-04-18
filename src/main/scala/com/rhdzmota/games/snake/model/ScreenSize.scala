package com.rhdzmota.games.snake.model

case class ScreenSize(xmin: Int, xmax: Int, ymin: Int, ymax: Int) {
  def getRandomX: Int = (xmax * scala.math.random).toInt
  def getRandomY: Int = (ymax * scala.math.random).toInt
  def contains(pos: Position): Boolean = (xmin < pos.x) && (pos.x < xmax) && (ymin < pos.y) && (pos.y < ymax)
}
