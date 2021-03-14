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

import org.apache.spark.sql.types.StructType

object SchemaComparison {
  def compareSchema(schema1Name: String, schema1: StructType,
                    schema2Name: String, schema2: StructType
                   ): ComparisonResult = {
    val result =
      DataTypeComparison.compareDataTypes(
        fieldPath = "/",
        schema1Name, schema1,
        schema2Name, schema2)

    result.diffs = s"$schema1Name <--> $schema2Name\n${result.diffs}"
    result
  }
}