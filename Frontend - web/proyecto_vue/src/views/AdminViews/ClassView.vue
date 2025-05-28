<template>
    <NavbarAdmin />
    <br><br>
    <div class="container mt-5 pt-5">
      <div class="row">
        <!-- Calificación de Estudiantes -->
        <div class="col-md-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Calificación de Estudiantes</h5>
              <p>Estudiante: <span id="student-name">Miguel Ángel</span></p>
              <p>Clase a Calificar: <span id="class-name">Salsa</span></p>
              <p>Calificación: <span id="student-grade">4.0</span></p>
              <p>Observación a Mejorar: <span id="student-obs">Lorem ipsum dolor sit amet...</span></p>
              <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registerModal">Registrar</button>
              <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">Editar</button>
            </div>
          </div>
        </div>
  
        <!-- Novedades del Sistema -->
        <div class="col-md-6">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Novedades del sistema</h5>
              <p>Hay un nuevo Evento</p>
              <p>Se registró un alumno en tu clase de Salsa</p>
              <p>Tu clase tiene el mejor promedio de la academia</p>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Tabla de Asistencia -->
      <div class="row mt-4">
        <div class="col-md-12">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Años</th>
                <th>Clase</th>
                <th>Asistencia</th>
              </tr>
            </thead>
            <tbody id="student-table">
              <tr>
                <td>Juan</td>
                <td>50</td>
                <td>Merengue</td>
                <td><input type="checkbox" class="attendance-toggle"></td>
              </tr>
              <tr>
                <td>Pedro</td>
                <td>38</td>
                <td>Merengue</td>
                <td><input type="checkbox" class="attendance-toggle"></td>
              </tr>
              <tr>
                <td>Pedro</td>
                <td>38</td>
                <td>Merengue</td>
                <td><input type="checkbox" class="attendance-toggle"></td>
              </tr>
              <tr>
                <td>Pedro</td>
                <td>38</td>
                <td>Merengue</td>
                <td><input type="checkbox" class="attendance-toggle"></td>
              </tr>
              <tr>
                <td>Pedro</td>
                <td>38</td>
                <td>Merengue</td>
                <td><input type="checkbox" class="attendance-toggle"></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  
    <!-- Modal para Registrar -->
    <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registerModalLabel">Registrar Calificación</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form id="registerForm">
              <div class="mb-3">
                <label for="studentNameInput" class="form-label">Nombre del Estudiante</label>
                <input type="text" class="form-control" id="studentNameInput">
              </div>
              <div class="mb-3">
                <label for="classNameInput" class="form-label">Clase</label>
                <input type="text" class="form-control" id="classNameInput">
              </div>
              <div class="mb-3">
                <label for="gradeInput" class="form-label">Calificación</label>
                <input type="number" class="form-control" id="gradeInput" step="0.1">
              </div>
              <div class="mb-3">
                <label for="obsInput" class="form-label">Observación</label>
                <textarea class="form-control" id="obsInput"></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Registrar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  
    <!-- Modal para Editar -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editModalLabel">Editar Calificación</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form id="editForm">
              <div class="mb-3">
                <label for="editStudentName" class="form-label">Nombre del Estudiante</label>
                <input type="text" class="form-control" id="editStudentName" value="Miguel Ángel">
              </div>
              <div class="mb-3">
                <label for="editClassName" class="form-label">Clase</label>
                <input type="text" class="form-control" id="editClassName" value="Salsa">
              </div>
              <div class="mb-3">
                <label for="editGrade" class="form-label">Calificación</label>
                <input type="number" class="form-control" id="editGrade" value="4.0" step="0.1">
              </div>
              <div class="mb-3">
                <label for="editObs" class="form-label">Observación</label>
                <textarea class="form-control" id="editObs">Lorem ipsum dolor sit amet...</textarea>
              </div>
              <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <br><br>
    <FooterAdmin />
  </template>
  <script>
import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';

  export default {    
    name: 'HomeAdmin',
    components: {
        NavbarAdmin,
      FooterAdmin,
  },
    name: 'NotificacionView',
    mounted() {
  
    },
    data() {
      return {
  
      }
    }
  }
  </script>
  
  <style scoped>
  
  .card {
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  thead.thead-dark th {
    background-color: #ffc107;
    color: #000;
  }
  
  
  </style>