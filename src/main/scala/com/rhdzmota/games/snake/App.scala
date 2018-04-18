package com.rhdzmota.games.snake

import org.querki.jquery._
import org.scalajs.dom
import com.rhdzmota.games.snake.model._
import org.scalajs.dom.ext.KeyCode
import org.scalajs.dom.html.Canvas

import com.rhdzmota.games.snake.jsUtil.canvas.ImplicitDrawingOps._
import com.rhdzmota.games.snake.jsUtil.canvas.DrawingSyntax._

object App {

  var currentSnake: Option[Snake] = None
  var canvas: Option[Canvas] = None
  var nextMove: Movement = Down

  def main(args: Array[String]): Unit = {

    // Configure ScreenSize
    val xmax = (0.97 * dom.window.innerWidth).toInt
    val ymax = (0.97 * dom.window.innerHeight).toInt
    implicit val screenSize: ScreenSize = ScreenSize(0, xmax, 0, ymax)
    implicit val ctx: dom.CanvasRenderingContext2D = setup

    currentSnake = Some(SnakeCreator.create())
    scala.scalajs.js.timers.setInterval(30) {gameLoop}

  }

  def setup(implicit screenSize: ScreenSize): dom.CanvasRenderingContext2D = {
    canvas = Some(dom.document.createElement("canvas").asInstanceOf[Canvas])

    // Adjust to the window
    for (cnv <- canvas) yield {
      cnv.width = screenSize.xmax
      cnv.height = screenSize.ymax
      $("body").append(cnv)
    }

    dom.document.addEventListener("keydown", (event: dom.KeyboardEvent) => event.keyCode match {
      case KeyCode.Down  => nextMove = Down
      case KeyCode.Up => nextMove = Up
      case KeyCode.Right => nextMove = Right
      case KeyCode.Left => nextMove = Left
      case _ => Undefined
    })

    // Create context
    canvas.get.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  }

  def drawCanvas(implicit screenSize: ScreenSize, ctx: dom.CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = "black"
    ctx.fillRect(screenSize.xmin, screenSize.ymin, screenSize.xmax, screenSize.ymax)
  }

  def gameLoop(implicit screenSize: ScreenSize, ctx: dom.CanvasRenderingContext2D): Unit = currentSnake match {
    case Some(snake) => snake match {
      case looser: Dead =>
        drawCanvas
        looser.draw
        currentSnake = Some(looser)
      case survivor: Living =>
        drawCanvas
        survivor.draw
        currentSnake = Some(survivor.move(nextMove))
    }
    case None => println("You shouldn't see this in the console.")
  }



}
