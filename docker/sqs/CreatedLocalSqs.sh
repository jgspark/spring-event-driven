#!/usr/bin

awslocal sqs create-queue \
    --queue-name sample-queue7.fifo \
    --attributes \
        FifoQueue=true,ContentBasedDeduplication=true,FifoThroughputLimit=3000,MessageDeduplicationIdAgeLimit=30
