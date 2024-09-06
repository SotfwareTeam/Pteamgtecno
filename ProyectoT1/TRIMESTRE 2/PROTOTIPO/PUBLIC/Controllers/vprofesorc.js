// Función para actualizar la sección de calificación de estudiantes
document.querySelectorAll("#lista-estudiantes tr").forEach(function(row) {
    row.addEventListener("click", function() {
        var nombre = this.getAttribute("data-nombre");
        var clase = this.getAttribute("data-clase");
        var calificacion = this.getAttribute("data-calificacion");
        var observaciones = this.getAttribute("data-observaciones");

        // Actualizar el contenido de la sección de calificación
        document.getElementById("nombre-estudiante").textContent = nombre;
        document.getElementById("clase-estudiante").textContent = clase;
        document.getElementById("calificacion-estudiante").textContent = calificacion;
        document.getElementById("observaciones-estudiante").textContent = observaciones;

        // Actualizar la visualización de las estrellas
        var estrellas = "★★★★★".substring(0, Math.round(calificacion)) + "☆☆☆☆☆".substring(0, 5 - Math.round(calificacion));
        document.getElementById("estrellas-calificacion").textContent = estrellas;
    });
});

// Asistencia toggle
document.querySelectorAll(".asistencia-toggle").forEach(function(checkbox) {
    checkbox.addEventListener("change", function() {
        var estudianteRow = this.closest("tr");
        var nombreEstudiante = estudianteRow.getAttribute("data-nombre");

        if (this.checked) {
            console.log(`Asistencia de ${nombreEstudiante}: Presente`);
        } else {
            console.log(`Asistencia de ${nombreEstudiante}: Ausente`);
        }
    });
});
const studentTable = document.getElementById("student-table");
const registerForm = document.getElementById("registerForm");
const editForm = document.getElementById("editForm");

studentTable.addEventListener("click", (e) => {
  if (e.target.tagName === "TD") {
    const row = e.target.parentElement;
    document.getElementById("student-name").textContent = row.cells[0].textContent;
    document.getElementById("class-name").textContent = row.cells[2].textContent;
  }
});

registerForm.addEventListener("submit", (e) => {
  e.preventDefault();
  document.getElementById("student-name").textContent = document.getElementById("studentNameInput").value;
  document.getElementById("class-name").textContent = document.getElementById("classNameInput").value;
  document.getElementById("student-grade").textContent = document.getElementById("gradeInput").value;
  document.getElementById("student-obs").textContent = document.getElementById("obsInput").value;
  bootstrap.Modal.getInstance(document.getElementById("registerModal")).hide();
});

editForm.addEventListener("submit", (e) => {
  e.preventDefault();
  document.getElementById("student-name").textContent = document.getElementById("editStudentName").value;
  document.getElementById("class-name").textContent = document.getElementById("editClassName").value;
  document.getElementById("student-grade").textContent = document.getElementById("editGrade").value;
  document.getElementById("student-obs").textContent = document.getElementById("editObs").value;
  bootstrap.Modal.getInstance(document.getElementById("editModal")).hide();
});