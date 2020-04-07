#!/bin/bash
spark-submit \
  --class djr.spark.fah.Driver \
  --archives FAH.zip#FAH \
  --files config/app.properties,config/fah.properties \
  --master yarn \
  --deploy-mode cluster \
  --conf spark.dynamicAllocation.enabled=true \
  --executor-cores 16 \
  --conf spark.task.cpus=16 \
  --queue idle \
  spark-fah-harness-1.0.0-all.jar
