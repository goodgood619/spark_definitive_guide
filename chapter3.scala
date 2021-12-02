case class Flight(dest_country_name: String, origin_country_name: String, count: BigInt)
val flightsDF = spark.read.parquet("./Spark-The-Definitive-Guide/data/flight-data/parquet/2010-summary.parquet/")
val flights = flightsDF.as[Flight]
flights.filter(flight_row => flight_row.origin_country_name != "Canada").map(flight_row => flight_row).take(5)
flights.take(5).filter(flight_row => flight_row.origin_country_name != "Canada").map(fr => Flight(fr.dest_country_name, fr.origin_country_name, fr.count + 5))


val staticDataFrame = spark.read.format("csv").option("header","true").option("inferSchema","true").load("./Spark-The-Definitive-Guide/data/retail-data/by-day/*.csv")
staticDataFrame.createOrReplaceTempView("retail_data")
val staticSchema = staticDataFrame.schema
staticDataFrame.selectExpr("CustomerId", "(UnitPrice * Quantity) as total_cost", "InvoiceDate").groupBy(col("CustomerId"), window(col("InvoiceDate"), "1 day")).sum("total_cost").show(5)

spark.conf.set("spark.sql.shuffle.partitions","5")



