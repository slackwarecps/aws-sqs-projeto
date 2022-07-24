# aws-sqs-projeto
aws-sqs-projeto


programador1

brew tap aws/tap
brew install aws-sam-cli

{
    "QueueUrls": [
        "https://sqs.us-east-1.amazonaws.com/401713041411/asgard-test"
    ]
}

### Escrevendo
aws sqs send-message --queue-url https://sqs.us-east-1.amazonaws.com/401713041411/asgard-test  --message-body "Ola xuxu! dois"

## lendo

aws sqs receive-message --queue-url https://sqs.us-east-1.amazonaws.com/401713041411/asgard-test


    {
        "Messages": [
            {
                "MessageId": "742d6136-94bb-4f2a-ab0e-08a2885d473d",
                "ReceiptHandle": "AQEBws1V9RTpNkYj0Y4/VY3/xtDSrbIyDYEl55BAZON1NshrlfROe62DnqkT+eBB0JyfphDLfmt2RW1fb1CK2AnfXBQymjTRRRa4ooLsj5D/R6RjqzymlVxBD2fRSlnXVo7bE5tfrHIlSe00BT2Z/1KpWsglFgeq/QYjDFND6XFGbzH6uAqVFH9PMi/EL4++aXUZEGgWi9PWGgQu08ftoMQqmPQp+oy00DuGFUwUp5pc3UlUWOmZA6Fbau1lvvtMoL997JLCxjnPnoflrXBnDrp7QyIm7HZuIM8M97bW5OgR30xdnMqXt3D52E+w1WDDYMsDdjgWTGe0A5tKZPNPHd0ZnD+1qvv5GELMgaLyUpv/y4BfqpEYDHczvhmjSltwF45Nt/pkO4EWS55VpaW1RBfXMw==",
                "MD5OfBody": "cd90abb990349cb8c46a8f5c14a44b21",
                "Body": "Ola xuxu"
            }
        ]
    }


# delete queue
aws sqs delete-queue --queue-url https://sqs.us-east-1.amazonaws.com/401713041411/asgard-teste
# listar
aws sqs list-queues
# criar
aws sqs create-queue --queue-name asgard-teste

export FILA=https://sqs.us-east-1.amazonaws.com/401713041411/asgard-teste

# apagar a mensagem
aws sqs delete-message --receipt-handle  "AQEB9NaYhNctYKqxFCrsA2cGaAp1UjuVNr+BXDrnxSdRMpbG2qPleaLeLvSamSKvRRi3aLmBJHXnchz8Yr0GErta6Qnujb9ej54VdonzjRogSiCTuOBLzrhQ4p1/4FEiAm83Xsa6n8BkYSkTV73M55zcHoCbWrP3WeB6Sv/C89Hv8ybdCLlr2RPg+N6SY2oacTiFTwMQPmYHrRcici3/xuHbfo17hOkG9CJ7ECw3EnT8BpCiy1yY1pyvTwnDQ0nXhyCj83vkuePu+IJ4c5Kn83AGfjbkKjfoQQoRhgipLPSvE696vQbb+w+M66AUvkjucX9Z6ThR4m6OZjJSt1LZ3IAP27Tj8XYe7W0WMY8MRZjIvy3p3kYqZ6InpocMPVxlz9qI" --queue-url https://sqs.us-east-1.amazonaws.com/401713041411/asgard-teste


# Comandos pra testar
    curl -X POST -d "text=This is a message" http://localhost:8080/sendMessage
    curl http://localhost:8080/receiveMessagesWithoutDeleteLimitedVisibilityTimeout
    curl http://localhost:8080/receiveMessagesWithoutDeleteLimitedVisibilityTimeout
    curl http://localhost:8080/receiveMessagesWithoutDeleteLimitedVisibilityTimeout
    curl http://localhost:8080/receiveMessagesWithoutDeleteLimitedVisibilityTimeout
    {"timestamp":"2021-10-16T12:19:20.362+00:00","status":500,"error":"Internal Server Error","message":"","path":"/receiveMessagesWithoutDeleteLimitedVisibilityTimeout"}
