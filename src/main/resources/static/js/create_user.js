
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
  }
 


  

//comprueba pwd iguales
function passCheck() {
  var password = document.getElementById('password');
  var vpassword = document.getElementById('confirm_password');

  if (password.value != vpassword.value) {
    document.getElementById("submitFormRegister").disabled = true;
    document.getElementById("failed_register_pwd").style.display="inline"
  }
  else {
    document.getElementById("submitFormRegister").disabled = false;
        document.getElementById("failed_register_pwd").style.display="none"

  }
}

