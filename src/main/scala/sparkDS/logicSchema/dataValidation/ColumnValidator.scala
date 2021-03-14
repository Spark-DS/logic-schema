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

package sparkDS.logicSchema.dataValidation

class ColumnValidator
(

  /**
   * Validator name
   */
  name: String,

  /**
   * SQL code for asserting the value of a single column, generating a SQL boolean value.
   * To validations involving multiple columns, record validator should be used.
   *
   * Use Spark SQL syntax and functions to code.
   * Use %this_col% to refer the column name of the column that the validator is asserting.
   *
   * Example 1:  %this_col% = 'USA'
   * Example 2:  isnotnull(cast(%this_col% as int) and abs(cast(%this_col% as int)) < 999
   */
  var sqlAssertionCode: String,

  /**
   * The detail message explaining the validation failure.
   * Use %this_col% to refer the column name of the column that the validator is asserting.
   *
   * Example 1:  '%this_col% must be USA'
   * Example 2:  '%this_col% must be integer and abs value < 999'
   */
  assertionMessage: String

) {

  /**
   * Get SQL validation code, all occurrences of %this_col% are replaced with the value of parameter columnName.
   *
   * @param columnName
   * @return
   */
  def sqlValidationCode(columnName: String): String = {
    val columnNameReplaced = sqlAssertionCode.replaceAll("%this_col%", columnName)
    s"if(($columnNameReplaced), null, struct('COLUMN_VALIDATION', '$columnName', '$name', '$assertionMessage'))"
  }

}
