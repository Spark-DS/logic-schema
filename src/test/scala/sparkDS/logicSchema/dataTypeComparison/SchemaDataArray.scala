package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.types.{DataTypes, StructType}
import StructFieldData._

object SchemaDataArray {
  val schema1: StructType = DataTypes.createStructType(List(
    binaryField1, booleanField1, byteField1, calendarIntervalField1, dateField1, doubleField1, floatField1,
    DataTypes.createStructField(
      "arrayTypeField",
      DataTypes.createArrayType(DataTypes.createStructType(List(
        integerField1, longField1, nullField1, shortField1, stringField1, timestampField1).toArray)),
      true)
  ).toArray)

  val schema1Same: StructType = DataTypes.createStructType(List(
    binaryField1, booleanField1, byteField1, calendarIntervalField1, dateField1, doubleField1, floatField1,
    DataTypes.createStructField(
      "arrayTypeField",
      DataTypes.createArrayType(DataTypes.createStructType(List(
        integerField1, longField1, nullField1, shortField1, stringField1, timestampField1).toArray)),
      true)
  ).toArray)

  val schema1FldDiffType: StructType = DataTypes.createStructType(List(
    binaryField1, booleanField1, byteField1, calendarIntervalField1, dateField1, doubleField1, floatField1,
    DataTypes.createStructField(
      "arrayTypeField",
      DataTypes.createArrayType(DataTypes.createStructType(List(
        integerField1, longField1, nullField1, shortField1, stringField1, timestampField1Date).toArray)),
      true)
  ).toArray)

  val schema2FldDiffTypeDiffName: StructType = DataTypes.createStructType(List(
    // Comment these 3 fields so they will be displayed in diff:
    // binaryField1, booleanField1, byteField1,
    calendarIntervalField1, dateField1, doubleField1, floatField1,
    DataTypes.createStructField(
      "arrayTypeField",
      DataTypes.createArrayType(DataTypes.createStructType(List(
        integerField1, integerField2, longField2, nullField2, shortField2, stringField2, timestampField2).toArray)),
      true)
  ).toArray)
}