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

package sparkDS.logicSchema.dataSpec

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.expr
import org.apache.spark.sql.types._
import sparkDS.logicSchema.dataValidation.RecordValidator

import scala.collection.mutable

abstract class DataFile
(
  val name: String,
  val columns: Seq[ColumnType]
) {
  val schema: StructType = StructType(columns.map(col => col.structField))

  /**
   * Add column logic_validation_messages to the input data frame.
   * Client code then can get the validation result by selecting row with non-empty logic_validation_messages.
   *
   * @param dataframe The data frame to be validated
   */
  def validate(dataframe: DataFrame): DataFrame = {
    dataframe.withColumn("logic_validation_result", expr(s"filter(${sqlValidationCode()}, msg -> isnotnull(msg))"))
  }

  def addRecordValidator(validator: RecordValidator): mutable.Seq[RecordValidator] = {
    _sqlCodeUpdated = false
    _recordValidators += validator
  }

  override def toString: String = name

  /**
   * Generate SQL code for constructing the array of validation result struct by:
   * 1. Call column validators
   * 2. Call record validators
   *
   * @return Array of validation result struct
   */
  def sqlValidationCode(): String = {
    if (!_sqlCodeUpdated) {
      // generate SQL Code for accumulate validation message array.
      val colValidateCode = _sqlColumnValidationCode(columns)
      val recValidateCode = _sqlRecordValidationCode(_recordValidators)
      _cachedSqlCode = s"array($colValidateCode, $recValidateCode)"
      println("\n\n")
      println(s"[Debug][DataFile.sqlValidationCode] name=$name, _cachedSqlCode=${_cachedSqlCode}")
      println("\n\n")
    }
    _sqlCodeUpdated = true
    _cachedSqlCode
  }

  /**
   * List of the sqlValidationCode of columns, as array elements.
   *
   * @return
   */
  def _sqlColumnValidationCode(columns: Seq[ColumnType]): String = {
    columns.size match {
      case 0 => throw new RuntimeException("columns can't be empty")
      case 1 => columns.head.sqlValidationCode()
      case _ =>
        val head = columns.head
        val tail = columns.tail
        s"${head.sqlValidationCode()}, ${_sqlColumnValidationCode(tail)}"
    }
  }

  /**
   * List of the sqlValidationCode of individual column validator, as array elements.
   *
   * @return
   */
  def _sqlRecordValidationCode(recordValidator: Seq[RecordValidator]): String = {
    recordValidator.size match {
      case 0 => "null"
      case 1 => recordValidator.head.sqlValidationCode()
      case _ =>
        val head = recordValidator.head
        val tail = recordValidator.tail
        s"${head.sqlValidationCode()}, ${_sqlRecordValidationCode(tail)}"
    }
  }

  private val _recordValidators: mutable.Buffer[RecordValidator] = mutable.Buffer[RecordValidator]()
  private var _sqlCodeUpdated = false
  private var _cachedSqlCode: String = _
}
