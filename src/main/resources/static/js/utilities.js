
//comprobar contraseñas
function comprobarContraseñas(ev) {
    var passValue = document.getElementById("password").value
    var confpassValue = document.getElementById("confirm_password").value
   if(passValue !== confpassValue) {
       window.alert("Las contraseñas no coinciden")
    }
}