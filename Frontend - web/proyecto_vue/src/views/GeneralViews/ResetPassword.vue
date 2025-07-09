<template>
  <div class="reset-password-page">
    <div class="container">
      <div class="password-container">
        <h2 class="password-title">Restablecer Contraseña</h2>
        <form @submit.prevent="handleResetPassword">
          <div class="mb-3">
            <label for="newPassword" class="form-label">Nueva Contraseña</label>
            <input
              type="password"
              class="form-control"
              id="newPassword"
              v-model="newPassword"
              placeholder="Ingresa tu nueva contraseña"
              required
            >
          </div>

          <div class="mb-3">
            <label for="confirmPassword" class="form-label">Confirmar Contraseña</label>
            <input
              type="password"
              class="form-control"
              id="confirmPassword"
              v-model="confirmPassword"
              placeholder="Confirma tu nueva contraseña"
              required
            >
          </div>

          <button type="submit" class="btn btn-primary btn-recuperar">
            Cambiar Contraseña
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ResetPassword',
  data() {
    return {
      newPassword: '',
      confirmPassword: '',
      token: ''
    }
  },
  mounted() {
    this.token = this.$route.query.token
    console.log("Token capturado desde URL:", this.token)

    if (!this.token) {
      alert('Token no válido')
      this.$router.push('/')
    }
  },
  methods: {
    mostrarAlerta(tipo, mensaje) {
      alert(`${tipo.toUpperCase()}: ${mensaje}`)
    },

    async handleResetPassword() {
      if (this.newPassword !== this.confirmPassword) {
        this.mostrarAlerta('error', 'Las contraseñas no coinciden')
        return
      }

      const payload = {
        token: this.token,
        newPassword: this.newPassword
      }

      console.log("Datos que se enviarán al backend:", payload)

      try {
        const response = await axios.post('https://hqt8rl0q-80.use2.devtunnels.ms/backend/send_reset_password.php', payload, {
          headers: { 'Content-Type': 'application/json' }
        })

        console.log("Respuesta cruda de axios:", response)
        console.log("Respuesta del backend (data):", response.data)

        // 🧠 Aquí se hace la validación robusta:
        if (typeof response.data === 'object' && response.data.success) {
          this.mostrarAlerta('éxito', 'Contraseña actualizada correctamente')

          setTimeout(() => {
            this.$router.push('/')
          }, 300) // un poco más de tiempo para mostrar alerta
        } else {
          const mensaje = response.data?.error || 'No se pudo actualizar la contraseña.'
          this.mostrarAlerta('error', mensaje)
        }
      } catch (error) {
        console.error('Error al comunicarse con el backend:', error)
        this.mostrarAlerta('error', 'Error de conexión con el servidor.')
      }
    }
  }
}
</script>

<style scoped>
.reset-password-page {
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
