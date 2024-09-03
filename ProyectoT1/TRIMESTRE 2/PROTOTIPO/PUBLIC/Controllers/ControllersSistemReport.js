// script.js
$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

// Gráficos con Chart.js

// Datos precargados para Rendimiento de Clases
const ctx1 = document.getElementById('rendimientoClasesChart').getContext('2d');
const rendimientoClasesChart = new Chart(ctx1, {
    type: 'bar',
    data: {
        labels: ['Salsa Básico', 'Bachata Intermedio', 'Hip-Hop Avanzado', 'Ballet Principiante'],
        datasets: [{
            label: 'Estudiantes Completados',
            data: [35, 22, 18, 40],
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }, {
            label: 'Estudiantes Inscritos',
            data: [50, 30, 25, 50],
            backgroundColor: 'rgba(255, 159, 64, 0.2)',
            borderColor: 'rgba(255, 159, 64, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Datos precargados para Satisfacción de Estudiantes
const ctx2 = document.getElementById('satisfaccionEstudiantesChart').getContext('2d');
const satisfaccionEstudiantesChart = new Chart(ctx2, {
    type: 'pie',
    data: {
        labels: ['Muy Satisfecho', 'Satisfecho', 'Neutral', 'Insatisfecho', 'Muy Insatisfecho'],
        datasets: [{
            label: 'Satisfacción de Estudiantes',
            data: [60, 25, 10, 3, 2],
            backgroundColor: [
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(255, 99, 132, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(75, 192, 192, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(255, 99, 132, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
    }
});

// Simulación de Chat con datos precargados
const chatLog = document.getElementById('chatLog');
const chatMessageInput = document.getElementById('chatMessage');
const sendMessageButton = document.getElementById('sendMessage');

// Mensajes precargados en el chat
const initialMessages = [
    { sender: 'admin', message: '¡Bienvenido al soporte de la Academia de Baile! ¿En qué puedo ayudarte?' },
    { sender: 'user', message: 'Hola, tengo una duda sobre la clase de Salsa Básico.' },
    { sender: 'admin', message: 'Claro, dime, ¿qué te gustaría saber?' }
];

initialMessages.forEach(msg => {
    addMessageToChat(msg.sender, msg.message);
});

// Funcionalidad para enviar mensajes nuevos
sendMessageButton.addEventListener('click', function() {
    const message = chatMessageInput.value.trim();
    if (message !== '') {
        addMessageToChat('user', message);
        chatMessageInput.value = '';
        
        // Simulación de respuesta automática
        setTimeout(() => {
            addMessageToChat('admin', 'Recibido, en breve te responderemos.');
        }, 1000);
    }
});

function addMessageToChat(sender, message) {
    const messageElement = document.createElement('div');
    messageElement.classList.add('chat-message', sender);
    messageElement.textContent = message;
    chatLog.appendChild(messageElement);
    chatLog.scrollTop = chatLog.scrollHeight;
}
