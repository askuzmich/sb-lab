const fs = require("fs");
const jsonServer = require("json-server");
const path = require("path");

const decodeBase64 = (data) => {
  return Buffer.from(data, "base64").toString("ascii");
};

const server = jsonServer.create();

const router = jsonServer.router(path.resolve(__dirname, "db.json"));

server.use(jsonServer.defaults({}));
server.use(jsonServer.bodyParser);

/**
*  задержка - как в реальном АПИ
*/
server.use(async (req, res, next) => {
  await new Promise((res) => {
    setTimeout(res, 800);
  });

  next();
});

// var header = req.headers.authorization || '';       // get the auth header
// var token = header.split(/\s+/).pop() || '';        // and the encoded auth token
// var auth = Buffer.from(token, 'base64').toString(); // convert from base64
// var parts = auth.split(/:/);                        // split on colon
// var username = parts.shift();                       // username is first
// var password = parts.join(':');                     // everything else is the password
const authFilterByUsernameAndPassword = (req, res) => {
  try {
  // const u = req.body;
    if (!req.headers.authorization) {
      throw new Error("No auth header");
    }

    const hAuth = req.headers.authorization.split(" ") || "";

    if (hAuth[0] !== "Basic") {
      throw new Error("Not Basic auth");
    }

    const decode = decodeBase64(hAuth[1]);

    return decode.split(":") || "";
  } catch (e) {
    console.log(e);

    throw new Error(e.message);
  }
};

// Authentication of user by login and password -> token
// POST /api/v1/users/login
server.post("/api/v1/users/login", (req, res) => {
  try {
    const [username, password] = authFilterByUsernameAndPassword(req, res);

    const db = JSON.parse(fs.readFileSync(path.resolve(__dirname, "db.json"), "UTF-8"));

    const { users = [] } = db;

    const candidate = users.find(
      // password should be encoded in real API
      (user) => user.name === username && user.password === password,
    );

    if (candidate) {
      const { id, name, roles, enabled } = candidate;

      const CustomReturnData = {
        isSuccess: true,
        statusCode: 200,
        message: "Login user info and JWT",
        data: {
          user: { id, name, roles, enabled },
          // eslint-disable-next-line max-len
          token: "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiU2VyZ2V5IiwiZXhwIjoxNzMwNDQ1NzY2LCJpYXQiOjE3MzA0Mzg1NjYsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIn0.L13KPSf2I_zahJXU3hfWSLDBBKVIG7OOVZ6NPkyMpPlxSDmYdiu9qAaYGif0nU5SnlwpcUeXbpxFZo_twXHw3yxXRqSdhwUYDaKST8NaxhxujLsLM3BEaxdxX-5Rp3GEyV0uxpygQ1saNGz4mpkA0bG0DrHWkwbKe9x5La6FCl1YYto3buJjeEPpRLuN40GyJSJRoO3NHAsnIa27PS4iwlGFbFhBk5HScx7s_HwXxNPjnUnoet5n4d9pWBZ3tBvMRA7a0Jis_pqUmqVJdzLSpnlmILyGaAVKz3hu4OOelu-3ZX7Z1YBc5hAHAhwgYodJQyjzKYuBSLMpEtcvyDwc3A"
        }
      };

      return res.json(CustomReturnData);
    }

    return res.status(403).json({
      isSuccess: false,
      statusCode: 403,
      message: "User not found"
    });
  } catch (e) {
    console.log(e);

    return res.status(500).json({
      isSuccess: false,
      statusCode: 500,
      message: e.message
    });
  }
});

// eslint-disable-next-line
// server.use((req, res, next) => {
//   if (!req.headers.authorization) {
//     return res.status(403).json({
//       isSuccess: false,
//       statusCode: 403,
//       message: "AUTH ERROR"
//     });
//   }

//   next();
// });

server.use(router);

// запуск сервера
const API_SERVER_PORT = 8000;

server.listen(API_SERVER_PORT, () => {
  console.log(`server is running on ${API_SERVER_PORT} port`);
  console.log(`http://localhost:${API_SERVER_PORT}/api/v1/users/login`);
});