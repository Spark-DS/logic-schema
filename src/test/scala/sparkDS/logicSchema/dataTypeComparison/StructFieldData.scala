package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.types.{DataTypes, StructField}

//@formatter:off
object StructFieldData {
  val binaryField1          : StructField = DataTypes.createStructField("binaryField1",           DataTypes.BinaryType,           true)
  val booleanField1         : StructField = DataTypes.createStructField("booleanField1",          DataTypes.BooleanType,          true)
  val byteField1            : StructField = DataTypes.createStructField("byteField1",             DataTypes.ByteType,             true)
  val calendarIntervalField1: StructField = DataTypes.createStructField("calendarIntervalField1", DataTypes.CalendarIntervalType, true)
  val dateField1            : StructField = DataTypes.createStructField("dateField1",             DataTypes.DateType,             true)
  val doubleField1          : StructField = DataTypes.createStructField("doubleField1",           DataTypes.DoubleType,           true)
  val floatField1           : StructField = DataTypes.createStructField("floatField1",            DataTypes.FloatType,            true)
  val integerField1         : StructField = DataTypes.createStructField("integerField1",          DataTypes.IntegerType,          true)
  val longField1            : StructField = DataTypes.createStructField("longField1",             DataTypes.LongType,             true)
  val nullField1            : StructField = DataTypes.createStructField("nullField1",             DataTypes.NullType,             true)
  val shortField1           : StructField = DataTypes.createStructField("shortField1",            DataTypes.ShortType,            true)
  val stringField1          : StructField = DataTypes.createStructField("stringField1",           DataTypes.StringType,           true)
  val timestampField1       : StructField = DataTypes.createStructField("timestampField1",        DataTypes.TimestampType,        true)

  val timestampField1Date   : StructField = DataTypes.createStructField("timestampField1",        DataTypes.DateType,             true)
  val binaryField1Boolean   : StructField = DataTypes.createStructField("binaryField1",           DataTypes.BooleanType,          true)

  val binaryField2          : StructField = DataTypes.createStructField("binaryField2",           DataTypes.BinaryType,           true)
  val booleanField2         : StructField = DataTypes.createStructField("booleanField2",          DataTypes.BooleanType,          true)
  val byteField2            : StructField = DataTypes.createStructField("byteField2",             DataTypes.ByteType,             true)
  val calendarIntervalField2: StructField = DataTypes.createStructField("calendarIntervalField2", DataTypes.CalendarIntervalType, true)
  val dateField2            : StructField = DataTypes.createStructField("dateField2",             DataTypes.DateType,             true)
  val doubleField2          : StructField = DataTypes.createStructField("doubleField2",           DataTypes.DoubleType,           true)
  val floatField2           : StructField = DataTypes.createStructField("floatField2",            DataTypes.FloatType,            true)
  val integerField2         : StructField = DataTypes.createStructField("integerField2",          DataTypes.IntegerType,          true)
  val longField2            : StructField = DataTypes.createStructField("longField2",             DataTypes.LongType,             true)
  val nullField2            : StructField = DataTypes.createStructField("nullField2",             DataTypes.NullType,             true)
  val shortField2           : StructField = DataTypes.createStructField("shortField2",            DataTypes.ShortType,            true)
  val stringField2          : StructField = DataTypes.createStructField("stringField2",           DataTypes.StringType,           true)
  val timestampField2       : StructField = DataTypes.createStructField("timestampField2",        DataTypes.TimestampType,        true)
}
//@formatter:on