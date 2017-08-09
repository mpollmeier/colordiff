package colordiff

import scala.collection.JavaConverters._
import io.AnsiColor._
import difflib.{Delta, DiffUtils}

object ColorDiff {

  def apply(original: List[String], revised: List[String]): String = {
    DiffUtils
      .diff(original.asJava, revised.asJava)
      .getDeltas
      .asScala
      .map { delta =>
        def original =
          delta.getOriginal.getLines.asScala.map("< " + _).mkString("\n")
        def revised =
          delta.getRevised.getLines.asScala.map("> " + _).mkString("\n")

        delta.getType match {
          case Delta.TYPE.DELETE =>
            s"""${delta.getOriginal.getPosition + 1}d${delta.getRevised.getPosition}
               |$RED$original$RESET""".stripMargin
          case Delta.TYPE.CHANGE =>
            s"""${delta.getOriginal.getPosition + 1}c${delta.getRevised.getPosition + 1}
               |$RED$original$RESET
               |___
               |$GREEN$revised$RESET""".stripMargin
          case Delta.TYPE.INSERT =>
            s"""${delta.getOriginal.getPosition}a
               |$GREEN$revised$RESET""".stripMargin
        }
      }
      .mkString("\n")
  }
}
