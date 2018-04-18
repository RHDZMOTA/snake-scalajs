package com.rhdzmota.games.snake.model

class Food(val position: Position, val value: Int, val active: Boolean)

object Food {

  def apply(different: List[Position])(implicit screenSize: ScreenSize): Food = {
    def recPosition(position: Position): Position =
      if (different.foldRight(false) {(pos: Position, has: Boolean) => pos equivalent position}) recPosition(RandomPosition.get)
      else position
    new Food(recPosition(RandomPosition.get), 1, true)
  }
}
