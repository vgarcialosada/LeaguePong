
//comprobar contraseñas
function ansValidation(ev) {
    var passValue = document.getElementById("password").value
    var confpassValue = document.getElementById("confirm_password").value
   if(passValue !== confpassValue) {
       window.alert("Passwords do not match!")
    }
}