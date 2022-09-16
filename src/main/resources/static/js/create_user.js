import axios from "axios";

function crear_usuario_data() {
  fetch("http://127.0.0.1:8080/register_user")
    .then((response) => response.json())
    .then((data) => console.log(data));
    //console.log(data[0]);
    //for( i = 0; i < data.length; i++){
      //console.log(data[i]);
       // return data[i];
    //} 
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
