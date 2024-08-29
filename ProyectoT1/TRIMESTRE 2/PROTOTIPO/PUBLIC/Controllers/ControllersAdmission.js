function flipCard(card) {
    card.classList.toggle('flipped');
}


//Modal Alumns 
let filaSeleccionada;

$(document).on('click', '.btn-modification', function () {
    filaSeleccionada = $(this).closest('tr');
    const nombre = filaSeleccionada.find('td:eq(0)').text();
    const nota = filaSeleccionada.find('td:eq(1)').text();
    const profesor = filaSeleccionada.find('td:eq(2)').text();
    const clase = filaSeleccionada.find('td:eq(3)').text();
    const status = filaSeleccionada.find('td:eq(4)').text();

    $('#nombre').val(nombre);
    $('#nota').val(nota);
    $('#profesor').val(profesor);
    $('#clase').val(clase);
    $('#status').val(status);
});

$('#guardarCambios').on('click', function () {
    const nombre = $('#nombre').val();
    const nota = $('#nota').val();
    const profesor = $('#profesor').val();
    const clase = $('#clase').val();
    const status = $('#status').val();

    filaSeleccionada.find('td:eq(0)').text(nombre);
    filaSeleccionada.find('td:eq(1)').text(nota);
    filaSeleccionada.find('td:eq(2)').text(profesor);
    filaSeleccionada.find('td:eq(3)').text(clase);
    filaSeleccionada.find('td:eq(4)').text(status);

    $('#ModificationModal').modal('hide');
});

//Modal Alumns Final


// Modal Events
// Variables
const modal = document.getElementById("modalModificar");
const eventForm = document.getElementById("formEvento");
const addressInput = document.getElementById("direccion");
const timeInput = document.getElementById("hora");
const hostInput = document.getElementById("anfitrion");
const descriptionInput = document.getElementById("descripcion");
const imageInput = document.getElementById("imagen");

// Function to open the modal
function openModal(eventClass) {
    //event details
    const eventCard = document.querySelector(`.${eventClass}`);
    addressInput.value = eventCard.querySelector('p[id^="eventAddress"]').innerText.replace("Dirección: ", "");
    timeInput.value = eventCard.querySelector('p[id^="eventTime"]').innerText.replace("Hora: ", "");
    hostInput.value = eventCard.querySelector('p[id^="eventHost"]').innerText.replace("Anfitrión: ", "");
    descriptionInput.value = eventCard.querySelector('p[id^="eventDescription"]').innerText.replace("Descripción: ", "");

    // Open the modal
    modal.style.display = "block";

    eventForm.onsubmit = function (e) {
        e.preventDefault();

        // Update event card details
        eventCard.querySelector('p[id^="eventAddress"]').innerText = "Dirección: " + addressInput.value;
        eventCard.querySelector('p[id^="eventTime"]').innerText = "Hora: " + timeInput.value;
        eventCard.querySelector('p[id^="eventHost"]').innerText = "Anfitrión: " + hostInput.value;
        eventCard.querySelector('p[id^="eventDescription"]').innerText = "Descripción: " + descriptionInput.value;

        // Update event image if a new one was selected
        if (imageInput.files.length > 0) {
            const reader = new FileReader();
            reader.onload = function (event) {
                const eventImageId = `eventImage${eventClass.slice(-1)}`;
                document.getElementById(eventImageId).src = event.target.result;
            };
            reader.readAsDataURL(imageInput.files[0]);
        }

        // Close the modal
        modal.style.display = "none";
    };
}

// Close the modal if clicking outside of it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}