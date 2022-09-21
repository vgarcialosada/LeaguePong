
//comprobar contraseñas
//comprueba pwd iguales
function passCheck() {
  var password = document.getElementById('password');
  var vpassword = document.getElementById('confirm_password');

  if (password.value != vpassword.value|| 
  vpassword.value ==null ||
   password.value==null) {
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

function addSound(){
  var bns = document.getElementsByTagName("button");
 for (i = 0; i < bns.length; i++) {
    bns[i].addEventListener("click", function() {
    var sound = document.getElementById("audio");
  sound.play(); });
  }}
  
  addSound();

