# Messaging Technologies - RabbitMQ Demo Application

## Overview
This is a project representing a simple message flow between a single publisher and a single subscriber (both being Spring Boot apps) using a dockerized RabbitMQ cluster of 3 nodes. The publisher generates messages containing information about different flights with randomly generated mock data and sends them to a direct exchange called `flight.direct`. This exchange has a binding to a quorum queue called `flight.queue` and redirects all the messages to it. The listener tracks incoming messages and logs them to the console output. All queue, exchange and binding definitions are loaded on startup into `rabbit1` node from definitions.json file. A peer discovery mechanism is used to set up the cluster. For this purpose, a `Config File Peer Discovery` method is used (see [RabbitMQ Docs](https://www.rabbitmq.com/cluster-formation.html#peer-discovery-classic-config) for reference), with all configuration located inside of `rabbitmq.conf`, which is also loaded on startup of each node.  

In scope of this project I used quorum queues instead of mirrored queues because of the latter ones being deprecated and highly discouraged by the RabbitMQ documentation.

## Running the project
1. Set up the RabbitMQ cluster
- Start the rabbit containers:

   ```cd startRabbit && ./run_rabbit_cluster.sh```

- Wait for the rabbit nodes to start and set up the cluster
- (Optionally) Run the `replicate_queues.sh` script to make sure the quorum queue from `rabbit1` node is replicated to the other nodes

2. Launch Listener's Spring Boot app
3. Launch Sender's Spring Boot app
4. Observe the logs on Listener's console

## Cluster availability

Using quorum queues replicated across all nodes means that the cluster assures high availability upon failure of one of the nodes. To test this behaviour, simulate a failure of one of the nodes that either the listener or the sender is connected to. 

From the output logs of both Spring apps you can get the information about the node that the app is currently connected to - look for the port in a string similar to this one(in this case the port is 30004):
```
Created new connection: listenerConnectionFactory#325f7fa9:0/SimpleConnection@4074023c [delegate=amqp://guest@127.0.0.1:30004/, localPort=63135]
``` 
Mapping ports to nodes:\
30000 - rabbit1\
30002 - rabbit2\
30004 - rabbit3

You can stop the node by executing the following commands sequentially:
```
docker exec -it rabbit1 bash
rabbitmqctl stop_app
```
or in one line:
```
docker exec -it rabbit1 bash rabbitmqctl stop_app
```

and observe the effect on the UI (30001: rabbit1, 30003: rabbit2, 30005: rabbit3, login: guest, password: guest) in the `Nodes` section (see the numbers changing under `socket descriptors`). The app should automatically reconnect to one of the remaining nodes. 

If you restart the stopped node: 
```
docker exec -it rabbit1 bash
rabbitmqctl start_app
```
or:
```
docker exec -it rabbit1 bash rabbitmqctl stop_app
```
it should rejoin the cluster (however the connections stay the same, old connections aren’t being re-established).

## Note
The replication mechanism of quorum queues is triggered automatically on startup of the rabbit1 node. It means that if rabbit1 makes an attempt to replicate the queue to other nodes, some of them might not yet have joined the cluster, which often leads to 1 or 2 nodes being left without any quorum queue replica. Therefore I’ve made rabbit1 container depend on rabbit2 and rabbit3, however there is still a chance that rabbit1 might start replicating before rabbit2 or rabbit3 join the cluster. If this scenario happens, an additional script `replicate_queues.sh` should be executed manually after the cluster is set up. 
