# Oauth2

Learning and Testing Oauth2 Grant Types with Spring Security.

How to test:

<h1>1. Password Credentials Flow</h1>

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


<h1>2. Password Grant Type with Refresh Token Grant Type</h1>

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


<h1>3. Configuring Client Credentials Grant Type</h1>

      3.1 Request Example:
            curl --location --request POST 'localhost:8081/oauth/token' \
            --header 'Authorization: Basic YmFja2VuZC1jbGllbnQ6YmFja2VuZGNsaWVudDEyMw==' \
            --header 'Content-Type: application/x-www-form-urlencoded' \
            --header 'Cookie: JSESSIONID=34C10FDEBCEF3F916FC3ED950F4E08D6' \
            --data-urlencode 'grant_type=client_credentials'

<h1>4. Configuring Authorization Code Grant Type </h1>

      To Test:

      4.1 Access Link from Browser:
      http://localhost:8081/oauth/authorize?response_type=code&client_id=schoolanalytics&state=abc&redirect_uri=http://school-client-app

      4.2 Get the Auth Code:
      http://client-app/?code=uapag1&state=abc
      * If using Client App test State equals state sent.

      4.3 Request from Postman:
      curl --location --request POST 'localhost:8081/oauth/token' \
      --header 'Authorization: Basic c2Nob29sYW5hbHl0aWNzOnNjaG9vbDEyMw==' \
      --header 'Content-Type: application/x-www-form-urlencoded' \
      --header 'Cookie: JSESSIONID=EC5316C7389F74D8343CDA9484183410' \
      --data-urlencode 'code=LqMDa3' \
      --data-urlencode 'grant_type=authorization_code' \
      --data-urlencode 'redirect_uri=http://school-client-app'
      
<h1>5. Configuring Implicit Flow Grant Type [Legacy]</h1>

      Similar to Authorization Code, but instead return a Authorization Code it returns directly an Access Token to the Client.

      Testing:

      5.1 Request from browser:
      http://localhost:8081/oauth/authorize?response_type=token&client_id=schooladmin&state=abc&redirect_uri=http://school-client2-app

      5.2 Grant Client Access

      5.3 Take Access Token

      5.4 Request for resource using postman
