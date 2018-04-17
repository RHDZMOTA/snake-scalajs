package com.rhdzmota.games.snake.model

case class ScreenSize(xmin: Int, xmax: Int, ymin: Int, ymax: Int) {
  def getRandomX: Int = ((xmax - xmin) * scala.math.random).toInt
  def getRandomY: Int = ((ymax - ymin) * scala.math.random).toInt
}
