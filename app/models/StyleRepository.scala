package models

import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{Future , ExecutionContext}


@Singleton
class StyleRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec:ExecutionContext){
	private val dbConfig = dbConfigProvider.get[JdbcProfile]

	import dbConfig._
	import profile.api._

	private class StyleTable(tag:Tag) extends Table[Style](tag, "STYLE"){
		def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
		def name = column[String]("NAME")
		def imageUrl = column[String]("IMAGE_URL")

		def * = (id, name, imageUrl) <> ((Style.apply _).tupled, Style.unapply)
	}

	private val style = TableQuery[StyleTable]

	def list():Future[Seq[Style]] = db.run {
		style.result
	}
}

