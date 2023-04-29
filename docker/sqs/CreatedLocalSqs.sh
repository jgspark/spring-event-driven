#!/usr/bin

awslocal sqs create-queue --queue-name "${1}" --profile testUser
