Consumer app runs on port 8080 

Producer app runs on port 8081 

To test endpoints use curl:

```
curl --location --request POST 'http://localhost:8081/uploadFile'
```