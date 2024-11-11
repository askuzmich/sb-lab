# SB-LAB PROJECT DOCUMENTATION (API)



# HEADOBJECT API CRUD

## GET get subEntity by ID
GET: /api/v1/subEntities/{id}  </br>
```bash
curl -X GET localhost:80/api/v1/subEntities/110066 -v
```
EXAMPLE: localhost:80/api/v1/subEntities/110066 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": {
        "id": "110066",
        "name": "se5",
        "description": "woo-hoo se5",
        "imgUrl": "https://fakeImageUrl.com/se5",
        "owner": {
            "id": 1,
            "name": "HeadObject 1",
            "hasSubEntities": 1
        }
    }
}
```
### not success:
EXAMPLE: localhost:80/api/v1/subEntities/110067 </br>
```bash
curl -X GET localhost:80/api/v1/subEntities/110067 -v
```
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find subEntity with ID: 110067",
    "data": null
}
```


## GET get ALL subEntities
GET /api/v1/subEntities </br>
```bash
curl -X GET localhost:80/api/v1/subEntities -v
```
EXAMPLE: localhost:80/api/v1/subEntities </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": [
        {
            "id": "110022",
            "name": "se1",
            "description": "woo-hoo se1",
            "imgUrl": "https://fakeImageUrl.com/se1",
            "owner": {
                "id": 1,
                "name": "HeadObject 1",
                "hasSubEntities": 1
            }
        },
        {
            "id": "110033",
            "name": "se2",
            "description": "woo-hoo se2",
            "imgUrl": "https://fakeImageUrl.com/se2",
            "owner": {
                "id": 1,
                "name": "HeadObject 1",
                "hasSubEntities": 1
            }
        },
        {
            "id": "110066",
            "name": "se5",
            "description": "woo-hoo se5",
            "imgUrl": "https://fakeImageUrl.com/se5",
            "owner": {
                "id": 1,
                "name": "HeadObject 1",
                "hasSubEntities": 1
            }
        }
    ]
}
```



## POST add subEntity
POST /api/v1/subEntities </br>
```bash
curl -X POST localhost:80/api/v1/subEntities/110067 -v
```
EXAMPLE: localhost:80/api/v1/subEntities </br>
REQUEST BODY:
```json
{
    "name": "se10",
    "description": "woo-hoo se10",
    "imgUrl": "https://fakeImageUrl.com/se10"
}
```
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "body": {
        "id": "5873cf16-cef6-49f0-83d6-fc31a53d90c7",
        "name": "se10",
        "description": "woo-hoo se10",
        "imgUrl": "https://fakeImageUrl.com/se10",
        "owner": null
    }
}
```
### not success:
statusCode: 400; isSuccess: false;
```json
{
    "message": "Data is wrong",
    "body": {
        "name": "is required",
        "description": "is required",
        "imgUrl": "is required"
    }
}
```



## UPDATE (PUT) subEntity
PUT /api/v1/subEntities/{id} </br>
EXAMPLE: localhost:80/api/v1/subEntities/110066 </br>
REQUEST BODY:
```json
{
    "name": "se10",
    "description": "woo-hoo se10",
    "imgUrl": "https://fakeImageUrl.com/se10"
}
```
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": {
        "id": "110066",
        "name": "se10",
        "description": "woo-hoo se10",
        "imgUrl": "https://fakeImageUrl.com/se10",
        "owner": null
    }
}
```
### not success:
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find subEntity with ID: 110069",
    "data": null
}
```


## DELETE subEntity
DELETE /api/v1/subEntities/{id} </br>
EXAMPLE: localhost:80/api/v1/subEntities/110055 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": null
}
```
### not success:
404: NOT FOUND EL WITH ID
```json
{
    "message": "Not find subEntity with ID: 110055",
    "data": null
}
```





# HEADOBJECT API CRUD


## GET all HeadObjects
GET /api/v1/headObjects </br>
EXAMPLE: localhost:80/api/v1/headObjects </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": [
    {
      "id": 1,
      "name": "HeadObject 1",
      "hasSubEntities": 1
    },
    {
      "id": 2,
      "name": "HeadObject 2",
      "hasSubEntities": 2
    },
    {
      "id": 3,
      "name": "HeadObject 3",
      "hasSubEntities": 1
    }
  ]
}
```



## GET get HeadObject by ID
GET /api/v1/headObjects/{1} </br>
EXAMPLE: localhost:80/api/v1/headObjects/1 </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": {
      "id": 1,
      "name": "HeadObject 1",
      "hasSubEntities": 1
    }
}
```
### not success:
EXAMPLE: localhost:80/api/v1/headObjects/4 </br>
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find Head Object with ID: 4",
  "data": null
}
```


