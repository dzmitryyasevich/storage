# storage
Perfume Storage Service

http://localhost:8080/v2/api-docs - API documentation

http://localhost:8080/products?name=Versace%20Eros - search by product name

http://localhost:8080/products?brand=Versace - search by brand name

http://localhost:8080/products?brand=Versace%20Eros&name=Versace%20Eros - search by brand name and product name

http://localhost:8080/products?limit=5 - find products, which are going to be sold. limit=5 means that the REST returns products with quentity less or equal 5.


http://localhost:8080/products/download?brand=Versace%20Eros - download search results by brand name 

http://localhost:8080/products/download?format=xls - export report in xls
