package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.functions
import org.apache.spark.sql.types._
import org.scalatest._
import sparkDS.logicSchema.testData.TestDataFile
import sparkDS.logicSchema.testUtil.TestPrinting

class SchemaComparisonParquetFileTest extends FlatSpec with Matchers with BeforeAndAfterAll {
  override def beforeAll(): Unit = {
    TestPrinting.resetTestSuiteOutputDir(this)
  }

  "========================================================================================================================" +
    "\nCase 1 - Comparing two schemas retrieved from the same data frame" should "return true" in {
    val schema1 = TestDataFile.custInteractionDataFrame.schema
    val schema2 = TestDataFile.custInteractionDataFrame.schema
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = schema1,
      schema2Name = "schema2", schema2 = schema2)
    assert(comparisonResult.isSame, comparisonResult)
  }

  "\nCase 2 - Comparing a schema and another one with one column dropped" should "return false" in {
    val schema1 = TestDataFile.custInteractionDataFrame.schema
    val schema2 = TestDataFile.custInteractionDataFrame.drop("customer_id").schema
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = schema1,
      schema2Name = "schema2", schema2 = schema2)

    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case2", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 3 - Comparing schema1 whose interactions array column has description field and schema2 which has description_xxx field " +
    "in interactions array column" should "return false" in {
    val schema1 = TestDataFile.custInteractionDataFrame.schema

    val interactions_struct = schema1(schema1.fieldIndex("interactions")).dataType.asInstanceOf[ArrayType].elementType.asInstanceOf[StructType]
    val new_interactions_struct =
      StructType(interactions_struct.fields.filter(_.name != "description"))
        .add("description_xxx", DataTypes.StringType, nullable = true)
    val schema2 = TestDataFile.custInteractionDataFrame.drop("interactions").schema.add(
      "interactions", DataTypes.createArrayType(new_interactions_struct), nullable = true)

    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = schema1,
      schema2Name = "schema2", schema2 = schema2)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case3", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

  "\nCase 4 - Comparing a schema and another one with one column has different data type" +
    "(the data type of the customer_id column was String, change it to Integer)" should "return false" in {
    val schema1 = TestDataFile.custInteractionDataFrame.schema
    val schema2 = TestDataFile.custInteractionDataFrame.drop("customer_id").withColumn("customer_id", functions.expr("9999")).schema
    val comparisonResult = SchemaComparison.compareSchema(
      schema1Name = "schema1", schema1 = schema1,
      schema2Name = "schema2", schema2 = schema2)
    if (!comparisonResult.isSame) {
      TestPrinting.msg(this, "Case4", comparisonResult.toString)
    }
    assert(!comparisonResult.isSame, comparisonResult)
  }

}