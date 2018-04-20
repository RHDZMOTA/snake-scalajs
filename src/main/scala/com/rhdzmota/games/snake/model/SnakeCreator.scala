package com.rhdzmota.games.snake.model

object SnakeCreator {

  def initialX(implicit screenSize: ScreenSize): Int = screenSize.xmax / 4
  def initialY(implicit  screenSize: ScreenSize): Int = screenSize.ymax / 4
  def initialBody(implicit screenSize: ScreenSize): List[Position] =
    (0 to 3).map((dy: Int) => Position(initialX, initialY + 10 * dy)).toList.reverse
  val availableMoves = 1000

  def create()(implicit screenSize: ScreenSize) = Living(initialBody, Down, Down, Food(initialBody), 0, 0, availableMoves)
}
