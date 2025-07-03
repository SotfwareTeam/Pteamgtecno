<template>
  <NavbarAdmin />
  <br><br><br>
  <div class="container mt-5 mb-5">
    <div class="d-flex justify-content-between align-items-center bg-light p-3 rounded-top shadow-sm">
      <div class="d-flex align-items-center">
        <i class="fas fa-users mr-2"></i>
        <strong>Estudiantes</strong>
      </div>
      <div class="input-group w-50">
        <input v-model="busqueda" type="text" class="form-control" placeholder="Buscar..." />
        <div class="input-group-append">
          <span class="input-group-text"><i class="fas fa-search"></i></span>
        </div>
      </div>
    </div>

    <!-- Tabla de alumnos -->
    <div class="table-responsive">
      <table class="table table-bordered text-center mb-0">
        <thead class="thead-dark">
          <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Nota</th>
            <th>Status</th>
            <th>Rol</th>
            <th>Modificar</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(alumno, index) in alumnosFiltrados" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ alumno.nombre }}</td>
            <td>{{ alumno.nota }}</td>
            <td>{{ alumno.status }}</td>
            <td>{{ alumno.rol }}</td>
            <td>
              <button class="btn btn-success btn-sm" @click="abrirModalModificar(index)">Modificar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div v-if="mostrarModal" class="modal-overlay">
      <div class="modal-box">
        <h5 class="modal-title">Modificar Alumno</h5>

        <div class="form-group">
          <label>Nombre</label>
          <input v-model="formulario.nombre" class="form-control" />
        </div>
        <!-- Rol -->
        <div class="form-group">
          <label>Rol</label>
          <select v-model="formulario.status" class="form-control">
            <option disabled value="">Seleccione un rol</option>
            <option v-for="rol in rolesDisponibles" :key="rol.id_rol" :value="rol.nombre_rol">
              {{ rol.nombre_rol }}
            </option>
          </select>
        </div>

        <!-- Estado -->
        <div class="form-group">
          <label>Status</label>
          <select v-model="formulario.rol" class="form-control">
            <option disabled value="">Seleccione un estado</option>
            <option value="Activo">Activo</option>
            <option value="Inactivo">Inactivo</option>
            <option value="Pausado">Pausado</option>
          </select>
        </div>


        <div class="modal-footer mt-4 d-flex justify-content-between">
          <button class="btn btn-secondary" @click="cerrarModal">Cancelar</button>
          <button class="btn btn-primary" @click="guardarCambios">Guardar Cambios</button>
        </div>
      </div>
    </div>
  </div>
   <FooterAdmin />
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'
import FooterAdmin from '@/components/footers/FooterAdmin.vue';
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';




const alumnos = ref([])
const busqueda = ref('')
const mostrarModal = ref(false)
const filaSeleccionada = ref(null)

const formulario = ref({
  id_usuario: null,
  nombre: '',
  rol: '',
  status: ''
})

onMounted(async () => {
  try {
    const res = await fetch('http://localhost/backend/Usuarios_roles.php')
    const data = await res.json()

    alumnos.value = data.map(usuario => ({
      id_usuario: usuario.id_usuario,
      nombre: `${usuario.nombre} ${usuario.apellido}`,
      nota: usuario.nota_promedio ?? 'N/A',
      status: usuario.nombre_rol,
      rol: usuario.estado.charAt(0).toUpperCase() + usuario.estado.slice(1)
    }))
  } catch (err) {
    console.error('Error al cargar alumnos:', err)
  }
})


const alumnosFiltrados = computed(() =>
  alumnos.value.filter((alumno) =>
    Object.values(alumno).some((campo) =>
      campo.toLowerCase().includes(busqueda.value.toLowerCase())
    )
  )
)

const abrirModalModificar = (index) => {
  filaSeleccionada.value = index
  const alumno = alumnos.value[index]

  formulario.value = {
    id_usuario: alumno.id_usuario,
    nombre: alumno.nombre,
    nota: alumno.nota,
    status: alumno.status, // ← debe coincidir con el value del option (ej: 'profesor')
    rol: alumno.rol        // ← debe coincidir (ej: 'Activo')
  }

  mostrarModal.value = true
}


const cerrarModal = () => {
  mostrarModal.value = false
}

const guardarCambios = async () => {
  try {
    const res = await fetch('http://localhost/backend/update_usuario.php', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        id_usuario: formulario.value.id_usuario,
        estado: formulario.value.rol.toLowerCase(),
        id_rol: getIdRol(formulario.value.status)
      })
    })

    const result = await res.json()

    if (result.success) {
      const index = filaSeleccionada.value
      alumnos.value[index].nombre = formulario.value.nombre
      alumnos.value[index].status = formulario.value.status
      alumnos.value[index].rol = formulario.value.rol
      mostrarModal.value = false
    } else {
      console.error('Error al guardar:', result.error)
      alert('Error al guardar')
    }
  } catch (err) {
    console.error('Error al conectar:', err)
  }
}

const getIdRol = (nombreRol) => {
  switch (nombreRol.toLowerCase()) {
    case 'administrador': return 1
    case 'profesor': return 2
    case 'estudiante': return 3
    default: return 0 // rol inválido, se puede bloquear acceso
  }
}
const rolesDisponibles = ref([])

onMounted(async () => {
  try {
    const resUsuarios = await fetch('http://localhost/backend/Usuarios_roles.php')
    const dataUsuarios = await resUsuarios.json()

    alumnos.value = dataUsuarios.map(usuario => ({
      id_usuario: usuario.id_usuario,
      nombre: `${usuario.nombre} ${usuario.apellido}`,
      nota: usuario.nota_promedio ?? 'N/A',
      status: usuario.nombre_rol,
      rol: usuario.estado.charAt(0).toUpperCase() + usuario.estado.slice(1)
    }))

    const resRoles = await fetch('http://localhost/backend/get_roles.php')
    rolesDisponibles.value = await resRoles.json()

  } catch (err) {
    console.error('Error:', err)
  }
})



</script>

<style scoped>
.container {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.table thead th {
  background-color: #2d2d2d;
  color: white;
}

.input-group .form-control {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}

.input-group-text {
  background-color: white;
  border-left: none;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

.btn-success {
  background-color: #2e7d32;
  border: none;
}

.btn-success:hover {
  background-color: #1b5e20;
}

/* === Modal Styles === */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(20, 20, 20, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2000;
}

.modal-box {
  background-color: #adb4c2;
  padding: 2rem;
  border-radius: 12px;
  width: 90%;
  max-width: 500px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
}

.modal-title {
  font-weight: bold;
  font-size: 20px;
  margin-bottom: 20px;
  color: #333;
}

.modal-box input,
.modal-box select {
  border-radius: 8px;
  padding: 8px;
  border: 1px solid #ccc;
  margin-bottom: 15px;
  width: 100%;
}

.modal-footer button {
  min-width: 130px;
}
</style>
