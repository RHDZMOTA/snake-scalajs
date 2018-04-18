package com.rhdzmota.games.snake.jsUtil.canvas

import com.rhdzmota.games.snake.model.{Food, Position, Snake}
import org.scalajs.dom.raw.CanvasRenderingContext2D

trait ImplicitDrawing[-A] {
  def draw(value: A)(implicit ctx: CanvasRenderingContext2D): Unit
}

object ImplicitDrawingOps {

  implicit object ImplicitFoodDrawing extends ImplicitDrawing[Food] {
    override def draw(value: Food)(implicit ctx: CanvasRenderingContext2D): Unit = FoodDrawing(value).draw
  }

  implicit object ImplicitSnakeDrawing extends ImplicitDrawing[Snake] {
    override def draw(value: Snake)(implicit ctx: CanvasRenderingContext2D): Unit = SnakeDrawing(value).draw
  }

}