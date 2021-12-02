val flightData2015 = spark.read.option("inferSchema","true").option("header","true").csv("./Spark-The-Definitive-Guide/data/flight-data/csv/2015-summary.csv")
flightData2015.take(3)

flightData2015.createOrReplaceTempView("flight_data_2015")
val sqlWay = spark.sql("""
     | select dest_country_name, count(1)
     | from flight_data_2015
     | group by dest_country_name
     |  """)

val dataFrameWay = flightData2015.groupBy("DEST_COUNTRY_NAME").count()
sqlWay.explain
dataFrameWay.explain



val maxSql = spark.sql("""
     | select dest_country_name, sum(count) as destination_total
     | from flight_data_2015
     | group by dest_country_name
     | order by sum(count) desc
     | limit 5
     | """)

maxSql.show()

flightData2015.groupBy("dest_country_name").sum("count").withColumnRenamed("sum(count)", "destination_total").sort(desc("destination_total")).limit(5).show
