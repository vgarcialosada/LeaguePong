
//comprobar contraseñas
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
function btnRegistrar(){
  document.getElementById("submitFormRegister").disabled = false;
}
btnRegistrar()

function returnToPreviousPage() {
    window.history.back();
}

