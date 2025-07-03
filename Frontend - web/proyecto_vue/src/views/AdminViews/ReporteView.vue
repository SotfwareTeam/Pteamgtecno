<template>
  <div>
  <NavbarAdmin />
  <div class="container mt-5 pt-5">
    <!-- Tarjetas Resumen -->
    <div class="row mb-4">
      <div class="col-lg-6 col-md-6">
        <div class="card text-white mb-3">
          <div class="card-body">
            <div class="card-title text-black">Total Usuarios</div>
            <div class="card-text text-black">120</div>
          </div>
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <div class="card text-white mb-3">
          <div class="card-body">
            <div class="card-title text-black">Clases Activas</div>
            <div class="card-text text-black">8</div>
          </div>
        </div>
      </div>
    </div>

    <!-- Acordeón -->
    <div class="row">
      <div class="col-lg-4">
        <div class="accordion" id="accordionExample">
          <!-- Clases -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
              <button class="accordion-button" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                Clases
              </button>
            </h2>
            <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <ul class="list-unstyled">
                  <li><a href="#" @click.prevent="showClassReport('Clase 1')">Clase 1</a></li>
                  <li><a href="#" @click.prevent="showClassReport('Clase 2')">Clase 2</a></li>
                  <li><a href="#" @click.prevent="showClassReport('Clase 3')">Clase 3</a></li>
                </ul>
              </div>
            </div>
          </div>
          <!-- Profesores -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                Profesores
              </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <ul class="list-unstyled">
                  <li><a href="#" @click.prevent="showTeacherReport('Profesor 1')">Profesor 1</a></li>
                  <li><a href="#" @click.prevent="showTeacherReport('Profesor 2')">Profesor 2</a></li>
                  <li><a href="#" @click.prevent="showTeacherReport('Profesor 3')">Profesor 3</a></li>
                </ul>
              </div>
            </div>
          </div>
          <!-- Estudiantes -->
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                Estudiantes
              </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <ul class="list-unstyled">
                  <li><a href="#" @click.prevent="showStudentReport('Estudiante 1')">Estudiante 1</a></li>
                  <li><a href="#" @click.prevent="showStudentReport('Estudiante 2')">Estudiante 2</a></li>
                  <li><a href="#" @click.prevent="showStudentReport('Estudiante 3')">Estudiante 3</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Contenido del Reporte -->
      <div class="col-lg-8">
        <div class="card">
          <div class="card-header">
            <h2>Reporte Seleccionado</h2>
          </div>
          <div class="card-body" id="reportContent">
            <div v-html="reporteSeleccionado"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- Tarjetas adicionales -->
    <div class="row mt-4">
      <div class="col-lg-4 col-md-6">
        <div class="card text-white mb-3">
          <div class="card-body">
            <div class="card-title text-black">Inscripciones Mensuales</div>
            <div class="card-text text-black">250</div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6">
        <div class="card text-white mb-3">
          <div class="card-body">
            <div class="card-title text-black">Satisfacción de Estudiantes</div>
            <div class="card-text text-black">85%</div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6">
        <div class="card text-white mb-3">
          <div class="card-body">
            <div class="card-title text-black">Clases Completadas</div>
            <div class="card-text text-black">40</div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <FooterAdmin />
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import Chart from 'chart.js/auto'
import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';

const reporteSeleccionado = ref('<p>Seleccione un elemento del acordeón para ver el reporte.</p>')

function showClassReport(nombre) {
  reporteSeleccionado.value = `
    <h4>${nombre}</h4>
    <canvas id="classChart" width="400" height="200"></canvas>
    <table class="table table-bordered mt-3">
      <thead>
        <tr><th>Fecha</th><th>Asistencia</th><th>Comentarios</th></tr>
      </thead>
      <tbody>
        <tr><td>2024-09-01</td><td>30</td><td>Excelente</td></tr>
        <tr><td>2024-09-08</td><td>28</td><td>Buena</td></tr>
      </tbody>
    </table>
  `
  nextTick(() => {
    const ctx = document.getElementById('classChart').getContext('2d')
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Semana 1', 'Semana 2', 'Semana 3', 'Semana 4'],
        datasets: [{
          label: 'Asistentes',
          data: [30, 28, 32, 25],
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: { beginAtZero: true }
        }
      }
    })
  })
}

function showTeacherReport(nombre) {
  reporteSeleccionado.value = `
    <h4>${nombre}</h4>
    <p><strong>Fecha:</strong> 2024-09-03</p>
    <p>Este es el reporte relacionado a ${nombre}.</p>
  `
}

function showStudentReport(nombre) {
  reporteSeleccionado.value = `
    <h4>${nombre}</h4>
    <p><strong>Fecha:</strong> 2024-09-10</p>
    <p>Este es el reporte relacionado a ${nombre}.</p>
  `
}
</script>

<style scoped>
.card-title {
  font-size: 1.25rem;
  font-weight: bold;
}
.card-text {
  font-size: 1.5rem;
}
.accordion-button {
  font-size: 1.2rem;
}
#reportContent {
  min-height: 300px;
}
</style>

<!-- CDN requeridos en tu index.html -->
<!--
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
-->
