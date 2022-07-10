# A Simple Spring Microservice

![alt text](https://pbs.twimg.com/media/FXIAb_NUUAAzc5R?format=jpg&name=small)

## Summary
- Started miltiple microservices
- Let them communicate each other using RestTemplate and WebClient (Hard coded URLs)
- Use the concept of Service Discovery
- Create a Euraka Server
- All these microservices register in Euraka client by adding them to the class path
- Use @LoadBalance on RestTemplate to leverage Euraka server with way minimun code changes 
