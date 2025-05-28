<template>
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
    <div class="modal fade" id="modalEvento" tabindex="-1" role="dialog" aria-labelledby="modalEventoLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalEventoLabel">Nuevo Evento</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="eventoTitulo">Título del Evento</label>
                <input type="text" class="form-control" id="eventoTitulo" v-model="nuevoEvento.titulo" />
              </div>
              <div class="form-group">
                <label for="eventoFecha">Fecha</label>
                <input type="date" class="form-control" id="eventoFecha" v-model="nuevoEvento.fecha" />
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
            <button type="button" class="btn btn-primary" @click="agregarEvento">Guardar Evento</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';

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
.container {
  max-width: 1200px;
}
.card {
  margin: 0 auto;
}
</style>
