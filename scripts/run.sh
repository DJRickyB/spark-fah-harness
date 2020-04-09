#!/bin/bash
spark-submit \
  --class djr.spark.fah.Driver \
  --archives FAH.tar#FAH \
  --files config/app.properties,config/fah.properties \
  --master yarn \
  --deploy-mode cluster \
  --conf spark.dynamicAllocation.enabled=true \
  --executor-cores 6 \
  --conf spark.task.cpus=6 \
  --queue idle \
  spark-fah-harness-1.0.0-all.jar
