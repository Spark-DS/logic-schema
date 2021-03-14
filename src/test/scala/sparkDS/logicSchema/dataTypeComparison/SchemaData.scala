package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.types.{DataTypes, StructType}
import StructFieldData._

object SchemaData {
  val schema1: StructType = DataTypes.createStructType(List(
    binaryField1, booleanField1, byteField1, calendarIntervalField1, dateField1, doubleField1, floatField1,
    integerField1, longField1, nullField1, shortField1, stringField1, timestampField1).toArray)

  val schema1Same: StructType = DataTypes.createStructType(List(
    binaryField1, booleanField1, byteField1, calendarIntervalField1, dateField1, doubleField1, floatField1,
    integerField1, longField1, nullField1, shortField1, stringField1, timestampField1).toArray)

  val schema1FldDiffType: StructType = DataTypes.createStructType(List(
    binaryField1Boolean, booleanField1, byteField1, calendarIntervalField1, dateField1, doubleField1, floatField1,
    integerField1, longField1, nullField1, shortField1, stringField1, timestampField1).toArray)

  val schema2FldDiffTypeDiffName: StructType = DataTypes.createStructType(List(
    binaryField1Boolean, booleanField2, byteField2, calendarIntervalField2, dateField2, doubleField2, floatField2,
    integerField2, longField2, nullField2, shortField2, stringField2, timestampField2).toArray)
}