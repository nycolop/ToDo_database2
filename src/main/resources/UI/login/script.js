function start() {
  const validationText = document.getElementById("validation");
  validationText.style.display = "none";

  document.getElementById("ingresar").onclick = function (e) {
    e.preventDefault();

    const nick = document.getElementById("nick-name").value;
    const password = document.getElementById("input-password").value;

    const user = JSON.parse(app.login(nick, password));

    if (user) {
      app.loadHomeView();
    } else {
      validationText.style.display = "block";
    }
  };

  document.getElementById('registro').onclick = () => {
     app.loadRegisterView();
  }
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
