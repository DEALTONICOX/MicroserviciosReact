Guia rapida de Postman para catalog-service
Base URL: http://localhost:8081
Rutas usan prefijo /api/v1.

Variables sugeridas en Postman:
- baseUrl = http://localhost:8081
- adminRut = 11111111-1
- adminRol = ADMIN
- userRut = 22222222-2
- userRol = USER

Headers obligatorios en cada request:
- X-User-Rut: {{adminRut}} o {{userRut}}
- X-User-Rol: {{adminRol}} o {{userRol}}

Pasos express:
1) Crear coleccion "catalog-service" y definir variable {{baseUrl}}.
2) En cada request agregar headers anteriores (ADMIN requerido para CRUD de categorias y productos).
3) Probar endpoints:

Categorias (solo ADMIN):
- GET {{baseUrl}}/api/v1/categorias
- GET {{baseUrl}}/api/v1/categorias/{{id}}
- POST {{baseUrl}}/api/v1/categorias
  body: {"nombre":"Equipo","descripcion":"Pesas"}
- PUT {{baseUrl}}/api/v1/categorias/{{id}}
  body: {"nombre":"Equipo","descripcion":"Actualizado"}
- DELETE {{baseUrl}}/api/v1/categorias/{{id}}

Productos:
- GET {{baseUrl}}/api/v1/productos
- GET {{baseUrl}}/api/v1/productos/{{idCategoria}}/{{idProducto}}
- GET {{baseUrl}}/api/v1/productos/categoria/{{idCategoria}}
- POST {{baseUrl}}/api/v1/productos
  body: {"id":{"idCategoria":1,"idProducto":1},"nombre":"Mancuerna 10kg","descripcion":"Par ajustable","precio":29990,"stock":20}
- PUT {{baseUrl}}/api/v1/productos/{{idCategoria}}/{{idProducto}}
  body: {"id":{"idCategoria":1,"idProducto":1},"nombre":"Mancuerna 10kg","descripcion":"Actualizado","precio":29990,"stock":15}
- DELETE {{baseUrl}}/api/v1/productos/{{idCategoria}}/{{idProducto}}
- POST {{baseUrl}}/api/v1/productos/stock/reservar
  body: [{"idCategoria":1,"idProducto":1,"cantidad":2}]

Notas:
- CRUD de productos y categorias requiere header rol ADMIN.
- GET de productos y reservar stock acepta USER o ADMIN.
- Swagger disponible en {{baseUrl}}/swagger-ui.html
