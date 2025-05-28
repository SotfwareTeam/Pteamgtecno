<template>
    <div class="xd">
      <body>
        <nav>
            <NavbarAdmin />
        </nav>
        <div class="container mt-4">
          <!-- Tabla de Alumnos -->
          <h2 class="text-center mb-4">Gestión de Alumnos</h2>
          <div class="table-responsive">
            <table class="table table-striped table-bordered">
              <thead class="thead-dark">
                <tr>
                  <th>Nombre</th>
                  <th>Nota</th>
                  <th>Profesor</th>
                  <th>Clase</th>
                  <th>Estado</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(alumno, index) in alumnos" :key="index">
                  <td>{{ alumno.nombre }}</td>
                  <td>{{ alumno.nota }}</td>
                  <td>{{ alumno.profesor }}</td>
                  <td>{{ alumno.clase }}</td>
                  <td>{{ alumno.status }}</td>
                  <td>
                    <button class="btn btn-primary btn-sm mr-2" @click="abrirModalModificar(index)">Modificar</button>
                    <button class="btn btn-danger btn-sm mr-2" @click="eliminarAlumno(index)">Eliminar</button>
                    <button class="btn btn-info btn-sm" @click="visualizarAlumno(index)">Visualizar</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <!-- Modal Modificar Alumno -->
          <div class="modal fade" id="modalModificar" tabindex="-1" role="dialog" aria-labelledby="modalModificarLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="modalModificarLabel">Modificar Alumno</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="form-group">
                      <label for="nombre">Nombre</label>
                      <input type="text" class="form-control" id="nombre" v-model="formulario.nombre" />
                    </div>
                    <div class="form-group">
                      <label for="nota">Nota</label>
                      <input type="text" class="form-control" id="nota" v-model="formulario.nota" />
                    </div>
                    <div class="form-group">
                      <label for="profesor">Profesor</label>
                      <input type="text" class="form-control" id="profesor" v-model="formulario.profesor" />
                    </div>
                    <div class="form-group">
                      <label for="clase">Clase</label>
                      <input type="text" class="form-control" id="clase" v-model="formulario.clase" />
                    </div>
                    <div class="form-group">
                      <label for="status">Estado</label>
                      <input type="text" class="form-control" id="status" v-model="formulario.status" />
                    </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                  <button type="button" class="btn btn-primary" @click="guardarCambios">Guardar cambios</button>
                </div>
              </div>
            </div>
          </div>

          <!-- Módulo de Calendario -->
          <h2 class="text-center mt-5">Calendario de Eventos</h2>
          <div class="card shadow-lg mt-4">
            <div class="card-body">
              <full-calendar
                :plugins="[dayGridPlugin, timeGridPlugin]"
                :events="eventos"
                :editable="true"
                headerToolbar="{
                  left: 'prev,next today',
                  center: 'title',
                  right: 'dayGridMonth,timeGridWeek,timeGridDay'
                }"
                @dateClick="abrirModalEvento"
              />
            </div>
          </div>

          <!-- Modal Evento -->
          <div class="col-md-6 mb-4">
              <div id="carouselExampleIndicators" class="carousel slide box-carrusel" data-ride="carousel">
                  <!-- Slides -->
                  <div class="carousel-inner">
                      <div class="carousel-item active">
                          <div class="flip-card" onclick="flipCard(this)">
                              <div class="flip-card-inner">
                                  <div class="flip-card-front">
                                      <img src="../../../Public/Assets/img/icons/Evento1.jpeg" id="eventImage1"
                                          class=" py-1 d-block w-100 img-fluid height-400" alt="Primera Imagen">
                                  </div>
                                  <div class="flip-card-back event1">
                                      <h5 id="eventTitle1">Título del Evento</h5>
                                      <p id="eventAddress1">Dirección: Calle Falsa 123</p>
                                      <p id="eventTime1">Hora: 18:00</p>
                                      <p id="eventHost1">Anfitrión: John Doe</p>
                                      <p id="eventDescription1">Descripción: Esta es la descripción del evento 1.</p>
                                      <button class="btn btn-light btn-sm ModificationEventsBtn"
                                          onclick="openModal('event1')">Modificar</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="carousel-item">
                          <div class="flip-card" onclick="flipCard(this)">
                              <div class="flip-card-inner">
                                  <div class="flip-card-front">
                                      <img src="../../../Public/Assets/img/icons/Evento2.jpeg" id="eventImage2"
                                          class="d-block w-100 img-fluid" alt="Segunda Imagen">
                                  </div>
                                  <div class="flip-card-back event2">
                                      <h5 id="eventTitle2">Título del Evento</h5>
                                      <p id="eventAddress2">Dirección: Calle Verdadera 456</p>
                                      <p id="eventTime2">Hora: 20:00</p>
                                      <p id="eventHost2">Anfitrión: Jane Smith</p>
                                      <p id="eventDescription2">Descripción: Esta es la descripción del evento 2.</p>
                                      <button class="btn btn-light btn-sm ModificationEventsBtn"
                                          onclick="openModal('event2')">Modificar</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="carousel-item">
                          <div class="flip-card" onclick="flipCard(this)">
                              <div class="flip-card-inner">
                                  <div class="flip-card-front">
                                      <img src="../../../Public/Assets/img/icons/Evento3.jpeg" id="eventImage3"
                                          class="d-block w-100 img-fluid" alt="Tercera Imagen">
                                  </div>
                                  <div class="flip-card-back event3">
                                      <h5 id="eventTitle3">Título del Evento</h5>
                                      <p id="eventAddress3">Dirección: Avenida Ejemplo 789</p>
                                      <p id="eventTime3">Hora: 19:30</p>
                                      <p id="eventHost3">Anfitrión: Alice Johnson</p>
                                      <p id="eventDescription3">Descripción: Esta es la descripción del evento 3.</p>
                                      <button class="btn btn-light btn-sm ModificationEventsBtn"
                                          onclick="openModal('event3')">Modificar</button>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
        </div>
        <FooterAdmin />
      </body>
  </div>
  </template>

  <script setup>
  import { ref } from 'vue';
  //import FullCalendar from '@fullcalendar/vue3';
  //import dayGridPlugin from '@fullcalendar/daygrid';
  //import timeGridPlugin from '@fullcalendar/timegrid';

