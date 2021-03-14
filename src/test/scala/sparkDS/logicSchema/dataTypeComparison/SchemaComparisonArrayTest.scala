package sparkDS.logicSchema.dataTypeComparison

import org.scalatest._
import sparkDS.logicSchema.testUtil.TestPrinting

class SchemaComparisonArrayTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    TestPrinting.resetTestSuiteOutputDir(this)
  }

  "========================================================================================================================" +
    "\nCase 1 - Comparing StructType with same fields, name and type" should "return true" in {
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = SchemaDataArray.schema1,
      schema2Name = "schema1Same", schema2 = SchemaDataArray.schema1Same)
    assert(comparisonResult.isSame, comparisonResult)
  }

  "\nCase 2 - Comparing StructType and StructType fields with same fields with same name and different type" should "return false" in {
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = SchemaDataArray.schema1,
      schema2Name = "schema1FldDiffType", schema2 = SchemaDataArray.schema1FldDiffType)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case2",
        s"\nThe test Case 2 passed, printing detail for debugging:$comparisonResult")
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 3 - Comparing StructType and StructType fields with same fields with different name and same type" should "return false" in {
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = SchemaDataArray.schema1,
      schema2Name = "schema2FldDiffTypeDiffName", schema2 = SchemaDataArray.schema2FldDiffTypeDiffName)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case3", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

}