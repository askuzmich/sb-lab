# sb-lab DB_MODEL

## GET (getByID) subEntity
GET: /api/v1/subEntities/{id}  </br>
EXAMPLE: localhost:8080/api/v1/subEntities/110066 </br>
200: SHOULD RETURN:
```
{
    "isSuccess": true,
    "statusCode": 200,
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
404:
```
{
    "isSuccess": false,
    "statusCode": 404,
    "message": "Not find subEntity with ID: 110067",
    "data": null
}
```


## GET (getALL) subEntity
GET /api/v1/subEntities </br>
EXAMPLE: localhost:8080/api/v1/subEntities </br>
200: SHOULD RETURN:
```
{
    "isSuccess": true,
    "statusCode": 200,
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
```
{
    "name": "se10",
    "description": "woo-hoo se10",
    "imgUrl": "https://fakeImageUrl.com/se10"
}
```
200: SHOULD RETURN:
```
{
    "isSuccess": true,
    "statusCode": 200,
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
400: FAIL:
```
{
    "isSuccess": false,
    "statusCode": 400,
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
```
{
    "name": "se10",
    "description": "woo-hoo se10",
    "imgUrl": "https://fakeImageUrl.com/se10"
}
```
200: SHOULD RETURN:
```
{
    "isSuccess": true,
    "statusCode": 200,
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
404: NOT FOUND EL WITH ID
```
{
    "isSuccess": false,
    "statusCode": 404,
    "message": "Not find subEntity with ID: 110069",
    "data": null
}
```





## DELETE subEntity
DELETE /api/v1/subEntities/{id} </br>
EXAMPLE: localhost:8080/api/v1/subEntities/110055 </br>
200: SHOULD RETURN:
```
{
    "isSuccess": true,
    "statusCode": 200,
    "message": "Transaction is Ok",
    "data": null
}
```
404: NOT FOUND EL WITH ID
```
{
    "isSuccess": false,
    "statusCode": 404,
    "message": "Not find subEntity with ID: 110055",
    "data": null
}
```
