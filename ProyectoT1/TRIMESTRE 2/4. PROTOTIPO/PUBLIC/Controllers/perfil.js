function guardarCambios(tipo) {
    alert("Cambios guardados exitosamente para el " + tipo + ".");
}

function uploadImage() {
    const input = document.getElementById('upload-image');
    const image = document.getElementById('profile-image');
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            image.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function navigateTo(page) {
    window.location.href = page;
}
