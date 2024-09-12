// Datos simulados de clases programadas (estos datos pueden venir de una base de datos)
const classData = {
    '15/09/2024': ['Clase de Salsa', 'Clase de Bachata'],
    '20/09/2024': ['Clase de Tango', 'Clase de Merengue'],
    '25/09/2024': ['Clase de Hip-Hop', 'Clase de Ballet']
};

var selectedDate = null;
var selectedClass = null;

// Inicializar el calendario
var picker = new Pikaday({
    field: document.getElementById('datepicker'),
    format: 'DD/MM/YYYY',
    toString(date) {
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    },
    onSelect: function(date) {
        selectedDate = picker.toString();
        document.getElementById('selectedDate').innerText = selectedDate;
        loadClasses(selectedDate);
    }
});

// Cargar clases programadas para la fecha seleccionada
function loadClasses(date) {
    const classSelect = document.getElementById('classSelect');
    const classSection = document.getElementById('class-section');
    classSelect.innerHTML = ''; // Limpiar opciones anteriores

    if (classData[date]) {
        classData[date].forEach(clase => {
            const option = document.createElement('option');
            option.value = clase;
            option.text = clase;
            classSelect.appendChild(option);
        });
        classSection.style.display = 'block';
    } else {
        classSection.style.display = 'none';
        alert('No hay clases programadas para esta fecha.');
    }
}

function sendNotification(message) {
    if (!selectedDate) {
        alert('Por favor, seleccione una fecha antes de enviar.');
        return;
    }
    if (!document.getElementById('classSelect').value) {
        alert('Por favor, seleccione una clase para enviar la notificación.');
        return;
    }
    selectedClass = document.getElementById('classSelect').value;
    showModal(message);
}

function sendCustomNotification() {
    const message = document.getElementById('customMessage').value;
    const sendToTeacher = document.getElementById('sendToTeacher').checked;

    if (!selectedDate) {
        alert('Por favor, seleccione una fecha antes de enviar.');
        return;
    }
    if (!document.getElementById('classSelect').value) {
        alert('Por favor, seleccione una clase para enviar la notificación.');
        return;
    }
    if (message) {
        selectedClass = document.getElementById('classSelect').value;
        let fullMessage = `Notificación personalizada enviada: ${message}.`;
        fullMessage += sendToTeacher ? " También fue enviada al maestro." : "";
        showModal(fullMessage);
    } else {
        alert('Por favor, escribe un mensaje antes de enviar.');
    }
}

function showModal(message) {
    document.getElementById('modalBodyContent').innerText = `Mensaje enviado: ${message}\nClase: ${selectedClass}\nFecha: ${selectedDate}`;
    new bootstrap.Modal(document.getElementById('notificationModal')).show();
}
