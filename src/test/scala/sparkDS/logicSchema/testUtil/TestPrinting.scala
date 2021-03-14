package sparkDS.logicSchema.testUtil

import org.scalatest.TestSuite

import java.io._
import java.nio.file.{Files, Paths}
import scala.reflect.io.Directory

/**
 * Utility for printing information related to test result, no matter if the test case fails or not,
 * organized by test suite then test case, under test/output.
 * The purpose is to help developer verifying things even when the case passed.
 */
object TestPrinting {
  val testOutputDir = "testOutput/testPrinting"
  val printNegativeCaseDetail = true

  def resetTestSuiteOutputDir(testSuite: TestSuite): Unit = {
    if (!printNegativeCaseDetail)
      return
    val testSuiteOutputDir = getOutputDirForTestSuite(testSuite)
    println(s"[TestPrinting.resetTestSuiteOutputDir] Deleting $testSuiteOutputDir")
    val directory = new Directory(new File(testSuiteOutputDir))
    directory.deleteRecursively()
    Files.createDirectories(Paths.get(testSuiteOutputDir))
  }

  def msg(testSuite: TestSuite, caseName: String, msg: String): Unit = {
    if (!printNegativeCaseDetail)
      return
    Files.createDirectories(Paths.get(testOutputDir))
    val testSuiteOutputDir = getOutputDirForTestSuite(testSuite)
    val filePath = s"$testSuiteOutputDir/$caseName"
    val fw = new FileWriter(filePath, true)
    fw.append(s"Test suite source: ${testSuite.getClass.getSimpleName}.scala\n\nThe test $caseName passed, printing detail for debugging:\n$msg\n")
    fw.close()
  }

  private def getOutputDirForTestSuite(testSuite: TestSuite): String = {
    s"$testOutputDir/${testSuite.getClass.getName}"
  }
}
