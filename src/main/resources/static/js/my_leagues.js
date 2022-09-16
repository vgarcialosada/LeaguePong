// function mis_ligas(id) {
//     fetch("http://127.0.0.1:8080/" + id + "/mis-ligas")
//       .then((response) => response.json())
//       ;
//   }
//   //console.log("hello");
//   document.getElementById("my_leagues").innerHTML = mis_ligas(1);
  const mis_ligas = async(id) => (await fetch("http://127.0.0.1:8080/" + id + "/mis-ligas")).json();

  mis_ligas(1)
  .then((data) => console.log(data))
  .then(data => {
    for (i = 0; i < data.length; i++){
      console.log(data[i].nombre);
    };
  });
    

    
  