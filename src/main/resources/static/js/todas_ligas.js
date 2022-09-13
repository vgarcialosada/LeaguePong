//import axios from "axios";
function hello() {
    return "hello";
  }
  function todas_ligas() {
    fetch("http://127.0.0.1:8080/todas-ligas")
      .then((response) => response.json())
      .then((data) => console.log(data));
  }
  //console.log("hello");
  document.getElementById("todas").innerHTML = todas_ligas();