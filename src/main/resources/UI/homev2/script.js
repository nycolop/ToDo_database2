let globalSelectedFilter = 'Todas';

function blockScroll() {
  document.body.style.overflow = "hidden";
  document.querySelector('html').style.overflow = "hidden";
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
}

function unlockScroll() {
  document.body.style.overflow = "visible";
  document.querySelector('html').style.overflow = "visible";
}

function dismountUpdateForm() {
  const parentElement = document.body;
  unlockScroll();

   if (parentElement.lastChild) {
    parentElement.removeChild(parentElement.lastChild);
   }
}

function loadUpdateForm(task) {
  blockScroll();
  const newChild = document.createElement("div");

  newChild.innerHTML = `
    <div id="update-container">
        <h2>Actualizar tarea</h2>

        <form class="task-form animate__animated animate__fadeIn">
          <label for="task-nombre">Nombre</label>
          <input type="text" id="task-nombre" name="task-nombre" required />

          <label for="task-start-date">Fecha de inicio</label>
          <input
            type="date"
            id="task-start-date"
            name="task-start-date"
            placeholder="YYYY-MM-DD HH:MM:SS"
            required
          />

          <label for="task-end-date">Fecha de fin estimada</label>
          <input
            type="date"
            id="task-end-date"
            name="task-end-date"
            placeholder="YYYY-MM-DD HH:MM:SS"
            required
           />

          <label for="task-description">Descripción</label>
          <textarea
              id="task-description"
              name="task-description"
              placeholder="Escribe la descripción aquí..."
              required
          ></textarea>

          <label for="task-priority">Prioridad</label>
          <select id="task-priority" name="task-priority">
            <option value="alta">Alta</option>
            <option value="media">Media</option>
            <option value="baja">Baja</option>
          </select>

          <label for="task-status">Estado</label>
          <select id="task-status" name="task-status">
            <option value="pendiente">Pendiente</option>
            <option value="en_progreso">En progreso</option>
            <option value="completado">Completado</option>
          </select>

          <button id="button-update-task" type="submit">Actualizar tarea</button>

          </form>
        <button id="close-update-form">Cerrar</button>
        </div>
    `;

  document.body.appendChild(newChild);

  document.getElementById('close-update-form').onclick = () => {
    dismountUpdateForm();
  };

  document.getElementById('button-update-task').onclick = (e) => {
    const name = document.getElementById('task-nombre').value;
    const startDate = document.getElementById('task-start-date').value;
    const estimatedEndDate = document.getElementById('task-end-date').value;
    const description = document.getElementById('task-description').value;
    const priority = document.getElementById('task-priority').value;
    const status = document.getElementById('task-status').value;


    app.updateTask(task.id, name, description, priority, status, startDate, estimatedEndDate);
  };
}

function loadUserTasks(selectedStatus = 'Todas') {
  const tasks = JSON.parse(app.getUserTasks());
  let htmlArticles = "";

  const filteredTasks = selectedStatus === 'Todas' ? tasks : tasks.filter(task => task.status.toLowerCase() === selectedStatus.toLowerCase());

  if (filteredTasks.length) {
    for (const task of filteredTasks) {
      htmlArticles += `
        <article id=${task.id} class="task animate__animated animate__fadeIn">
          <p>${task.name}</p>
          <p>Inicio: ${task.startDate}  Fin: ${task.estimatedEndDate}</p>
          <p>${task.description}</p>
          <p>${task.priority}</p>
          <p>${task.status}</p>

          <section>
            <button id="boton-borrar-tarea" class="button is-danger">Borrar tarea</button>
            <button id="boton-update-tarea" class="button is-primary">Actualizar</button>
          </section>
        </article>`;
    }
  } else {
    htmlArticles += '<h2 class="animate__animated animate__fadeIn">Sin tareas</h2>';
  }

  return htmlArticles;
}

function loadTaskFilter() {
    let filterHTML = "";

    filterContainer = `
      <div class="task-filter">
        <label for="task-status-filter">Filtrar por estado: </label>
        <select id="task-status-filter">
          <option value="Todas">Todos</option>
          <option value="Pendiente">Pendiente</option>
          <option value="En_Progreso">En progreso</option>
          <option value="Completado">Completado</option>
        </select>
      </div>
    `;

    return filterContainer;
}

