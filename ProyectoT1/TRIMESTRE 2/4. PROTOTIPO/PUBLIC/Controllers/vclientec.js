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
