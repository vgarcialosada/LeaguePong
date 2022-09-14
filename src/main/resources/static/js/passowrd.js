function password() {
    var x = document.getElementById("Input1");
    var y = document.getElementById("Input2")
    if (x.type === "password") {
      x.type = "text";
      y.type="text;"
    } else {
      x.type = "password";
      y.type="password";
    }
  }