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

import org.apache.spark.sql.types._
import sparkDS.logicSchema.dataValidation.ColumnValidator

import scala.collection.mutable

/**
 * Hold info of data types, validators.
 *
 * @param name           The SQL name of this column
 * @param isKey          True if the column is a key column of the containing data file
 * @param columnDataType Column data type
 */
abstract class ColumnType
(
  val name: String,
  val isKey: Boolean,
  val columnDataType: ColumnDataType
) {
  def this(name: String, columnDataType: ColumnDataType) = {
    this(name, false, columnDataType)
  }

  val sqlType: String = columnDataType.sqlType

  /**
   * Used for construct schema
   */
  val structField: StructField = DataTypes.createStructField(name, columnDataType.dataType, true)

  def addColumnValidator(validator: ColumnValidator): mutable.Seq[ColumnValidator] = {
    _sqlCodeUpdated = false
    _columnValidators += validator
  }

  /**
   * Get the sqlValidationCode of individual column.
   *
   * @return
   */
  def sqlValidationCode(): String = {
    if (!_sqlCodeUpdated) {
      // generate SQL Code for accumulate validation message array.
      _sqlCode = _sqlValidationCode(_columnValidators)
    }
    _sqlCodeUpdated = true
    _sqlCode
  }

  /**
   * List of the sqlValidationCode of individual column validators, as array elements.
   *
   * @return
   */
  def _sqlValidationCode(columnValidators: Seq[ColumnValidator]): String = {
    columnValidators.size match {
      case 0 => "null"
      case 1 => columnValidators.head.sqlValidationCode(name)
      case _ =>
        val head = columnValidators.head
        val tail = columnValidators.tail
        s"${head.sqlValidationCode(name)}, ${_sqlValidationCode(tail)}"
    }
  }

  override def toString: String = name

  // Private members
  private val _columnValidators: mutable.Buffer[ColumnValidator] = mutable.Buffer[ColumnValidator]()
  private var _sqlCodeUpdated: Boolean = false
  private var _sqlCode: String = _
}
