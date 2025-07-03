import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/GeneralViews/LoginView.vue'
import ForgotPassword from '../views/GeneralViews/ForgotPassword.vue'
import ResetPassword from '@/views/GeneralViews/ResetPassword.vue'
import AdminView from '@/views/AdminViews/AdminView.vue'
import AdminProgramingView from '@/views/AdminViews/AdminProgramingView.vue'
import ClassView from '@/views/AdminViews/ClassView.vue'
import Classes1 from '../views/AdminViews/adminRoles.vue'
import NotificationView from '@/views/AdminViews/NotificationView.vue';
import EventsPage from '@/views/AdminViews/EventsPage.vue';
import ProfileView from '@/views/AdminViews/ProfileView.vue';
import Classes2 from '../views/UsersViews/vcliente.vue'
import ReporteView from '@/views/AdminViews/ReporteView.vue'
import Student from '../views/UsersViews/StudentView.vue'
import StudentEvents from '../views/UsersViews/StudentEvents.vue'
import StudentProfile from '../views/UsersViews/StudentProfile.vue'
import StudentProgramming from '../views/UsersViews/StudentProgramingClass.vue'
import ClassAssigned from '../views/ProfessorViews/ClassAssigned.vue'
import TeacherProfile from '@/views/ProfessorViews/TeacherProfile.vue'
import TeacherProgramingView from '@/views/ProfessorViews/TeacherProgramingView.vue'
import Teacherview from '@/views/ProfessorViews/Teacherview.vue'
import TeacherEvents from '@/views/ProfessorViews/TeacherEvents.vue'





const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'LoginView',
      component: Login,
    },
    {
      path: '/forgot-password',
      name: 'ForgotPassword',
      component: ForgotPassword
    },
    {
      path: '/ResetPassword',
      name: 'ResetPassword',
      component: ResetPassword
    },
    {
      path: '/Admin/inicio',
      name: 'AdminView',
      component: AdminView,
    },
    {
      path: '/Admin/programacion_clases',
      name: 'AdminProgramingView',
      component: AdminProgramingView,
    },
    {
      path: '/Roles',
      name: 'adminRoles',
      component: Classes1,
    },
    {
      path: '/Admin/Calificaciones',
      name: 'ClassView',
      component: ClassView,
    },
    {
      path: '/vclientePage',
      name: 'vclienteview',
      component: Classes2,
    },
    {
      path: '/Admin/Reporte',
      name: 'ReporteView',
      component: ReporteView,
    },
    {
      path: '/Admin/Notificaciones',
      name: 'NotificationView',
      component: NotificationView
    },
    {
      path: '/Admin/eventos',
      name: 'EventsPage',
      component: EventsPage
    },
    {
      path: '/Admin/profile',
      name: 'ProfileView',
      component: ProfileView
    },
    // Vistas estudiantes
    {
      path: '/Estudiante/Inicio',
      name: 'StudentView',
      component: Student,
    },
    {
      path: '/Estudiante/Eventos',
      name: 'StudentEvents',
      component: StudentEvents,
    },
    {
      path: '/Estudiante/Perfil',
      name: 'StudentProfile',
      component: StudentProfile,
    },
    {
      path: '/Estudiante/Programar_clase',
      name: 'StudentProgrammingClass',
      component: StudentProgramming,
    },
    //Vistas profesor
    {
      path: '/Profesor/Calificacion',
      name: 'ClassAssigned',
      component: ClassAssigned,
    },
    {
      path: '/Profesor/Perfil',
      name: 'TeacherProfile',
      component: TeacherProfile,
    },
    {
      path: '/profesor/Inicio',
      name: 'Teacherview',
      component:Teacherview,
    },
    {
      path: '/Profesor/Calendario',
      name: 'TeacherProgramingView',
      component: TeacherProgramingView,
    },
    {
      path: '/Profesor/Eventos',
      name: 'TeacherEvents',
      component: TeacherEvents,
    },




  ],
})

export default router
