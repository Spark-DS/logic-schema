/*
 * Copyright 2021 by DJ Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sparkDS.logicSchema.dataTypeComparison

import org.apache.spark.sql.types._
import ReportFormatting.{indent, minPathWidth}

private object DataTypeComparison {
  /**
   * Compare the data types of two fields identified by the same fieldPath in both schema.
   * Recursively called if the field is array or struct.
   */
  def compareDataTypes(fieldPath: String,
                       schema1Name: String, dataType1: DataType,
                       schema2Name: String, DataType2: DataType): ComparisonResult = {
    var isSame = true
    var diffs = ""

    if (dataType1 == DataType2 && dataType1.isInstanceOf[CalendarIntervalType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[TimestampType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[BooleanType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[DecimalType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[IntegerType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[BinaryType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[DoubleType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[StringType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[FloatType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[ShortType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[ByteType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[DateType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[LongType] ||
      dataType1 == DataType2 && dataType1.isInstanceOf[NullType]
    ) {
      // Both data types are the same simple type
      ComparisonResult(isSame, diffs)

    } else if (dataType1.isInstanceOf[ArrayType] && DataType2.isInstanceOf[ArrayType]) {
      // Both data types are ArrayType, let's compare the element types
      compareDataTypes(fieldPath = s"$fieldPath[Array]", schema1Name, dataType1.asInstanceOf[ArrayType].elementType, schema2Name, DataType2.asInstanceOf[ArrayType].elementType)

    } else if (dataType1.isInstanceOf[StructType] && DataType2.isInstanceOf[StructType]) {
      // Both data types are StructType, let's compare the types of their fields recursively
      val structType1 = dataType1.asInstanceOf[StructType]
      val structType2 = DataType2.asInstanceOf[StructType]
      val fieldNames1 = structType1.fieldNames
      val fieldNames2 = structType2.fieldNames

      val fieldNames1diff2 = fieldNames1.diff(fieldNames2).toList
      val fieldNames2diff1 = fieldNames2.diff(fieldNames1).toList

      if (fieldNames1diff2.nonEmpty) {
        isSame = false
        diffs = s"$diffs\n$indent $fieldPath: Fields in [$schema1Name] but not in [$schema2Name]:\n$indent$indent ${fieldNames1diff2.toString}"
      }

      if (fieldNames2diff1.nonEmpty) {
        isSame = false
        diffs = s"$diffs\n$indent $fieldPath: Fields not in [$schema1Name] but in [$schema2Name]:\n$indent$indent ${fieldNames2diff1.toString}"
      }

      // Now compare the fields with the same name
      for (fieldName <- fieldNames1.intersect(fieldNames2).sorted) {
        val element1 = structType1.fields(structType1.fieldIndex(fieldName))
        val element2 = structType2.fields(structType2.fieldIndex(fieldName))
        val pathSep = {
          if (fieldPath.endsWith("/")) "" else "/"
        }
        val fieldCompareResult = compareDataTypes(fieldPath = s"$fieldPath$pathSep$fieldName", schema1Name, element1.dataType, schema2Name, element2.dataType)
        if (!fieldCompareResult.isSame) {
          isSame = false
          diffs = s"$diffs\n${fieldCompareResult.diffs}"
        }
      }
      ComparisonResult(isSame, diffs)

    } else {
      // The types of both fields are different
      isSame = false
      val type1Name = dataType1.typeName
      val type2Name = DataType2.typeName
      val padding = if (s"$indent $fieldPath:".length < minPathWidth.length) {
        minPathWidth.substring(s"$indent $fieldPath:".length)
      } else {
        ""
      }
      diffs = s"$diffs\n$indent $fieldPath:$padding type $type1Name <--> type $type2Name"
      ComparisonResult(isSame, diffs)
    }
  }
}