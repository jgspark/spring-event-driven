#!/usr/bin

# User Visits Queue Created
awslocal sqs create-queue \
    --queue-name user-visits1.fifo \
    --attributes \
        FifoQueue=true,ContentBasedDeduplication=true,FifoThroughputLimit=3000,MessageDeduplicationIdAgeLimit=30

# Send Mail Queue Created
awslocal sqs create-queue \
    --queue-name send-mail1.fifo \
    --attributes \
        FifoQueue=true,ContentBasedDeduplication=true,FifoThroughputLimit=3000,MessageDeduplicationIdAgeLimit=30