import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';



  const alumnos = ref([
    { nombre: 'Juan Pérez', nota: '8', profesor: 'Mr. Smith', clase: 'Matemáticas', status: 'Activo' },
    { nombre: 'Ana García', nota: '9', profesor: 'Mrs. Johnson', clase: 'Ciencias', status: 'Activo' },
  ]);

  const formulario = ref({ nombre: '', nota: '', profesor: '', clase: '', status: '' });
  const filaSeleccionada = ref(null);

  const abrirModalModificar = (index) => {
    filaSeleccionada.value = index;
    Object.assign(formulario.value, alumnos.value[index]);
    ('#modalModificar').modal('show');
  };

  const guardarCambios = () => {
    Object.assign(alumnos.value[filaSeleccionada.value], formulario.value);
    ('#modalModificar').modal('hide');
  };

  const eliminarAlumno = (index) => {
    alumnos.value.splice(index, 1);
  };

  const visualizarAlumno = (index) => {
    alert(JSON.stringify(alumnos.value[index], null, 2));
  };

  const eventos = ref([
    { title: 'Clase de Danza', start: '2024-12-06' },
  ]);

  const nuevoEvento = ref({ titulo: '', fecha: '' });
  const abrirModalEvento = (info) => {
    nuevoEvento.value.fecha = info.dateStr;
    ('#modalEvento').modal('show');
  };

  const agregarEvento = () => {
    eventos.value.push({ title: nuevoEvento.value.titulo, start: nuevoEvento.value.fecha });
    ('#modalEvento').modal('hide');
  };
  </script>

<style scoped>
body {
  display: flex;
  flex-direction: column;
  background: repeating-linear-gradient(#5f2f9a, #076a81);
  overflow: hidden;
}

.container {
  position: relative;
  height: 1000px;
}
.card {
  margin: 0 auto;
}
</style>

