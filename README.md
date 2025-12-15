# EComerce_Torres_API (Spring Boot)

Proyecto  **API REST con CRUD de productos**.

## Requisitos
- Java 17
- Maven
- IntelliJ IDEA 



## Endpoints (CRUD)
- GET    `/api/products`
- GET    `/api/products/{id}`
- POST   `/api/products`  (devuelve **201 CREATED**)
- PUT    `/api/products/{id}`
- DELETE `/api/products/{id}` (devuelve **204 NO CONTENT**)

### Filtros
- GET `/api/products?name=fender`
- GET `/api/products?price=99.99`
- GET `/api/products?name=pedal&price=99.99`

## Validaciones 
- Si falta algún campo → **400 BAD REQUEST**
- Si no existe el id → **404 NOT FOUND**
- Errores inesperados → **500 INTERNAL SERVER ERROR**

## Probado con Postman
Body usado de ejemplo en el POST:
```json
{
  "name": "Piano Digital Roland FP-10",
  "price": 599.99,
  "description": "Piano digital con teclado PHA-4.",
  "category": "Teclados"
}
```
