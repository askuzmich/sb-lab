# sb-lab DB_MODEL

GET /api/v1/subEntities/{id} get subEntity by ID </br>
EXAMPLE: localhost:8080/api/v1/subEntities/110066 </br>
SHOULD RETURN:
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
