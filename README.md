## Cisco Tech Test

### Task:


Today, you will be creating a service to process data coming from Meraki devices. Let’s
assume you’ve got a stream of data with the following fields (node_id: Long, value:
Integer, timestamp: Long). You’ll need to implement a service that determines simple
stats for every node_id in this data stream. For each minute (based on the timestamp field)
your service needs to calculate the following stats: minimum value, maximum value and
average value and write the calculated stats into a database of your choice.

## Prerequisites

To build this project, you must have:

* Maven
    * Maven can be installed [here](https://maven.apache.org/download.cgi)
    * Alternatively, on MacOS, if you have homebrew you can install it using `brew install maven`
* Java JDK
    * You can get OpenJDK from [here](https://openjdk.java.net/install/)
    * Alternatively, on MacOS, if you have homebrew you can install it using `brew cask install java`
* Docker desktop + docker-compose
    * Both can be found [here](https://docs.docker.com/compose/install/)
## Building

You can build this project with the following command from the root directory:

`mvn clean package`

You can build the containerized kafka and database infrastructure by running this from the root directory:

`docker-compose up`
    
## Running

After you have built the program, you can run it with the following command from the root directory

`mvn exec:java -D exec.mainClass=app.App`

## Writing and reading messages from the stream

To publish messages to the stream, you can use `kafka-console-producer`, from the root directory:

`docker exec -i schema-registry /usr/bin/kafka-avro-console-producer --topic node-value-input --broker-list broker:9092 --property value.schema="$(< src/main/avro/nodevalue.avsc)"`

After you run the above, you should be able to then paste messages in the following format:

`{"node_id": 5, "value": 50, "timestamp": 600000}`

To view the result of these messages, you can view the changes in the database, using pgAdmin, which can be accessed [here](http://localhost:5050/)

The default username for pgAdmin is `admin@admin.com` and the password is `admin`

The DB can be accessed from pgAdmin by adding a new database, and using the following credentials:

* **Host name/address** `postgres`
* **Port** `5432`
* **Username** `postgres`
* **Password** `changeme`

You can then write a query to access the `average` table:

`SELECT * FROM average`

## Assumptions and shortcomings

It's important to note that I don't have any experience building data processing applications. While I have worked on a projects that use messaging queues to communicate, processing that data as opposed to simply consuming/publishing is new to me, as is using Apache kafka.

I've made a few assumptions while writing this, namely:
* As this project is related to scale, and involves both real time processing, and database access, I've tried to limit how often the database is written to. This means that I've only decided to write to the database at the end of each window (1 minute), instead of updating it each time a new message arrives.
* The above also means that some messages that arrive significantly later than the window that they belong to are disregarded. This is based on their event-time.
* Most of the kafka configuration is hard-coded - if I had more time, I would have extracted the settings to configuration files.
* There is no way to publish to kafka topics via this program - it is simply a processor and consumer
* Given more time, I would have written a REST api to add items to the stream, or view the current state of the DB
* I would have written tests, but was very low on time as I had to study kafka beforehand
