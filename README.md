# Spark F@H Harness

This project is a self-contained executable for Folding At Home on Apache Spark.

It takes advantage of the portability of Spark across various scheduler platforms
and invokes an instance of the F@H Client per executor.

## Development
```bash
./gradlew clean createDists
```

## Usage
### Prerequisites
* RHEL 7
* Apache Spark (installed)

### To Run
1. Unzip the release dist file
2. (Optional) configure the files in config/ directory
3. Execute `./run.sh`

## Configuration

### config/app.properties
Defines parameters for overall job (mostly related to Spark).  See in-line comments 
for configuration detail.

### config/fah.properties
Literal CLI parameters to be used by the executors on the F@H client.  For convenience,
the `usage` has been printed to the file as comments.