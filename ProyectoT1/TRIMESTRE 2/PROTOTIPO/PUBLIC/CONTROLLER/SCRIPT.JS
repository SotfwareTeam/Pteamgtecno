// Validación del formulario y redirección
(function () {
    'use strict';

    // Obtener el formulario
    const form = document.getElementById('formRegistro');

    // Escuchar el evento de envío del formulario
    form.addEventListener('submit', function (event) {
        // Verificar si el formulario es válido
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            // Prevenir el comportamiento por defecto para manejar la redirección manualmente
            event.preventDefault(); 
            // Redirigir a la vista de inicio de sesión
            window.location.href = 'iniciar_sesion.html';
        }

        form.classList.add('was-validated');
    }, false);
})();

// Mostrar/Ocultar contraseña
document.getElementById('togglePassword').addEventListener('click', function () {
    const passwordField = document.getElementById('contrasena');
    const passwordFieldType = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordField.setAttribute('type', passwordFieldType);

    // Cambiar el icono
    const icon = document.getElementById('iconoOjo');
    icon.classList.toggle('bi-eye');
    icon.classList.toggle('bi-eye-slash');
});

