package com.rhdzmota.games.snake

import org.querki.jquery._
import org.scalajs.dom
import com.rhdzmota.games.snake.model._
import org.scalajs.dom.ext.KeyCode
import org.scalajs.dom.html.Canvas

object App {

  var currentSnake: Option[Living] = None
  var canvas: Option[Canvas] = None
  var nextMove: Movement = Down

  def main(args: Array[String]): Unit = {

    // Configure ScreenSize
    val xmax = (0.97 * dom.window.innerWidth).toInt
    val ymax = (0.97 * dom.window.innerHeight).toInt
    implicit val screenSize: ScreenSize = ScreenSize(0, xmax, 0, ymax)

    val ctx = setup

    currentSnake = Some(SnakeCreator.create())

    drawCanvas(ctx)

    scala.scalajs.js.timers.setInterval(50) {drawWrapper(ctx)}
    //gameLoop(1000, snake, ctx)

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

  def drawCanvas(ctx: dom.CanvasRenderingContext2D)(implicit screenSize: ScreenSize): Unit = {
    ctx.fillStyle = "black"
    ctx.fillRect(screenSize.xmin, screenSize.ymin, screenSize.xmax, screenSize.ymax)
  }

  def drawSnake(snake: Snake, ctx: dom.CanvasRenderingContext2D): Unit = {
    def drawSnakeUnit(pos: Position, color: String): Unit = {
      ctx.fillStyle = color
      ctx.fillRect(pos.x, pos.y, 10, 10)
    }
    snake match {
      case survivor: Living => survivor.body.foreach(drawSnakeUnit(_, "white"))
      case looser: Dead => looser.cadaver.foreach(drawSnakeUnit(_, "red"))
    }
  }

  def drawFood(food: Food, ctx: dom.CanvasRenderingContext2D): Unit = {
    ctx.fillStyle = "white"
    ctx.fillRect(food.position.x, food.position.y, 10, 10)
  }

  def draw(snake: Snake, ctx: dom.CanvasRenderingContext2D)(implicit screenSize: ScreenSize): Snake = snake match {
    case looser: Dead =>
      drawCanvas(ctx)
      drawSnake(looser, ctx)
      looser
    case survivor: Living =>
      drawCanvas(ctx)
      drawSnake(survivor, ctx)
      drawFood(survivor.foodTarget, ctx)
      survivor.move(nextMove)
  }

  def drawWrapper(ctx: dom.CanvasRenderingContext2D)(implicit screenSize: ScreenSize): Unit = {
    currentSnake.map(draw(_, ctx) match {
      case looser: Dead =>
        currentSnake = None
      case survivor: Living =>
        currentSnake = Some(survivor)
    })

  }

  def gameLoop(speed: Int, snake: Snake, ctx: dom.CanvasRenderingContext2D)(implicit screenSize: ScreenSize): Unit = snake match {
    case looser: Dead => println(s"Game Over : ${looser}")
    case survivor: Living =>
      //Thread.sleep(speed)
      drawCanvas(ctx)
      println(survivor)
      drawSnake(survivor, ctx)
      drawFood(survivor.foodTarget, ctx)
      gameLoop(speed, survivor.move(Right), ctx)
  }

}
