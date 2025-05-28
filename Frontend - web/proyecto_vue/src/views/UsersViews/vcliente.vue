<template>
  <body>

      <NavbarStudents />

    <div class="container mt-4">
      <div class="row">
        <!-- Sección de calificación de estudiantes -->
        <div class="col-md-4">
          <div class="card mb-4">
            <div class="card-header bgcc">
              <h5 class="font-weight-bold">Calificación de Estudiantes</h5>
            </div>
            <div class="card-body">
              <p><strong>Estudiante:</strong> <span id="nombre-estudiante">Miguel Angel</span></p>
              <p><strong>Clase a Calificar:</strong> <span id="clase-estudiante">Salsa</span></p>
              <p><strong>Calificación:</strong> <span id="calificacion-estudiante">4.0</span></p>
              <p><strong>Observaciones a Mejorar:</strong></p>
              <p id="observaciones-estudiante">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
              </p>

              <!-- Sección de estrellas para la calificación -->
              <p><strong>Calificación de la clase:</strong></p>
              <div id="estrellas-calificacion" class="text-warning">
                ★★★☆☆
              </div>
            </div>
          </div>

          <!-- Sección de novedades -->
          <div class="card">
            <div class="card-header bgcc">
              <h5 class="font-weight-bold">Novedades del sistema</h5>
            </div>
            <div class="card-body">
              <ul>
                <li>Hay un nuevo evento</li>
                <li>Se registró un alumno en tu clase de Salsa</li>
                <li>Tu clase tiene el mejor promedio de la academia</li>
              </ul>
            </div>
          </div>
        </div>

        <!-- Sección de lista de estudiantes -->
        <div class="col-md-8">
          <table class="table table-bordered">
            <thead class="thead-dark">
              <tr>
                <th>Nombre</th>
                <th>Años</th>
                <th>Clase</th>
                <th>Asistencia</th>
              </tr>
            </thead>
            <tbody id="lista-estudiantes">
              <tr
                v-for="(estudiante, index) in estudiantes"
                :key="index"
                :data-nombre="estudiante.nombre"
                :data-clase="estudiante.clase"
                :data-calificacion="estudiante.calificacion"
                :data-observaciones="estudiante.observaciones"
              >
                <td>{{ estudiante.nombre }}</td>
                <td>{{ estudiante.edad }}</td>
                <td>{{ estudiante.clase }}</td>
                <td><button class="btn btn-secondary" disabled>✓</button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <FooterStudents />
  </body>
</template>

<script>
import NavbarStudents from '@/components/navbars/NavbarStudents.vue';
import FooterStudents from '@/components/footers/FooterStudents.vue';
export default {
  components: {
    NavbarStudents,
      FooterStudents,
  },

  name: "VistaProfesor",
  data() {
    return {
      estudiantes: [
        {
          nombre: "Pedro",
          edad: 38,
          clase: "Merengue",
          calificacion: 3.5,
          observaciones: "Debe mejorar su técnica en pasos básicos.",
        },
        {
          nombre: "Pablo",
          edad: 40,
          clase: "Bachata",
          calificacion: 4.8,
          observaciones: "Excelente ritmo y conexión con la música.",
        },
        // Más estudiantes pueden agregarse aquí
      ],
    };
  },
};
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: "Montserrat", sans-serif;
}

body {
    display: flex;
    flex-direction: column;
    background: repeating-linear-gradient(#5f2f9a, #076a81);
    height: 1500px;
}

.footer {
  position: absolute;
  bottom: -100px;
}

.card {
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

thead.thead-dark th {
  background-color: #8550c7;
  color: #000;
}

.bgcc {
  background-color: #8550c7;
  color: #000;
}

.container.mt-4 {
  margin-bottom: 5%;
}
</style>





esta vista la tengo perdida ya no se de que es sorry
