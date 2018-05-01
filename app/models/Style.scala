package models

import play.api.libs.json._

case class Style(id:Long, name:String, imageUrl:String)


object Style {
	implicit val styleFormat = Json.format[Style]
}