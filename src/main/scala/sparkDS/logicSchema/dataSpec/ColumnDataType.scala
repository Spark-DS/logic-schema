package sparkDS.logicSchema.dataSpec

import org.apache.spark.sql.types.DataType

class ColumnDataType(val sqlType: String, val dataType: DataType) {
  // Refer
  //   org.apache.spark.sql.catalyst.analysis.FunctionRegistry
  //   org.apache.spark.sql.catalyst.expressions.Cast
  assert(sqlType.replaceAll(" ", "") match {
    case typeString if typeString.startsWith("decimal(") && typeString.endsWith(")") && typeString.substring(8, typeString.length-1).matches("\\d*,\\d*") => true
    case "binary" | "boolean" | "interval" | "date" | "decimal" | "double" | "float" | "int" | "long" | "null" | "short" | "string" | "timestamp" => true
    case _ => false
  })
}
