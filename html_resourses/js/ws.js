var ws;
init = function () {
    ws = new WebSocket("ws://localhost:8060/dbwebsocket");
    ws.onopen = function (event) {

    }

    ws.onmessage = function (event) {
       alert("Hello")
    }

    ws.onclose = function (event) {

    }
};
function sendMessage() {
    var name = document.getElementById("name");
    ws.send(name);
};