package sparkDS.logicSchema.dataTypeComparison

import org.scalatest._
import ReportFormatting.indent
import sparkDS.logicSchema.testUtil.TestPrinting

class DataTypeComparisonStructTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    TestPrinting.resetTestSuiteOutputDir(this)
  }

  "========================================================================================================================" +
    "\nCase 1 - Comparing StructType and StructType fields with same fields with same name and type" should "return true" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = SchemaData.schema1,
      schema2Name = "schema2", DataType2 = SchemaData.schema1Same)
    assert(comparisonResult.isSame, comparisonResult)
  }

  "\nCase 2 - Comparing StructType and StructType fields with same fields with same name and different type" should "return false" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = SchemaData.schema1,
      schema2Name = "schema2", DataType2 = SchemaData.schema1FldDiffType)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case2", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 3 - Comparing StructType and StructType fields with same fields with different name and same type" should "return false" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = SchemaData.schema1,
      schema2Name = "schema2", DataType2 = SchemaData.schema2FldDiffTypeDiffName)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case3", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

}