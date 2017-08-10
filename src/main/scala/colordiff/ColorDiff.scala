package colordiff

import scala.collection.JavaConverters._
import scala.Console.{RED, GREEN, RESET}
import difflib.{Delta, DiffUtils}

object ColorDiff {

  def apply(original: List[String], revised: List[String]): String = {
    DiffUtils
      .diff(original.asJava, revised.asJava)
      .getDeltas
      .asScala
      .map { delta =>
        def original =
          delta.getOriginal.getLines.asScala
            .map(line => s"$RED< $line$RESET")
            .mkString("\n")
        def revised =
          delta.getRevised.getLines.asScala
            .map(line => s"$GREEN> $line$RESET")
            .mkString("\n")

        delta.getType match {
          case Delta.TYPE.DELETE =>
            s"""${delta.getOriginal.getPosition + 1}d${delta.getRevised.getPosition}
               |$original""".stripMargin
          case Delta.TYPE.CHANGE =>
            s"""${delta.getOriginal.getPosition + 1}c${delta.getRevised.getPosition + 1}
               |$original
               |___
               |$revised""".stripMargin
          case Delta.TYPE.INSERT =>
            s"""${delta.getOriginal.getPosition}a
               |$revised""".stripMargin
        }
      }
      .mkString("\n")
  }
}
