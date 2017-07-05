var text = document.getElementById("paradas").value;
listaParada = JSON.parse(text);
var map = L.map('map_id').setView([listaParada[0].position.lat,listaParada[0].position.lng], 15);
console.log(listaParada[0].position.lat)
for(i= 0; i<listaParada.length;i++){
	L.marker([listaParada[i].position.lat,listaParada[i].position.lng]).addTo(map).bindPopup(listaParada[i].name);
	console.log(listaParada[i].position.lat)
}

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1IjoiY2FybG9zcXVpeGFkYSIsImEiOiJjajRvcmlra3IwZDRqMzNwbXlucmtwdTJvIn0.gZjmAuotBrqTtBpvOuQivg'
}).addTo(map);