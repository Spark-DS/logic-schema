package sparkDS.logicSchema.dataSpec

import com.typesafe.scalalogging._
import org.apache.spark.sql.types.DataType

class ColumnDType(val sqlType: String, val dataType: DataType) {
  /*
   Refer
     org.apache.spark.sql.catalyst.analysis.FunctionRegistry
     org.apache.spark.sql.catalyst.expressions.Cast
      castAlias("boolean", BooleanType),
      castAlias("tinyint", ByteType),
      castAlias("smallint", ShortType),
      castAlias("int", IntegerType),
      castAlias("bigint", LongType),
      castAlias("float", FloatType),
      castAlias("double", DoubleType),
      castAlias("decimal", DecimalType.USER_DEFAULT),
      castAlias("date", DateType),
      castAlias("timestamp", TimestampType),
      castAlias("binary", BinaryType),
      castAlias("string", StringType),
   */
    val isValidType = sqlType.replaceAll(" ", "") match {
      case "null" | "boolean" | "tinyint" | "smallint" | "int" | "bigint" | "float" | "double" | "decimal" | "date" | "timestamp" | "binary" | "string" => true
      case st if st.startsWith("decimal(") && st.endsWith(")") && st.substring(8, st.length - 1).matches("\\d*,\\d*") => true
      case _ => false
    }
    if (!isValidType) {
      val exception = new RuntimeException(s"Invalid sqlType=$sqlType")
      Logger[this.type].error(s"Invalid sqlType=$sqlType", exception)
      throw exception
    }
}
