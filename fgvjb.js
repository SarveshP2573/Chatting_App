// demo.js

const http = require('http');

const PORT = 3000;

const server = http.createServer((req, res) => {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    
    const responseData = {
        message: "Hello from demo.js!",
        status: "Server is running successfully 🚀"
    };

    res.end(JSON.stringify(responseData));
});

server.listen(PORT, () => {
    console.log(`Server running at http://localhost:${PORT}`);
});
