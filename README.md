# Oauth2

Learning and Testing Oauth2 Grant Types with Spring Security.

How to test:

1. Password Credentials Flow

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

Configuring Refresh Token Grant Type with Password Grant Type

We have configured Refresh Token Grant Type with Password Grant Type.

2. Password Grant Type with Refresh Token Grant Type

      2.1 Generate Access Token (Postman Request Example):
      curl --location --request POST 'localhost:8081/oauth/token' \
      --header 'Authorization: Basic c2Nob29sY2xpZW50OnNjaG9vbDEyMw==' \
      --header 'Content-Type: application/x-www-form-urlencoded' \
      --data-urlencode 'grant_type=password' \
      --data-urlencode 'username=lucasuser' \
      --data-urlencode 'password=123456'

      2.2 Copy Access Token and Make a new Request to Resource Server using it as Bearer Token:

      curl --location --request GET 'localhost:8080/student/1' \
      --header 'Authorization: Bearer 678b9134-67cc-4963-951b-35ea22737340'

      2.3 When Access Token Expires, we need to request a new Access Token using Refresh Token, no more Resource Owner (User) credentials. Request Example:

      curl --location --request POST 'localhost:8081/oauth/token' \
      --header 'Authorization: Basic c2Nob29sY2xpZW50OnNjaG9vbDEyMw==' \
      --header 'Content-Type: application/x-www-form-urlencoded' \
      --header 'Cookie: JSESSIONID=34C10FDEBCEF3F916FC3ED950F4E08D6' \
      --data-urlencode 'refresh_token=3ad1c72c-b71c-4d19-9367-ed25376f8a0f' \
      --data-urlencode 'grant_type=refresh_token'

      2.4 Now with new Access Token make requests again until it expires.
