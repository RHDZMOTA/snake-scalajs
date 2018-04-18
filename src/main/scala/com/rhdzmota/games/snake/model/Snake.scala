package com.rhdzmota.games.snake.model

import javafx.scene.input.ScrollEvent.HorizontalTextScrollUnits

trait Snake {
  def age: Int
  def score: Int
}

case class Dead(cadaver: List[Position], age: Int, score: Int) extends Snake

case class Living(body: List[Position], lastMovement: Movement, lastRelevantMovement: Movement, foodTarget: Food,
                   age: Int, score: Int, moves: Int) extends Snake {

  def move(m: Movement)(implicit screenSize: ScreenSize): Snake = {
    val fixedMovement = avoidCollapsing(m)
    val lrm: Movement = if (lastMovement != Undefined) lastMovement else lastRelevantMovement

    if (moves == 0) Dead(body, age, score)
    else if (body contains nextPosition(fixedMovement)) Dead(body, age, score)
    else if (!screenSize.contains(nextPosition(fixedMovement))) Dead(body, age, score)
    else if (eats(fixedMovement)) Living(nextPosition(fixedMovement) :: body, fixedMovement, lrm,
      Food(different = body), age + 1, score + foodTarget.value, SnakeCreator.availableMoves)
    else Living(nextPosition(fixedMovement) :: body.init, fixedMovement, lrm,
      foodTarget, age + 1, score, moves - 1)
  }

  def nextPosition(m: Movement): Position = body.head + deltaPosition(m)

  def avoidCollapsing(m: Movement): Movement =  m match {
    case h: Horizontal => lastRelevantMovement match {
      case lrmh: Horizontal => if (h == lrmh) h else h.invert
      case lrmv: Vertical => h
      case _ => h
    }
    case v: Vertical => lastRelevantMovement match {
      case lrmh: Horizontal => v
      case lrmv: Vertical => if (v == lrmv) v else v.invert
      case _ => v
    }
    case _ => m
  }

  def deltaPosition(m: Movement): Position = m match {
    case Down => Position(0, 10)
    case Up => Position(0, -10)
    case Right => Position(10, 0)
    case Left => Position(-10, 0)
    case Undefined => deltaPosition(lastRelevantMovement)
  }

  def eats(m: Movement): Boolean = nextPosition(m).closeEnough(foodTarget.position, 7)

}

