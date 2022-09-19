
function create_user() {
    res = fetch("http://127.0.0.1:8080/register_user", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre_usuario: nombre_usuario.value, 
        password: password.value,
        mail: mail.value,
        localidad: localidad.value,
        nivel: nivel.value,
      }),

      
    }).then((response) => response.text);
   // console.log(res);
   // fetch("http://127.0.0.1:8080/" + id + "/mis-ligas")
    //.then((response) => response.json())
    //.then((data) => console.log(data));
        window.location.href = 'http://127.0.0.1:5500/src/main/resources/static/html/index.html'

  }
 


  