## POST (ADD) - make new HeadObject
POST /api/v1/headObjects </br>
EXAMPLE: localhost:80/api/v1/headObjects </br>
REQUEST BODY:
```json
{
  "name": "New Head Object"
}
```
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": {
    "id": 4,
    "name": "New Head Object",
    "hasSubEntities": 0
  }
}
```
### not success:
statusCode: 400; isSuccess: false;
```json
{
  "message": "Provided arguments are invalid, see data for details.",
  "data": {
    "name": "name is required."
  }
}
```



## UPDATE assignment of SubEntity to HeadObject
PUT /api/v1/headObjects/{hid}/subEntities/{sid} </br>
EXAMPLE: localhost:80/api/v1/headObjects/12/subEntities/1 </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Assignment is Ok",
  "data": null
}
```
### not success:
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find {subEntity | Head Object | [BOTH]} with ID: 1",
  "data": null
}
```



## UPDATE HeadObject
PUT /api/v1/headObjects/{id} </br>
EXAMPLE: localhost:80/api/v1/headObjects/12 </br>
REQUEST BODY:
```json
{
  "name": "Head Object #12"
}
```
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": {
    "id": 12,
    "name": "Head Object #12",
    "hasSubEntities": 0
  }
}
```
### not success:
statusCode: 400; isSuccess: false;
```json
{
  "message": "Provided arguments are invalid, see data for details.",
  "data": {
    "name": "name is required."
  }
}
```
"/api/v1/headObjects/12" </br>
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find Head Object with ID: 12",
  "data": null
}
```



## DELETE headObject (by ID)
DELETE /api/v1/headObjects/{id} </br>
EXAMPLE: localhost:80/api/v1/headObjects/1 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": null
}
```
### not success:
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find Head Object with ID: 1",
    "data": null
}
```






# USERS API CRUD


## GET all users
GET /api/v1/users </br>
если нет токена, регистрируемся:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Alexander:Alexander -v
```
...then send Bearer Token and get All Users:
```bash
curl http://localhost:80/api/v1/users -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiQWxleGFuZGVyIiwiZXhwIjoxNzMwNDQ0MzA3LCJpYXQiOjE3MzA0MzcxMDcsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.CM0uDzj5jwCifVM1p0VVTfjvBVKZN9N4gBuqTcwGm8aDUuACBbQhhtDo87BfA536grdyT1NLV18HFQW4dXEW87q6EUC-w3icPgRcaFHVyDj_fgc10HenUPhjnqkYpM61jqSX0CRj2JkeLA2HN_YYDlo7tdlVLtsCrbdqF_QiTgGy8QzIK6So2gbFyKwLRd9lLeHJOtcidihn29fXBt2MgEd7xGj-yod3vR0VfCODdmRfmi4_agTbyiGMDiHsdUJt6KUcUgzdz2hT474yg6b-kht6eG6YZapckRRrqEJkflTT3b8pjaTu2vYkdIdVIgiQwvWOtplbo9q17hYVBDZsyg" -v
```
EXAMPLE: localhost:80/api/v1/users </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": [
    {
      "id": 1,
      "name": "Alexander",
      "roles": "ADMIN",
      "enabled": true
    },
    {
      "id": 2,
      "name": "Sergey",
      "roles": "USER",
      "enabled": true
    },
    {
      "id": 3,
      "name": "Everybody",
      "roles": "USER",
      "enabled": false
    }
  ]
}
```



## GET user by ID
GET /api/v1/users/{1} </br>
если нет токена, регистрируемся:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Alexander:Alexander -v
```
...then send Bearer Token and get User By ID:
```bash
curl http://localhost:80/api/v1/users/1 -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiQWxleGFuZGVyIiwiZXhwIjoxNzMwNDQ0MzA3LCJpYXQiOjE3MzA0MzcxMDcsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.CM0uDzj5jwCifVM1p0VVTfjvBVKZN9N4gBuqTcwGm8aDUuACBbQhhtDo87BfA536grdyT1NLV18HFQW4dXEW87q6EUC-w3icPgRcaFHVyDj_fgc10HenUPhjnqkYpM61jqSX0CRj2JkeLA2HN_YYDlo7tdlVLtsCrbdqF_QiTgGy8QzIK6So2gbFyKwLRd9lLeHJOtcidihn29fXBt2MgEd7xGj-yod3vR0VfCODdmRfmi4_agTbyiGMDiHsdUJt6KUcUgzdz2hT474yg6b-kht6eG6YZapckRRrqEJkflTT3b8pjaTu2vYkdIdVIgiQwvWOtplbo9q17hYVBDZsyg" -v
```
EXAMPLE: localhost:80/api/v1/users/1 </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": {
    "id": 1,
    "name": "Alexander",
    "roles": "ADMIN",
    "enabled": true
  }
}
```
### not success:
EXAMPLE: localhost:80/api/v1/users/5 </br>
если нет токена, регистрируемся:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Alexander:Alexander -v
```
...then send a required data
```bash
curl http://localhost:80/api/v1/users/5 -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiQWxleGFuZGVyIiwiZXhwIjoxNzMwNDQ0MzA3LCJpYXQiOjE3MzA0MzcxMDcsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.CM0uDzj5jwCifVM1p0VVTfjvBVKZN9N4gBuqTcwGm8aDUuACBbQhhtDo87BfA536grdyT1NLV18HFQW4dXEW87q6EUC-w3icPgRcaFHVyDj_fgc10HenUPhjnqkYpM61jqSX0CRj2JkeLA2HN_YYDlo7tdlVLtsCrbdqF_QiTgGy8QzIK6So2gbFyKwLRd9lLeHJOtcidihn29fXBt2MgEd7xGj-yod3vR0VfCODdmRfmi4_agTbyiGMDiHsdUJt6KUcUgzdz2hT474yg6b-kht6eG6YZapckRRrqEJkflTT3b8pjaTu2vYkdIdVIgiQwvWOtplbo9q17hYVBDZsyg" -v
```
statusCode: 400; isSuccess: false;
```json
{
  "message": "Not find User with ID: 5",
  "data": null
}
```


