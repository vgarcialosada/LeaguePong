function mis_ligas(id) {
    fetch("http://127.0.0.1:8080/" + id + "/mis-ligas")
      .then((response) => response.json())
      .then((data) => console.log(data));
  }
  //console.log("hello");
  document.getElementById("my_leagues").innerHTML = mis_ligas(1);