package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.types.DataTypes
import org.scalatest._
import ReportFormatting.indent
import sparkDS.logicSchema.testUtil.TestPrinting

class DataTypeComparisonSimpleTypesTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    TestPrinting.resetTestSuiteOutputDir(this)
  }

  "========================================================================================================================" +
    "\nCase 1 - Comparing BooleanType fields" should "return true" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.BooleanType,
      schema2Name = "schema2", DataType2 = DataTypes.BooleanType)
    assert(comparisonResult.isSame)
  }

  "\nCase 2 - Comparing BooleanType and BinaryType fields" should "return false" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.BooleanType,
      schema2Name = "schema2", DataType2 = DataTypes.BinaryType)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case2", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

}