## POST make new user
POST /api/v1/users </br>
если нет токена, регистрируемся:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Alexander:Alexander -v
```
...then send a required data
```bash
curl -X POST http://localhost:80/api/v1/users -d '{"name": "newman", "password": "new-pass", "roles": "USER", "enabled": true}' -H "Content-Type: application/json" -H "Accept: application/json" -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiQWxleGFuZGVyIiwiZXhwIjoxNzMwNDQ0MzA3LCJpYXQiOjE3MzA0MzcxMDcsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.CM0uDzj5jwCifVM1p0VVTfjvBVKZN9N4gBuqTcwGm8aDUuACBbQhhtDo87BfA536grdyT1NLV18HFQW4dXEW87q6EUC-w3icPgRcaFHVyDj_fgc10HenUPhjnqkYpM61jqSX0CRj2JkeLA2HN_YYDlo7tdlVLtsCrbdqF_QiTgGy8QzIK6So2gbFyKwLRd9lLeHJOtcidihn29fXBt2MgEd7xGj-yod3vR0VfCODdmRfmi4_agTbyiGMDiHsdUJt6KUcUgzdz2hT474yg6b-kht6eG6YZapckRRrqEJkflTT3b8pjaTu2vYkdIdVIgiQwvWOtplbo9q17hYVBDZsyg" -v
```
EXAMPLE: localhost:80/api/v1/users </br>
REQUEST BODY:
```json
{
  "name": "newman",
  "password": "new-pass",
  "roles": "USER",
  "enabled": true
}
```
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": {
    "id": 4,
    "name": "newman",
    "roles": "USER",
    "enabled": true
  }
}
```
### not success:
statusCode: 400; isSuccess: false;
```json
{
  "message": "see what wrong in data",
  "data": {
    "name": "name is required",
    "password": "password is required",
    "roles": "space separated roles are required"
  }
}
```



## UPDATE user
PUT /api/v1/users/{id} </br>
если нет токена, регистрируемся:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Alexander:Alexander -v
```
...then send a required data
```bash
curl -X PUT http://localhost:80/api/v1/users/3 -d '{"name": "updated-name", "roles": "USER"}' -H "Content-Type: application/json" -H "Accept: application/json" -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiQWxleGFuZGVyIiwiZXhwIjoxNzMwNDQ0MzA3LCJpYXQiOjE3MzA0MzcxMDcsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.CM0uDzj5jwCifVM1p0VVTfjvBVKZN9N4gBuqTcwGm8aDUuACBbQhhtDo87BfA536grdyT1NLV18HFQW4dXEW87q6EUC-w3icPgRcaFHVyDj_fgc10HenUPhjnqkYpM61jqSX0CRj2JkeLA2HN_YYDlo7tdlVLtsCrbdqF_QiTgGy8QzIK6So2gbFyKwLRd9lLeHJOtcidihn29fXBt2MgEd7xGj-yod3vR0VfCODdmRfmi4_agTbyiGMDiHsdUJt6KUcUgzdz2hT474yg6b-kht6eG6YZapckRRrqEJkflTT3b8pjaTu2vYkdIdVIgiQwvWOtplbo9q17hYVBDZsyg" -v
```
EXAMPLE: localhost:80/api/v1/users/3 </br>
REQUEST BODY:
```json
{
  "name": "updated-name",
  "roles": "USER"
}
```
statusCode: 200; isSuccess: true;
```json
{
  "message": "Transaction is Ok",
  "data": {
    "id": 2,
    "name": "updated-name",
    "roles": "USER",
    "enabled": false
  }
}
```
### not success:
statusCode: 400; isSuccess: false;
```json
{
  "message": "see what wrong in data",
  "data": {
    "name": "name is required",
    "roles": "space separated roles are required"
  }
}
```
### not success: "/api/v1/users/5"
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find User with ID: 5",
  "data": null
}
```



