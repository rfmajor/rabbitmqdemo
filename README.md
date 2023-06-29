# Messaging Technologies - RabbitMQ Demo Application

## Running the project
1. Set up the RabbitMQ cluster
- Start the rabbit containers:

   ```cd startRabbit && ./run_rabbit_cluster.sh```

- Wait for the rabbit nodes to start and set up the cluster
- Run the `replicate_queues.sh` script to make sure the quorum queue from `rabbit1` node is replicated to the other nodes

2. Launch Listener's Spring Boot app
3. Launch Sender's Spring Boot app
4. Observe the logs on Listener's console
