import axios from "axios";

function create_user() {
    res = fetch("http://127.0.0.1:8080/" + id + "/crear-liga", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre_usuario: nombre_usuario.value,
        password: password.value,
        mail: reglas_de_liga.value,
        localidad: localidad.value,
        nivel: nivel.value,
      }),
    });
 
    console.log(res.data);
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

function returnToPreviousPage() {
    window.history.back();
}