## DELETE user (by ID)
DELETE /api/v1/users/{id} </br>
Try: </br>
Login:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Alexander:Alexander -v
```
...then send a required data
```bash
curl -X DELETE http://localhost:80/api/v1/users/3 -H "Content-Type: application/json" -H "Accept: application/json" -H "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiQWxleGFuZGVyIiwiZXhwIjoxNzMwNDQ0MzA3LCJpYXQiOjE3MzA0MzcxMDcsImF1dGhvcml0aWVzIjoiUk9MRV9BRE1JTiJ9.CM0uDzj5jwCifVM1p0VVTfjvBVKZN9N4gBuqTcwGm8aDUuACBbQhhtDo87BfA536grdyT1NLV18HFQW4dXEW87q6EUC-w3icPgRcaFHVyDj_fgc10HenUPhjnqkYpM61jqSX0CRj2JkeLA2HN_YYDlo7tdlVLtsCrbdqF_QiTgGy8QzIK6So2gbFyKwLRd9lLeHJOtcidihn29fXBt2MgEd7xGj-yod3vR0VfCODdmRfmi4_agTbyiGMDiHsdUJt6KUcUgzdz2hT474yg6b-kht6eG6YZapckRRrqEJkflTT3b8pjaTu2vYkdIdVIgiQwvWOtplbo9q17hYVBDZsyg" -v
```
EXAMPLE: localhost:80/api/v1/users/3 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": null
}
```
### not success:
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find User with ID: 12",
    "data": null
}
```








# USERS OAUTH2 JWT API



## Authentication of user by login and password -> token
POST /api/v1/users/login/ </br>
как получить токен через консоль или приложение:
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Sergey:Sergey -v
```
как получить токен через браузер:
```html
<script>
    fetch(
      'http://localhost:80/api/v1/users/login',
      {
          method: 'POST',
          headers: {
              Authorization: "Basic " + btoa('Sergey:Sergey')
          }
      }
    )
    .then((resp) => resp.json())
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.log(error);
    });
</script>
```
statusCode: 200; isSuccess: true;
```json
{
  "isSuccess": true,
  "statusCode": 200,
  "message": "Login user info and JWT",
  "data": {
    "user": {
      "id": 2,
      "name": "Sergey",
      "roles": "USER",
      "enabled": true
    },
    "token": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiU2VyZ2V5IiwiZXhwIjoxNzMwNDQ1NzY2LCJpYXQiOjE3MzA0Mzg1NjYsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.L13KPSf2I_zahJXU3hfWSLDBBKVIG7OOVZ6NPkyMpPlxSDmYdiu9qAaYGif0nU5SnlwpcUeXbpxFZo_twXHw3yxXRqSdhwUYDaKST8NaxhxujLsLM3BEaxdxX-5Rp3GEyV0uxpygQ1saNGz4mpkA0bG0DrHWkwbKe9x5La6FCl1YYto3buJjeEPpRLuN40GyJSJRoO3NHAsnIa27PS4iwlGFbFhBk5HScx7s_HwXxNPjnUnoet5n4d9pWBZ3tBvMRA7a0Jis_pqUmqVJdzLSpnlmILyGaAVKz3hu4OOelu-3ZX7Z1YBc5hAHAhwgYodJQyjzKYuBSLMpEtcvyDwc3A"
  }
}
```
### not success: wrong username or password
```bash
curl -X POST http://localhost:80/api/v1/users/login -u Sergey:WrongPassword -v
```
statusCode: 401; isSuccess: false;
```json
{
  "message":"Wrong Username or Password",
  "data":"Bad credentials"
}
```

</br></br>

# Making JAR file

```bash
./mvnw package -DskipTests
```

## Extracting layers from the Jar File in order to make a Docker Image
```bash
java -Djarmode=layertools -jar demo-0.0.1-SNAPSHOT.jar extract
```

</br></br>

# Docker

## making | build a Docker Image
```bash
docker build -t alx/sb-lab:1.0 .
```
## просмотреть все images
```bash
docker images
```
## запустить Docker Image
```bash
docker run -d -p 80:80 alx/sb-lab:1.0
```
## просмотреть запущен ли наш Docker Container (смотреть поле STATUS: "Up.." или "Exited..")
```bash
docker ps -a
```
## остановить Docker Container (cd4fbea is Container ID)
```bash
docker stop cd4fbea
```
## запустить созданный Docker Container (cd4fbea is Container ID)
```bash
docker start cd4fbea
```