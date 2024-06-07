# How to run this project

## Create required Azure resources
The following resources are required:
- A ServiceBus namespace.
- 2 queues in the created ServiceBus namespace.

## Update project
- Input required properties in [application.yaml](src/main/resources/application.yaml).
- Update the queue name in [QueueNameConstant.java](src/main/java/rujche/problem/reproduce/constant/QueueNameConstant.java).

## Start the application
Run the application, the expected log is like this:
```text
...
..rvice.PeriodMessageSender        :  == Send message periodically. queue name = que001, message = Fri Jun 07 17:10:02 CST 2024
..QueueReceiveThenSendService      :  - ServiceBusJmsApp - Received message. queue name = que001, message = Fri Jun 07 17:10:02 CST 2024.
..QueueReceiveThenSendService      :  - ServiceBusJmsApp - Sending message. queue name = que002, message = Fri Jun 07 17:10:02 CST 2024
..QueueReceiveThenSendService      :  - ServiceBusJmsApp - Send message succeed. queue name = que002, message = Fri Jun 07 17:10:02 CST 2024
..rvice.PeriodMessageSender        :  == Send message periodically. queue name = que001, message = Fri Jun 07 17:10:06 CST 2024
..QueueReceiveThenSendService      :  - ServiceBusJmsApp - Received message. queue name = que001, message = Fri Jun 07 17:10:06 CST 2024.
..QueueReceiveThenSendService      :  - ServiceBusJmsApp - Sending message. queue name = que002, message = Fri Jun 07 17:10:06 CST 2024
..QueueReceiveThenSendService      :  - ServiceBusJmsApp - Send message succeed. queue name = que002, message = Fri Jun 07 17:10:06 CST 2024
...
```

- `PeriodMessageSender` will send message to **queue 1** every 3 seconds.
- `QueueReceiveThenSendService` will receive message from **queue 1** and send to **queue 2**.


