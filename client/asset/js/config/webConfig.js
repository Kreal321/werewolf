const wsURL = "localhost:8080";

// create new websocket connection
function autoConnect() {
    
    ws = new WebSocket("ws://" + wsURL + "/api/broadcast/new");
        
    ws.addEventListener('open', (event)=>{
        console.log("connected");
    });
    ws.addEventListener('error', (event)=>{
        console.log(event);
    })

    // Receive a new message
    ws.addEventListener('message', receiveMsg);
}

function receiveMsg(event) {
    var msg = JSON.parse(event.data);

    console.log(msg);
}


/**
 * Send a new message and encoding to json
 * 
 * @param {*} data data to send
 * @param {int} type 0: server message, 1: client message, 2: simpi config queue, 3: Multi-client management
 */

function sendMsg(data, type = 1){
    try {
        msg = {data: data, type: type}
        ws.send(JSON.stringify(msg));
        log("Message send to server: " + data);
    } catch (error) {
        document.getElementById("h").innerHTML= stuff;
        log(error, true);
    }
}