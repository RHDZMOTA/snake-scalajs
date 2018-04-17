package com.rhdzmota.games.snake.model

sealed trait Movement

case object Down extends Movement
case object Up extends Movement
case object Right extends Movement
case object Left extends Movement
case object Undefined extends Movement
