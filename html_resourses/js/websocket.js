var ws;
init = function () {
    ws = new WebSocket("ws://localhost:8060/dbwebsocket");
    ws.onopen = function (event) {
    };

    ws.onmessage = function (event) {
        document.getElementById("newUser").innerHTML = event.data
    }
    ;

    ws.onclose = function (event) {
    }
};

function sendMessage() {
    var htmlName = document.getElementById("name").value;
    var htmlAge = document.getElementById("age").value;
    var myJSON = {
        name: htmlName,
        age: htmlAge
    };
    ws.send(JSON.stringify(myJSON));
}