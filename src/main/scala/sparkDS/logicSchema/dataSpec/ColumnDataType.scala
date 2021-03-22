package sparkDS.logicSchema.dataSpec

import com.typesafe.scalalogging._
import org.apache.spark.sql.types.DataType

class ColumnDataType(val sqlType: String, val dataType: DataType)

object ColumnDataType extends LazyLogging {

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
  def apply(sqlType: String, dataType: DataType): ColumnDataType = {
    val isValidType = sqlType.replaceAll(" ", "") match {
      case "null" | "boolean" | "tinyint" | "smallint" | "int" | "bigint" | "float" | "double" | "decimal" | "date" | "timestamp" | "binary" | "string" => true
      // Wait for Scala 2.13
      case s"decimal($precision,$scale)" => precision.toInt >= scale.toInt
      case st if st.startsWith("decimal(") && st.endsWith(")") => {
        val parameters = st.substring(8, st.length - 1)
        parameters.matches("\\d*,\\d*") && {
          val tokenized = parameters.split(",")
          tokenized(0).toInt >= tokenized(1).toInt
        }
      }
      case _ => false
    }
    if (!isValidType) {
      val msg = s"Invalid sqlType=$sqlType"
      val exception = new AnalysisException(msg)
      Logger[this.type].error(msg, exception)
      throw exception
    }
    new ColumnDataType(sqlType, dataType)
  }
}
