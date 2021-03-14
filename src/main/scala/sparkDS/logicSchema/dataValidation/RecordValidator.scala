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

case class RecordValidator
(

  /**
   * Validator name
   */
  val name: String,

  /**
   * SQL code for asserting the values of one or multiple columns, generating a SQL boolean value.
   * To validations a single column, column validator should be used.
   *
   * Use Spark SQL syntax and functions to code.
   *
   * Example:  country = 'USA' and isnotnull(cast(delta as int) and abs(cast(delta as int)) < 999
   */
  var sqlAssertionCode: String,

  /**
   * The detail message explaining the validation failure.
   */
  val assertionMessage: String

) {

  /**
   * Get SQL validation code.
   *
   * @param columnName
   * @return
   */
  def sqlValidationCode(): String = {
    s"if(($sqlAssertionCode), null, struct('RECORD_VALIDATION', null, '$name', '$assertionMessage'))"
  }

}