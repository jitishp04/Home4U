
const express = require('express')
const bodyParser = require('body-parser')

const app = express()
app.use(bodyParser.text())

const port = 8081;


function getApp(){
    return app
}


function start(){
    setupLogs()
    hostFiles()

    app.listen(port, () => {
        console.log(`Server listening on port ${port}`);
    })
}


function setupLogs(){
    app.use((req, res, next) => {
        if (req.method === 'GET') {
          console.log(`Received GET request for ${req.url}`);
        }
        next();
    });
}


// Host songs and song info
function hostFiles(){
    app.use(express.static('public'));
}



module.exports = {
    start,
    getApp
}