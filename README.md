# Oauth2

Learning and Testing Oauth2 Grant Types with Spring Security.

How to test:

1. Password Credentials 

(Postman Request Example)

 1.1 Generate Access Token:
curl --location --request POST 'localhost:8081/oauth/token' \
--header 'Authorization: Basic c2Nob29sY2xpZW50OnNjaG9vbDEyMw==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=lucasuser' \
--data-urlencode 'password=123456'

1.2 Copy Access Token and Make a new Request to Resource Server using it as Bearer Token:

curl --location --request GET 'localhost:8080/student/1' \
--header 'Authorization: Bearer 678b9134-67cc-4963-951b-35ea22737340'
