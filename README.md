# Clean Architecture
## Description
This repository uses Clean Architecture in my interpretation to solve the borrowing problem.
The description of the problem can be found [here.](https://github.com/backend-br/desafios/blob/master/loans/PROBLEM.md)

## Api Routes
__Root url__: localhost:8080

- /loans
- POST
```json
{
  "age": 28,
  "cpf": "000.000.000-00",
  "name": "John Doe",
  "income": 5000,
  "location": "SP"
}
```

## Running 
- Compile the project.
- Start the application.
- Access endpoint /loans with HTTP post method.

## References
- [Robert C. Martin (Uncle Bob)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
