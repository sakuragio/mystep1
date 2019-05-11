<%--
  Created by IntelliJ IDEA.
  User: wangteng
  Date: 2019/5/11
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Use WebSocket</title>
</head>
<body>
    <h1>Echo Server</h1>
    <h5>${flag}</h5>
    <div>
        <form>
            <input type="button" value="Press to send" onclick="send1()" />
            <input type="button" value="Press to send" onclick="send2()" />
            <input type="text" name="message" id="textID" value="Hello WebSocket" />
        </form>
    </div>
    <div id="output">

    </div>

<script>
    var echo_websocket;
    var output = document.getElementById("output");
    var textID = document.getElementById("textID");
    var wsUri = "ws://127.0.0.1:8080/mystep1/echo";
    var wsUri_p = "ws://127.0.0.1:8080/mystep1/echo2";

    function send1() {
        send_echo(wsUri);
    }
    function send2() {
        send_echo(wsUri_p);
    }

    function send_echo(wsUri) {
        writeToScreen("Connect to " + wsUri);
        echo_websocket = new WebSocket(wsUri);
        echo_websocket.onopen = function(e) {
            writeToScreen("Connected!");
            doSend(textID.value);
        }
        echo_websocket.onmessage = function(e) {
            writeToScreen("Received message: " + e.data);
            echo_websocket.close();
        }
        echo_websocket.onerror = function(e) {
            writeToScreen("<span style='color: red;'>ERROR: </span>" + e.data);
            echo_websocket.close();
        }
    }

    function doSend(message) {
        echo_websocket.send(message);
        writeToScreen("Sent message: " + message);
    }

    function writeToScreen(message) {
        var p = document.createElement("p");
        p.style.wordWrap = "break-word";
        p.innerHTML = message;
        output.appendChild(p);
    }

</script>
</body>
</html>
