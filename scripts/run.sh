#!/bin/bash
spark-submit \
  --class djr.spark.fah.Driver \
  --archives FAH.tar#FAH \
  --files config/app.properties,config/fah.properties \
  --deploy-mode cluster \
  --conf spark.dynamicAllocation.enabled=true \
  spark-fah-harness-1.0.0-all.jar
