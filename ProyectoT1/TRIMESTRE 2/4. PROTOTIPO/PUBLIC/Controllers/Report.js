// Función para mostrar reporte de clase con gráfico y tabla
function showClassReport(className) {
    document.getElementById('reportContent').innerHTML = `
        <h4>${className}</h4>
        <canvas id="classChart" width="400" height="200"></canvas>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Asistencia</th>
                    <th>Comentarios</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>2024-09-01</td>
                    <td>30</td>
                    <td>Excelente</td>
                </tr>
                <tr>
                    <td>2024-09-08</td>
                    <td>28</td>
                    <td>Buena</td>
                </tr>
            </tbody>
        </table>
    `;

    // Crear gráfico con Chart.js
    const ctx = document.getElementById('classChart').getContext('2d');
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
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Función para mostrar reporte de profesor
function showTeacherReport(teacherName) {
    document.getElementById('reportContent').innerHTML = `
        <h4>${teacherName}</h4>
        <p><strong>Fecha:</strong> 2024-09-03</p>
        <p>Este es el reporte relacionado a ${teacherName}.</p>
    `;
}

// Función para mostrar reporte de estudiante
function showStudentReport(studentName) {
    document.getElementById('reportContent').innerHTML = `
        <h4>${studentName}</h4>
        <p><strong>Fecha:</strong> 2024-09-10</p>
        <p>Este es el reporte relacionado a ${studentName}.</p>
    `;
}
