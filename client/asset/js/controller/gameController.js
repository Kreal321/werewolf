const roomMessages = document.getElementById("room-messages");
const roomSeats = document.getElementById("room-seats");
const roomName = window.localStorage.getItem("roomName");

// room name validation
if (!roomName) {
    swal({
        title: "错误",
        text: "未加入任何房间",
        icon: "error",
    }).then((value) => {
        window.location.replace("index.html");
    });
}


const gameJson = {
    numOfPlayers: 6,
    playerList: [new Player("Tom"), null, new Player("Ben"), null, new Player("Hi"), null]
};

const seatList = []; 

function initializeSeats(game) {
    game.playerList.forEach((player, idx) => {
        seatList[idx] = new Seat(idx + 1, player);
    });
}

function updateRoomSeatDOM() {
    
    roomSeats.innerHTML = "";

    seatList.forEach((seat) => {
        roomSeats.innerHTML += seat.render();
    });

}

initializeSeats(gameJson)
// updateRoomSeatDOM();

setTimeout(updateRoomSeatDOM, 2000);


function initializeRoomMessageLisenter(roomName) {
    // Start the STOMP communications, provide a callback for when the CONNECT frame arrives.
    client.connect({}, frame => {
        // Subscribe . Whenever a message arrives add the text in a list-item element in the unordered list.
        client.subscribe("/api/message/subscription/" + roomName, payload => {

            roomMessages.innerHTML = `<span class="d-block"> ${JSON.parse(payload.body).message} </span>` + roomMessages.innerHTML;

            console.log(payload.body);

        });

    });
}

// Take the value in the ‘message-input’ text field and send it to the server with empty headers.
function sendMessage(message, roomName){
    
    client.send('/api/message/new/' + roomName, {}, JSON.stringify({message: message}));

}

initializeRoomMessageLisenter("快乐");

setTimeout(()=>{
    sendMessage("a new player joined this room", "快乐");
}, 2000);
