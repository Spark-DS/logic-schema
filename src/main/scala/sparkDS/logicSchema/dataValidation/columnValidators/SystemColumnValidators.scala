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

package sparkDS.logicSchema.dataValidation.columnValidators

import sparkDS.logicSchema.dataValidation.ColumnValidator

object SystemColumnValidators {
  //@formatter:off
  val boolean_validator   = new ColumnValidator("boolean_validator",       "isnotnull(cast(%this_col% as boolean)",   "Value is not boolean")
  val byte_validator      = new ColumnValidator("byte_validator",          "isnotnull(cast(%this_col% as byte)",      "Value is not byte")
  val date_validator      = new ColumnValidator("date_validator",          "isnotnull(cast(%this_col% as date)",      "Value is not date")
  val double_validator    = new ColumnValidator("double_validator",        "isnotnull(cast(%this_col% as double)",    "Value is not double")
  val int_validator       = new ColumnValidator("int_validator",           "isnotnull(cast(%this_col% as int)",       "Value is not int")
  val long_validator      = new ColumnValidator("long_validator",          "isnotnull(cast(%this_col% as long)",      "Value is not long")
  val short_validator     = new ColumnValidator("short_validator",         "isnotnull(cast(%this_col% as short)",     "Value is not short")
  val timestamp_validator = new ColumnValidator("timestamp_validator",     "isnotnull(cast(%this_col% as timestamp)", "Value is not timestamp")
  //@formatter:on
}
