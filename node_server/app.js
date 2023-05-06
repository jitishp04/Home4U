
const express = require('express')
const bodyParser = require('body-parser')

const app = express()
app.use(bodyParser.text())

const port = 8081;

let isAlarmTriggered = false


// Host songs and song info
app.use(express.static('public'));


app.put("/setAlarmTriggered", (req, res) => {
    const input = req.body

    if(input === "true"){
        setAlarmTriggered(true)
        res.send(`isAlarmTriggered set to true`)
    } else if(input === "false"){
        setAlarmTriggered(false)
        res.send(`isAlarmTriggered set to false`)
    } else {
        const errMsg = `ERR: input must be either "true” or "false”, but was "${JSON.stringify(input)}"`

        console.log(errMsg)
        res.send(errMsg)
    }
})

function setAlarmTriggered(bool){
    isAlarmTriggered = bool
    console.log(`isAlarmTriggered set to ${isAlarmTriggered}`)
}


app.get("/isAlarmTriggered", (req, res) => {
    res.send(isAlarmTriggered)
})


app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
})