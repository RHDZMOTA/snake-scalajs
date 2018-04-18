package com.rhdzmota.games.snake.jsUtil.canvas

import org.scalajs.dom.raw.CanvasRenderingContext2D

object DrawingSyntax {

  implicit class DrawingOps[A](value: A) {
    def draw(implicit implicitDrawing: ImplicitDrawing[A], ctx: CanvasRenderingContext2D): Unit =
      implicitDrawing draw value
  }

}
