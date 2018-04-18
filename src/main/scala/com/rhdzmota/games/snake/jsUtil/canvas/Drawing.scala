package com.rhdzmota.games.snake.jsUtil.canvas

import com.rhdzmota.games.snake.model._
import org.scalajs.dom.CanvasRenderingContext2D

sealed trait Drawing[A] {
  def value: A
  def width: Int
  def height: Int
  def color: String
  def draw(implicit ctx: CanvasRenderingContext2D): Unit
}

case class PositionDrawing(value: Position, color: String) extends Drawing[Position] {
  val width: Int = 10
  val height: Int = 10
  override def draw(implicit ctx: CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = color
    ctx.fillRect(value.x, value.y, width, height)
  }
}

case class FoodDrawing(value: Food) extends Drawing[Food] {
  val width: Int = 10
  val height: Int = 10
  val color: String = "white"
  override def draw(implicit ctx: CanvasRenderingContext2D): Unit = {
    PositionDrawing(value.position, color).draw
  }
}

case class SnakeDrawing(value: Snake) extends Drawing[Snake] {
  val width: Int  = 10
  val height: Int = 10
  val color: String = value match {
    case survivor: Living => "white"
    case looser: Dead     => "red"
  }

  override def draw(implicit ctx: CanvasRenderingContext2D): Unit = value match {
    case survivor: Living =>
      survivor.body foreach {PositionDrawing(_, color).draw}
      FoodDrawing(survivor.foodTarget).draw
    case looser: Dead     =>
      looser.cadaver foreach {PositionDrawing(_, color).draw}
  }
}