# sb-lab DB_MODEL

## GET (getByID) subEntity
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

### GET getById Not Found: </br>
EXAMPLE: localhost:8080/api/v1/subEntities/110067 </br>
statusCode: 404; isSuccess: false;
```json
{
    "message": "Not find subEntity with ID: 110067",
    "data": null
}
```


## GET (getALL) subEntity
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




## POST (ADD) subEntity
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


## GET allHeadObjects
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



## GET getHeadObjectById
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


## POST make new HeadObject
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



## UPDATE HeadObject
PUT /api/v1/headObjects/12 </br>
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



## DELETE headObjectById
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
```json
{
    "message": "Not find Head Object with ID: 1",
    "data": null
}
```

