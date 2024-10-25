# SB-LAB PROJECT DOCUMENTATION (API)



# HEADOBJECT API CRUD

## GET get subEntity by ID 
GET: /api/v1/subEntities/{id}  </br>
EXAMPLE: localhost:8080/api/v1/subEntities/110066 </br>
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
EXAMPLE: localhost:8080/api/v1/subEntities/110067 </br>
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find subEntity with ID: 110067",
    "data": null
}
```


## GET get ALL subEntities
GET /api/v1/subEntities </br>
EXAMPLE: localhost:8080/api/v1/subEntities </br>
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
EXAMPLE: localhost:8080/api/v1/subEntities </br>
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
EXAMPLE: localhost:8080/api/v1/subEntities/110066 </br>
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
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find subEntity with ID: 110069",
    "data": null
}
```


## DELETE subEntity
DELETE /api/v1/subEntities/{id} </br>
EXAMPLE: localhost:8080/api/v1/subEntities/110055 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": null
}
```
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
EXAMPLE: localhost:8080/api/v1/headObjects </br>
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
EXAMPLE: localhost:8080/api/v1/headObjects/1 </br>
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
EXAMPLE: localhost:8080/api/v1/headObjects/4 </br>
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find Head Object with ID: 4",
  "data": null
}
```


## POST (ADD) - make new HeadObject
POST /api/v1/headObjects </br>
EXAMPLE: localhost:8080/api/v1/headObjects </br>
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
EXAMPLE: localhost:8080/api/v1/headObjects/12/subEntities/1 </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Assignment is Ok",
  "data": null
}
```
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find {subEntity | Head Object | [BOTH]} with ID: 1",
  "data": null
}
```



## UPDATE HeadObject
PUT /api/v1/headObjects/{id} </br>
EXAMPLE: localhost:8080/api/v1/headObjects/12 </br>
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
EXAMPLE: localhost:8080/api/v1/headObjects/1 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": null
}
```
statusCode: 404; isSuccess: false;
```
{
    "message": "Not find Head Object with ID: 1",
    "data": null
}
```






# USERS API CRUD


## GET all users
GET /api/v1/users </br>
EXAMPLE: localhost:8080/api/v1/users </br>
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
EXAMPLE: localhost:8080/api/v1/users/1 </br>
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
EXAMPLE: localhost:8080/api/v1/users/5 </br>
statusCode: 400; isSuccess: false;
```json
{
  "message": "Not find User with ID: 5",
  "data": null
}
```


## POST make new user
POST /api/v1/users </br>
EXAMPLE: localhost:8080/api/v1/users </br>
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
EXAMPLE: localhost:8080/api/v1/users/3 </br>
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
"/api/v1/users/5" </br>
statusCode: 404; isSuccess: false;
```json
{
  "message": "Not find User with ID: 5",
  "data": null
}
```



## DELETE user (by ID)
DELETE /api/v1/users/{id} </br>
EXAMPLE: localhost:8080/api/v1/users/3 </br>
statusCode: 200; isSuccess: true;
```json
{
    "message": "Transaction is Ok",
    "data": null
}
```
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find User with ID: 12",
    "data": null
}
```








# USERS OAUTH2 JWT API 



## Authentication of user by login and password
POST /api/v1/users/login/ </br>
EXAMPLE: localhost:8080/api/v1/users/login/ </br>
statusCode: 200; isSuccess: true;
```json
{
  "message": "Login user info and JWT",
  "data": {
    "user": {
      "name": "Oleg",
      "enabled": true,
      "roles": "ADMIN USER",
      "id": 1  
    },
    "token": "fdfgHjhbHJHJH^%#%$65456.........HGJhgDXcbLu89"
  }
}
```
statusCode: 401; isSuccess: false;

```json
{
  "message": "Username or password is wrong",
  "data": "something goes wrong"
}
```

Credentials: ROLE_ADMIN <br />
login:Alexander <br />
password:Alexander <br />
mac console:
```bash
curl -X POST http://localhost:8080/api/v1/users/login -u Alexander:Alexander -v
```
send Bearer Token and get All Users (for instance):
```bash
curl http://localhost:8080/api/v1/users -H "Authorization: Bearer {PUT_YOUR_TOKEN_HERE}" -v
```

in win console:
```console
curl.exe --user Alexander:Alexander http://localhost:8080/api/v1/users/login
```
