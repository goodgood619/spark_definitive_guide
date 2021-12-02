spark.range(2).toDF().collect()

import org.apache.spark.sql.types._
val b = ByteType
val s = ShortType
val m = MapType(StringType, StringType)



