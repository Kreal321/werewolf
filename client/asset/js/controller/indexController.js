// autoConnect();
var sock = new SockJS('http://localhost:8080/stomp');

sock.onopen = function() {
    console.log('open');
};

sock.onmessage = function(e) {
    console.log('message', e.data);
};

sock.onclose = function() {
    console.log('close');
};

// Create a new StompClient object with the WebSocket endpoint
let client = Stomp.over(sock);

// Start the STOMP communications, provide a callback for when the CONNECT frame arrives.
client.connect({}, frame => {
    // Subscribe to "/topic/messages". Whenever a message arrives add the text in a list-item element in the unordered list.
    client.subscribe("/api/message/subscription/" + document.getElementById("room-input").value, payload => {
    

        console.log(payload.body);

        let message_list = document.getElementById('message-list');
        let message = document.createElement('li');
        
        message.appendChild(document.createTextNode(JSON.parse(payload.body).message));
        message_list.appendChild(message);

    });

});

// Take the value in the ‘message-input’ text field and send it to the server with empty headers.
function sendMessage(){

    let input = document.getElementById("message-input");
    let message = input.value;
    
    client.send('/api/message/new/' + document.getElementById("room-input").value, {}, JSON.stringify({message: message}));


}