function addTaskListeners() {
    const tasks = document.querySelectorAll(".task");

    for (const task of tasks) {
        const taskId = task.id;

        const deleteButton = task.querySelector("#boton-borrar-tarea");
        const updateButton = task.querySelector('#boton-update-tarea');

        if (updateButton) {
          updateButton.onclick = () => {
            loadUpdateForm(task);
          };
        }

        if (deleteButton) {
            deleteButton.onclick = () => {
              app.deleteTask(taskId);

              document.getElementById('tasks-container').innerHTML = loadUserTasks(globalSelectedFilter);
              addTaskListeners();
            };
        }
    }

}

function loadHome() {
    const mainContainer = document.querySelector("#main-container");
    mainContainer.innerHTML = `
      <div id="task-filter-container"></div>
      <div id="tasks-container"></div>
    `;

    document.getElementById('tasks-container').innerHTML = loadUserTasks(globalSelectedFilter);
    document.getElementById('task-filter-container').innerHTML = loadTaskFilter();

    document.getElementById("task-status-filter").addEventListener("change", function() {
       globalSelectedFilter = this.value;
       document.getElementById('tasks-container').innerHTML = loadUserTasks(globalSelectedFilter);
       addTaskListeners();
    });

    addTaskListeners();
}

function loadCreateForm() {
  const mainContainer = document.querySelector("#main-container");

  mainContainer.innerHTML = `
      <h2>Cree una nueva tarea:</h2>

      <form class="task-form animate__animated animate__fadeIn">
        <label for="task-nombre">Nombre</label>
        <input type="text" id="task-nombre" name="task-nombre" required />

        <label for="task-start-date">Fecha de inicio</label>
        <input
          type="date"
          id="task-start-date"
          name="task-start-date"
          placeholder="YYYY-MM-DD HH:MM:SS"
          required
        />

        <label for="task-end-date">Fecha de fin estimada</label>
        <input
          type="date"
          id="task-end-date"
          name="task-end-date"
          placeholder="YYYY-MM-DD HH:MM:SS"
          required
         />

        <label for="task-description">Descripción</label>
        <textarea
            id="task-description"
            name="task-description"
            placeholder="Escribe la descripción aquí..."
            required
        ></textarea>

        <label for="task-priority">Prioridad</label>
        <select id="task-priority" name="task-priority">
          <option value="alta">Alta</option>
          <option value="media">Media</option>
          <option value="baja">Baja</option>
        </select>

        <label for="task-status">Estado</label>
        <select id="task-status" name="task-status">
          <option value="pendiente">Pendiente</option>
          <option value="en_progreso">En progreso</option>
          <option value="completado">Completado</option>
        </select>

        <button type="submit">Guardar tarea</button>
      </form>
  `;
}

function start() {
  const buttonCreateTask = document.getElementById("button-create-task");
  const buttonViewTasks = document.getElementById("button-view-tasks");
  const loggedUser = JSON.parse(app.getLoggedUser());

  buttonViewTasks.style.backgroundColor = "#3273dc";
  buttonViewTasks.querySelector("a").style.color = "white";
  loadHome();

  const buttonLogout = document.querySelector("#button-logout");
  buttonLogout.onclick = () => {
    app.logout();
  }

  const userNameElement = document.getElementById("display-user-name");
  userNameElement.innerText = loggedUser.name + " " + loggedUser.lastName;

  buttonCreateTask.onclick = () => {
     buttonCreateTask.style.backgroundColor = "#3273dc";
     buttonCreateTask.querySelector("a").style.color = "white";

     buttonViewTasks.style.backgroundColor = "transparent";
     buttonViewTasks.querySelector("a").style.color = "#313030";

     loadCreateForm();

     const form = document.querySelector("form");
     form.onsubmit = () => {
      const name = document.getElementById("task-nombre").value;
      const description = document.getElementById("task-description").value;
      const priority = document.getElementById("task-priority").value;
      const status = document.getElementById("task-status").value;
      const startDate = document.getElementById("task-start-date").value;
      const estimatedEndDate = document.getElementById("task-end-date").value;

      app.createTask(0, name, description, priority, status, startDate, estimatedEndDate);
     }
  }

  buttonViewTasks.onclick = () => {
     buttonViewTasks.style.backgroundColor = "#3273dc";
     buttonViewTasks.querySelector("a").style.color = "white";

     buttonCreateTask.style.backgroundColor = "transparent";
     buttonCreateTask.querySelector("a").style.color = "#313030";

     loadHome();
  }
}

document.addEventListener("DOMContentLoaded", function () {
  document.addEventListener("appReady", function () {
    try {
      start();
    } catch(e) {
      alert("Error en start: " + e);
    }
  });
});
