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

package sparkDS.logicSchema.testData

import org.apache.spark.sql.DataFrame
import sparkDS.logicSchema.testUtil.SparkTestUtil

object TestDataFile {
  val custInteractionFilePath = "./src/test/resources/dataFiles/custInteraction.parquet"
  def custInteractionDataFrame: DataFrame = SparkTestUtil.spark.read.parquet(custInteractionFilePath)
}