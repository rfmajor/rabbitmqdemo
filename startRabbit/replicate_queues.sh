#!/usr/bin/env bash

docker exec -it rabbit1 bash -c "
rabbitmq-queues add_member flight.queue rabbit@rabbit2 ;
rabbitmq-queues add_member flight.queue rabbit@rabbit3
"