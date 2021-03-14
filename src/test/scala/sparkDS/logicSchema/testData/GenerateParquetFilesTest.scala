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

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SaveMode}
import org.scalatest._
import sparkDS.logicSchema.testUtil.SparkTestUtil

class GenerateParquetFilesTest extends FlatSpec with Matchers {

  "========================================================================================================================" +
    "\nCase 1 - Generate custInteraction parquet file" should "return true" in {
    val custInteractionFileSchema: StructType = DataTypes.createStructType(List(
      StructField("product_id", LongType, nullable = false),
      StructField("customer_id", LongType, nullable = false),
      StructField("interactions", ArrayType(StructType(List(
        StructField("rep", StringType, nullable = false),
        StructField("feedback", StringType, nullable = false),
        StructField("description", StringType, nullable = true)
      ))), nullable = true)
    ).toArray)

    val custInteractionFileData: RDD[Row] = SparkTestUtil.spark.sparkContext.parallelize(
      Seq(
        Row(11L, 12L, null),
        Row(22L, 22L, Seq(
          Row("Stacy", "Neutral", null),
          Row("Dave", "Positive", "Customer's issue resolved."),
          Row("Rick", "Negative", "Customer's not happy with the product.")
        ))
      ))

    val custInteractionDF = SparkTestUtil.spark.createDataFrame(custInteractionFileData, custInteractionFileSchema)
    println("\n\n GenerateParquetFilesTest:")
    println("\n\n     Test data files were not written to avoid interference with other test cases, uncomment to regenerate.\n\n\n")
/*
    custInteractionDF.coalesce(1)
    custInteractionDF.show(999, truncate = false)
    custInteractionDF.write.mode(SaveMode.Overwrite).parquet(TestDataFile.custInteractionFilePath)
*/
  }

}