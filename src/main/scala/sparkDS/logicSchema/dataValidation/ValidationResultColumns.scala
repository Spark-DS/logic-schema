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

import sparkDS.logicSchema.dataSpec.ColumnType
import sparkDS.logicSchema.dataSpec.sysColumnTypes.StringColumnType

//@formatter:off
class ValidatorType     extends StringColumnType("validator_type")
class ValidationTarget  extends StringColumnType("validation_target")
class ValidatorName     extends StringColumnType("validator_name")
class ValidationMessage extends StringColumnType("validation_message")

class ValidationResultColumn extends ColumnType("logic_validation_result", ValidationResultColumnDataTypes.ValidationResultStruct)

object ValidationResultColumns {
  val validator_type          = new ValidatorType()
  val validation_target       = new ValidationTarget()
  val validator_name          = new ValidatorName()
  val validation_message      = new ValidationMessage()

  val logic_validation_result = new ValidationResultColumn()
}
//@formatter:on
