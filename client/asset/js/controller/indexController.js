const roomJoinBtn = document.getElementById("roomJoinBtn");
const roomCreateBtn = document.getElementById("roomCreateBtn");
const roomForm = document.getElementById("roomForm");
const roomNameInput = roomForm.roomName;

function roomNameInputError(message) {
    roomNameInput.classList.add("is-invalid");
    swal({
        title: "错误",
        text: message,
        icon: "error",
    });
}

async function joinRoomRequest(roomName) {
    let response = await fetch(HOST + "/room/" + roomName, {
        method: 'GET', 
        mode: 'cors',
        headers: {
            Accept: 'application/json',
        }
    }).catch(error => {
        swal({
            title: "网络错误",
            text: "请检查设备网络或服务器已离线",
            icon: "error",
        });
        return;
    });

    const data = await response.json();

    // Failed
    if (!data.success) {
        roomNameInputError(data.message);
        return;
    }

    // If room is closed
    if (!data.data.open) {
        roomNameInputError("房间已被关闭");
        return;
    }

    // Success
    window.localStorage.setItem('roomName', data.data.roomName);
    window.location.replace("game.html");

}

async function createRoomRequest(roomName) {
    let response = await fetch(HOST + "/room/new", {
        method: 'POST', 
        mode: 'cors',
        headers: {
            "Accept": 'application/json',
            "Content-Type": 'application/json'
        },
        body: JSON.stringify({
            "roomName": roomName
        })
    }).catch(error => {
        swal({
            title: "网络错误",
            text: "请检查设备网络或服务器已离线",
            icon: "error",
        });
        return;
    });

    const data = await response.json();

    // Failed
    if (!data.success) {
        roomNameInputError(data.message);
        return;
    }

    // Success
    window.localStorage.setItem('roomName', data.data.roomName);
    window.location.replace("game.html");

}

roomJoinBtn.addEventListener("click", ()=>{
    if (!roomNameInput.value) {
        roomNameInputError("房间名称不能为空");
        return;
    }
    if (/\s/g.test(roomNameInput.value)) {
        roomNameInputError("房间名称不能包含空格");
        return;
    } 
    joinRoomRequest(roomNameInput.value);
});

roomCreateBtn.addEventListener("click", ()=>{
    if (!roomNameInput.value) {
        roomNameInputError("房间名称不能为空");
        return;
    }
    if (/\s/g.test(roomNameInput.value)) {
        roomNameInputError("房间名称不能包含空格");
        return;
    } 
    createRoomRequest(roomNameInput.value);
});


