<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h4>Game status: <label id="gameStatus">${game.status}</label></h4>
	
	<h4>Turn: <label id="turn">${game.turn}</label></h4>
	<h4></h4>
	<h4>Winner: <label id="gameRes"> ${game.gameResult}</label></h4>
	<h4></h4>

	<table border="1" style="width: 80%; align: center">
		<tbody>
			<tr align="center">
				<td rowspan="3" style="width: 60px"><div id="pit6"
						class="button1">${game.board.player1BigPit.stoneCount}</div></td>

				<td><button id="pit5" type='button' class="button1"
						onclick='play(${game.id}, 5)'>${game.board.pits[5].stoneCount}</button></td>
				<td><button id="pit4" type='button' class="button1"
						onclick='play(${game.id}, 4)'>${game.board.pits[4].stoneCount}</button></td>
				<td><button id="pit3" type='button' class="button1"
						onclick='play(${game.id}, 3)'>${game.board.pits[3].stoneCount}</button></td>
				<td><button id="pit2" type='button' class="button1"
						onclick='play(${game.id}, 2)'>${game.board.pits[2].stoneCount}</button></td>
				<td><button id="pit1" type='button' class="button1"
						onclick='play(${game.id}, 1)'>${game.board.pits[1].stoneCount}</button></td>
				<td><button id="pit0" type='button' class="button1"
						onclick='play(${game.id}, 0)'>${game.board.pits[0].stoneCount}</button></td>

				<td rowspan="3" style="width: 60px"><div id="pit13"
						class="button2">${game.board.player2BigPit.stoneCount}</div></td>
			</tr>
			<tr align="center">
				<td colspan="6" style="height: 50px;"></td>
			</tr>
			<tr align="center">
				<td><button id="pit7" type='button' class="button2"
						onclick='play(${game.id}, 7)'>${game.board.pits[7].stoneCount}</button></td>
				<td><button id="pit8" type='button' class="button2"
						onclick='play(${game.id}, 8)'>${game.board.pits[8].stoneCount}</button></td>
				<td><button id="pit9" type='button' class="button2"
						onclick='play(${game.id}, 9)'>${game.board.pits[9].stoneCount}</button></td>
				<td><button id="pit10" type='button' class="button2"
						onclick='play(${game.id}, 10)'>${game.board.pits[10].stoneCount}</button></td>
				<td><button id="pit11" type='button' class="button2"
						onclick='play(${game.id}, 11)'>${game.board.pits[11].stoneCount}</button></td>
				<td><button id="pit12" type='button' class="button2"
						onclick='play(${game.id}, 12)'>${game.board.pits[12].stoneCount}</button></td>
			</tr>
		</tbody>
	</table>
	
	<h3 style="color: maroon" id="message"></h3>
</div>

<script>
var updatePit = function(pitDomId, newVal){
	document.getElementById(pitDomId).innerHTML = newVal;
}

var success = function(data){
    var obj = JSON.parse(data);
    //console.log(obj);
    
    if(obj["status"] == "OK") {
	    var i;
		for (i = 0; i < obj["game"]["board"]["pitCount"]; i++) { 
			updatePit("pit"+i, obj["game"]["board"]["pits"][i]["stoneCount"]);
		}
		
		document.getElementById("turn").innerHTML = obj["game"]["turn"];
		document.getElementById("gameRes").innerHTML = obj["game"]["gameResult"];
		document.getElementById("gameStatus").innerHTML = obj["game"]["status"];
    } else{
    	document.getElementById("message").innerHTML = obj["message"];
    }
}

var play = function(gameId, pitId) {
	document.getElementById("message").innerHTML = "";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	    success(this.responseText);
	  }
	};
	xhttp.open("PUT", "/games/" + gameId + "/pits/" + pitId, true);
	xhttp.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
	xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhttp.send();
}


</script>

<%@ include file="common/footer.jspf"%>