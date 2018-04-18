package com.rhdzmota.games.snake.model

sealed trait Movement

sealed trait Horizontal {
  def invert: Movement = this match {
    case Right => Left
    case Left => Right
  }
}
sealed trait Vertical {
  def invert: Movement = this match {
    case Up => Down
    case Down => Up
  }
}

case object Down extends Movement with Vertical
case object Up extends Movement with Vertical
case object Right extends Movement with Horizontal
case object Left extends Movement with Horizontal
case object Undefined extends Movement
