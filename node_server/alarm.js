const server = require("./server")

let isAlarmTriggered = false


function setAlarmTriggered(bool){
    isAlarmTriggered = bool
    console.log(`isAlarmTriggered set to ${isAlarmTriggered}`)
}


function setup(){
    listenIsAlarmTriggered()
    listenSetAlarmTriggered()
}

function listenIsAlarmTriggered(){
    server.getApp().get("/isAlarmTriggered", (req, res) => {
        res.send(isAlarmTriggered)
    })
}

function listenSetAlarmTriggered(){
    server.getApp().put("/setAlarmTriggered", (req, res) => {
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
}

module.exports = {
    setup
}