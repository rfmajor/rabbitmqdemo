# Messaging Technologies - RabbitMQ Demo Application

# Overview
This is a project representing a simple message flow between a single publisher and a single subscriber (both being Spring Boot apps) using a dockerized RabbitMQ cluster of 3 nodes. The publisher generates messages containing information about different flights with randomly generated mock data and sends them to a direct exchange called `flight.direct`. This exchange has a binding to a quorum queue called `flight.queue` and redirects all the messages to it. The listener tracks incoming messages and logs them to the console output. All queue, exchange and binding definitions are loaded on startup into `rabbit1` node from definitions.json file. A peer discovery mechanism is used to set up the cluster. For this purpose, a `Config File Peer Discovery` method is used (see [Config File Peer Discoveryv](https://www.rabbitmq.com/cluster-formation.html#peer-discovery-classic-config) for reference), with all configuration located inside of `rabbitmq.conf`, which is also loaded on startup of each node.  

In scope of this project I used quorum queues instead of mirrored queues because of the latter ones being deprecated and highly discouraged by the RabbitMQ documentation.

## Running the project
1. Set up the RabbitMQ cluster
- Start the rabbit containers:

   ```cd startRabbit && ./run_rabbit_cluster.sh```

- Wait for the rabbit nodes to start and set up the cluster
- Run the `replicate_queues.sh` script to make sure the quorum queue from `rabbit1` node is replicated to the other nodes

2. Launch Listener's Spring Boot app
3. Launch Sender's Spring Boot app
4. Observe the logs on Listener's console

The cluster assures high availability upon failure of one of the nodes. Yo
