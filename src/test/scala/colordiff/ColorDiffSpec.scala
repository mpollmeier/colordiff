package colordiff

import io.AnsiColor._
import org.scalatest._
import scala.io.Source

class ColorDiffSpec extends WordSpec with Matchers {
  "add" in {
    ColorDiff(List(), List("added")) shouldBe
      s"""0a
         |$GREEN> added$RESET""".stripMargin
  }

  "delete" in {
    ColorDiff(List("deleteme"), List()) shouldBe
      s"""1d0
         |$RED< deleteme$RESET""".stripMargin
  }

  "change" in {
    ColorDiff(List("changeme"), List("CHANGED")) shouldBe
      s"""1c1
         |$RED< changeme$RESET
         |___
         |$GREEN> CHANGEME$RESET""".stripMargin
  }

  "complex diff" in {
    def read(f: String) = Source.fromFile(f).getLines.toList
    val original = read("src/test/resources/original.file")
    val revised = read("src/test/resources/revised.file")

    ColorDiff(original, revised) shouldBe
      s"""1d0
       |$RED< this line will be deleted$RESET
       |3c2
       |$RED< this line will be changed$RESET
       |___
       |$GREEN> this line will be CHANGED$RESET
       |5a
       |$GREEN> here is another line
       |> and one more$RESET""".stripMargin

    //output of unix colordiff
    // 1d0
    // < this line will be deleted
    // 3c2
    // < this line will be changed
    // ---
    // > this line will be CHANGED
    // 5a5,6
    // > here is another line
    // > and one more
  }
}
