function start() {
  document.getElementById('ingresar').onclick = () => {
    app.loadLoginView();
  };

  document.getElementById("registro").onclick = function (e) {
      e.preventDefault();

      const nombre = document.getElementById("input-nombre").value;
      const apellido = document.getElementById("input-apellido").value;
      const contrasena = document.getElementById("input-password").value;
      const validationText = document.getElementById('validation');

      const userId = app.registerUser(nombre, apellido, contrasena);

      if (userId > 0) {
          app.loadLoginView();
      } else {
          validationText.style.display = "block";
      }
  };
}

document.addEventListener("DOMContentLoaded", function () {
  document.addEventListener("appReady", function () {
    try {
      start();
    } catch(e) {
      console.log("Error en start: " + e);
    }
  });
});
