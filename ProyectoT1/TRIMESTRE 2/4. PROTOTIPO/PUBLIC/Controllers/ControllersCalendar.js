document.addEventListener('DOMContentLoaded', function () {
    const calendarBody = document.getElementById('calendarBody');
    const currentMonth = document.getElementById('currentMonth');
    const prevMonthBtn = document.getElementById('prevMonth');
    const nextMonthBtn = document.getElementById('nextMonth');
    let selectedCell = null;
    let scheduledClasses = {}; // Objeto para almacenar la información programada

    let today = new Date();
    let currentYear = today.getFullYear();
    let currentMonthIndex = today.getMonth();

    function generateCalendar(month, year) {
        calendarBody.innerHTML = '';
        const firstDay = new Date(year, month).getDay();
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        currentMonth.textContent = new Date(year, month).toLocaleDateString('es-ES', { month: 'long', year: 'numeric' });

        let date = 1;
        for (let i = 0; i < 6; i++) {
            let row = document.createElement('tr');
            for (let j = 0; j < 7; j++) {
                let cell = document.createElement('td');
                if (i === 0 && j < firstDay) {
                    let cellText = document.createTextNode('');
                    cell.appendChild(cellText);
                } else if (date > daysInMonth) {
                    break;
                } else {
                    let cellText = document.createTextNode(date);
                    cell.appendChild(cellText);

                    // Pintar de verde si ya hay una clase programada para este día
                    const dateKey = `${year}-${month + 1}-${date}`;
                    if (scheduledClasses[dateKey]) {
                        cell.classList.add('selected', 'scheduled');
                    }

                    cell.addEventListener('click', function() {
                        if (selectedCell) {
                            selectedCell.classList.remove('selected');
                        }
                        cell.classList.add('selected');
                        selectedCell = cell;
                    });

                    date++;
                }
                row.appendChild(cell);
            }
            calendarBody.appendChild(row);
        }
    }

    prevMonthBtn.addEventListener('click', function () {
        currentMonthIndex--;
        if (currentMonthIndex < 0) {
            currentMonthIndex = 11;
            currentYear--;
        }
        generateCalendar(currentMonthIndex, currentYear);
    });

    nextMonthBtn.addEventListener('click', function () {
        currentMonthIndex++;
        if (currentMonthIndex > 11) {
            currentMonthIndex = 0;
            currentYear++;
        }
        generateCalendar(currentMonthIndex, currentYear);
    });

    // Programar clase
    document.getElementById('programForm').addEventListener('submit', function(e) {
        e.preventDefault();
        if (selectedCell) {
            const dateKey = `${currentYear}-${currentMonthIndex + 1}-${selectedCell.textContent}`;
            const classData = {
                profesor: document.getElementById('profesor').value,
                sede: document.getElementById('sede').value,
                hora: document.getElementById('hora').value,
                clase: document.getElementById('clase').value,
                dificultad: document.getElementById('dificultad').value
            };
            scheduledClasses[dateKey] = classData;
            selectedCell.classList.add('scheduled');
            selectedCell.style.backgroundColor = 'green'; // Pintar de verde al guardar

            let programModal = bootstrap.Modal.getInstance(document.getElementById('programModal'));
            programModal.hide();
        } else {
            alert('Por favor, selecciona un día.');
        }
    });

    // Eliminar clase
    document.getElementById('confirmDelete').addEventListener('click', function() {
        if (selectedCell) {
            const dateKey = `${currentYear}-${currentMonthIndex + 1}-${selectedCell.textContent}`;
            if (scheduledClasses[dateKey]) {
                delete scheduledClasses[dateKey];
                selectedCell.classList.remove('scheduled');
                selectedCell.style.backgroundColor = ''; // Despintar al eliminar
                let deleteModal = bootstrap.Modal.getInstance(document.getElementById('deleteModal'));
                deleteModal.hide();
            } else {
                alert('No hay clase programada para eliminar.');
            }
        } else {
            alert('Por favor, selecciona un día.');
        }
    });

    // Editar clase
    document.getElementById('editModal').addEventListener('show.bs.modal', function () {
        if (selectedCell) {
            const dateKey = `${currentYear}-${currentMonthIndex + 1}-${selectedCell.textContent}`;
            const classData = scheduledClasses[dateKey];
            if (classData) {
                document.getElementById('editProfesor').value = classData.profesor;
                document.getElementById('editSede').value = classData.sede;
                document.getElementById('editHora').value = classData.hora;
                document.getElementById('editClase').value = classData.clase;
                document.getElementById('editDificultad').value = classData.dificultad;
            } else {
                alert('No hay clase programada para este día.');
                let editModal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
                editModal.hide();
            }
        } else {
            alert('Por favor, selecciona un día.');
        }
    });

    document.getElementById('editForm').addEventListener('submit', function(e) {
        e.preventDefault();
        if (selectedCell) {
            const dateKey = `${currentYear}-${currentMonthIndex + 1}-${selectedCell.textContent}`;
            if (scheduledClasses[dateKey]) {
                scheduledClasses[dateKey] = {
                    profesor: document.getElementById('editProfesor').value,
                    sede: document.getElementById('editSede').value,
                    hora: document.getElementById('editHora').value,
                    clase: document.getElementById('editClase').value,
                    dificultad: document.getElementById('editDificultad').value
                };
                let editModal = bootstrap.Modal.getInstance(document.getElementById('editModal'));
                editModal.hide();
            } else {
                alert('No hay clase programada para este día.');
            }
        } else {
            alert('Por favor, selecciona un día.');
        }
    });

    // Información del día
    document.getElementById('infoModal').addEventListener('show.bs.modal', function () {
        if (selectedCell) {
            const dateKey = `${currentYear}-${currentMonthIndex + 1}-${selectedCell.textContent}`;
            const classData = scheduledClasses[dateKey];
            const infoContent = document.getElementById('infoContent');
            if (classData) {
                infoContent.innerHTML = `
                    <p><strong>Profesor:</strong> ${classData.profesor}</p>
                    <p><strong>Sede:</strong> ${classData.sede}</p>
                    <p><strong>Hora:</strong> ${classData.hora}</p>
                    <p><strong>Clase:</strong> ${classData.clase}</p>
                    <p><strong>Dificultad:</strong> ${classData.dificultad}</p>
                `;
            } else {
                alert('No hay información disponible para este día.');
                let infoModal = bootstrap.Modal.getInstance(document.getElementById('infoModal'));
                infoModal.hide();
            }
        } else {
            alert('Por favor, selecciona un día.');
        }
    });

    generateCalendar(currentMonthIndex, currentYear);
});
