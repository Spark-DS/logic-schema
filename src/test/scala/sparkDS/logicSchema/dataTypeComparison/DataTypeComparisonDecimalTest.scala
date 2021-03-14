package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.types.DataTypes
import org.scalatest._
import sparkDS.logicSchema.testUtil.TestPrinting

class DataTypeComparisonDecimalTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    TestPrinting.resetTestSuiteOutputDir(this)
  }

  "========================================================================================================================" +
    "\nCase 1 - Comparing DecimalType and DecimalType fields with same precision and scale" should "return true" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.createDecimalType(9, 2),
      schema2Name = "schema2", DataType2 = DataTypes.createDecimalType(9, 2))
    assert(comparisonResult.isSame, comparisonResult)

    val comparisonResult2 = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.createDecimalType(7, 1),
      schema2Name = "schema2", DataType2 = DataTypes.createDecimalType(7, 1))
    assert(comparisonResult2.isSame, comparisonResult2)
  }

  "\nCase 2 - Comparing DecimalType and DecimalType fields with different precision" should "return false" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.createDecimalType(9, 2),
      schema2Name = "schema2", DataType2 = DataTypes.createDecimalType(7, 2))
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case2", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 3 - Comparing DecimalType and DecimalType fields with different scale" should "return false" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.createDecimalType(9, 2),
      schema2Name = "schema2", DataType2 = DataTypes.createDecimalType(9, 3))
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case3", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 4 - Comparing DecimalType and DecimalType fields with different precision and scale" should "return false" in {
    val comparisonResult = DataTypeComparison.compareDataTypes(fieldPath = "/",
      schema1Name = "schema1", dataType1 = DataTypes.createDecimalType(9, 2),
      schema2Name = "schema2", DataType2 = DataTypes.createDecimalType(7, 1))
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case4", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

}