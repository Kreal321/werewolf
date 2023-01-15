const HOST = "http://10.0.0.222:8080";

// Create a web socket connection
let sock = new SockJS(HOST + '/stomp');

sock.onopen = function() {
    console.log('sock open');
};

sock.onmessage = function(e) {
    console.log('sock receive message', e.data);
};

sock.onclose = function() {
    console.log('sock close');
};


// Create a new StompClient object with the WebSocket endpoint
let client = Stomp.over(sock);