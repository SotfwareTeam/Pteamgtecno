<template>
    <div class="forgot-password-page">
      <div class="container">
        <div class="password-container">
          <h2 class="password-title">Recuperar Contraseña</h2>
          <p class="text-muted mb-4">Ingresa tu correo electrónico y te enviaremos un enlace para restablecer tu contraseña.</p>

          <form @submit.prevent="handleSubmit">
            <div class="mb-3">
              <label for="email" class="form-label">Correo Electrónico</label>
              <input
                type="email"
                class="form-control"
                id="email"
                v-model="email"
                placeholder="tu@email.com"
                required
              >
            </div>

            <button type="submit" class="btn btn-primary btn-recuperar">
              Enviar enlace de recuperación
            </button>

            <div class="text-center mt-3">
              <router-link to="/" class="text-decoration-none">
                Volver al inicio de sesión
              </router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </template>

  <script>
  export default {
    name: 'ForgotPassword',
    data() {
      return {
        email: ''
      }
    },
    methods: {
  async handleSubmit() {
  fetch('http://localhost/backend/send_reset_email.php', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email: this.email }) // 👈 Aquí se envía correctamente
  })
    .then(response => response.json())
    .then(data => {
      if (data.success) {
        alert('Correo enviado correctamente');
        this.$router.push('/');
      } else {
        alert('Error: ' + data.error);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}


}

  }
  </script>

  <style scoped>
  .forgot-password-page {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }

  .password-container {
    max-width: 500px;
    margin: 100px auto;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    background-color: white;
  }

  .password-title {
    color: #1a1717;
    margin-bottom: 25px;
    text-align: center;
    font-weight: 600;
  }

  .btn-recuperar {
    background-color: #5f2f9a;
    border: none;
    width: 100%;
    padding: 10px;
    font-weight: 500;
  }

  .btn-recuperar:hover {
    background-color: #5f2f9a;
  }

  .form-control:focus {
    border-color: #5f2f9a;
    box-shadow: 0 0 0 0.25rem rgba(197, 13, 253, 0.25);
  }
  </style>
