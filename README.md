# spring-event-driven

Event Driven Architecture with Spring &amp; SNS &amp; SQS

## Explanation

This repository is a test repository for using "Event Driven Architecture" in virtual "user service".

From a small MSA point of view, we aim to implement using SNS and SQS.

## Skill

1. Spring Boot
    1. Spring JPA
    2. Spring Event
    3. Spring Cloud
2. Java
    1. version is 17
3. gradle
4. database
    1. mysql
    2. h2
4. docker
    1. mysql
    2. localstack
7. testing
    1. jmeter
    2. junit5
7. Aws
    1. SNS
    2. SQS

## Architecture

<img width="1024" alt="스크린샷 2023-05-05 오후 1 44 28" src="https://user-images.githubusercontent.com/53357210/236379315-22da3120-c295-4509-aadf-26ba86e73633.png">

## Action required to run

This command creates SQS in the project.

```bash
awslocal sqs create-queue \
    --queue-name user-visits1.fifo \
    --attributes \
        FifoQueue=true,ContentBasedDeduplication=true,FifoThroughputLimit=3000,MessageDeduplicationIdAgeLimit=30
```

```bash
awslocal sqs create-queue \
    --queue-name send-mail1.fifo \
    --attributes \
        FifoQueue=true,ContentBasedDeduplication=true,FifoThroughputLimit=3000,MessageDeduplicationIdAgeLimit=30
```
