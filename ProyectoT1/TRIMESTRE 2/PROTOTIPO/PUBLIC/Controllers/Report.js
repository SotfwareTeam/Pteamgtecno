let currentCategory = '';

document.addEventListener('DOMContentLoaded', function () {
    loadReports();
});

function openModal(category) {
    currentCategory = category; // Guardar la categoría actual (Clase, Profesor, Estudiante)
    document.getElementById('reportForm').reset(); // Limpiar el formulario
    $('#reportModal').modal('show'); // Mostrar el modal
}

function addReport() {
    const reportTitle = document.getElementById('reportTitle').value;
    const reportDescription = document.getElementById('reportDescription').value;
    const reportAdditionalInfo = document.getElementById('reportAdditionalInfo').value;
    const createdBy = "Usuario"; // Asumiendo que el usuario está creando el reporte
    const currentDate = new Date().toISOString().split('T')[0]; // Obtener la fecha actual en formato AAAA-MM-DD

    if (reportTitle && reportDescription) {
        const report = {
            title: reportTitle,
            date: currentDate,
            description: reportDescription,
            createdBy: createdBy,
            additionalInfo: reportAdditionalInfo
        };

        saveReport(currentCategory, report);
        addReportToDOM(currentCategory, report);
        $('#reportModal').modal('hide'); // Ocultar el modal después de agregar el reporte
    } else {
        alert("Debe ingresar un título y una descripción para el reporte.");
    }
}

function saveReport(category, report) {
    let reports = JSON.parse(localStorage.getItem('reports')) || {};
    if (!reports[category]) {
        reports[category] = [];
    }
    reports[category].push(report);
    localStorage.setItem('reports', JSON.stringify(reports));
}

function loadReports() {
    let reports = JSON.parse(localStorage.getItem('reports')) || {};
    for (const category in reports) {
        reports[category].forEach(report => {
            addReportToDOM(category, report);
        });
    }
}

function addReportToDOM(category, report) {
    const newReport = document.createElement('li');
    newReport.innerHTML = `<a href="#" onclick="registerReport('${report.title}', '${report.date}', '${report.description}', '${report.createdBy}', '${report.additionalInfo}')">${report.title}</a>`;

    if (category === 'Clase') {
        document.querySelector('#collapseOne .card-body ul').appendChild(newReport);
    } else if (category === 'Profesor') {
        document.querySelector('#collapseTwo .card-body ul').appendChild(newReport);
    } else if (category === 'Estudiante') {
        document.querySelector('#collapseThree .card-body ul').appendChild(newReport);
    }
}

function registerReport(title, date, description, createdBy, additionalInfo) {
    const reportContent = document.getElementById('reportContent');

    // Crear el contenido del reporte
    const reportHTML = `
        <h3>${title}</h3>
        <p><strong>Fecha de Creación:</strong> ${date}</p>
        <p><strong>Descripción:</strong> ${description}</p>
        <p><strong>Creado por:</strong> ${createdBy}</p>
        <p><strong>Información Complementaria:</strong> ${additionalInfo}</p>
    `;

    // Colocar el contenido en la sección de reportes
    reportContent.innerHTML = reportHTML;
}
