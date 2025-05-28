<template>
  <body>
    <div>
      <!-- Navigation Bar -->

        <NavbarAdmin/>
        <br><br><br><br>
      <!-- Main Content -->
      <div class="container mt-5">
        <!-- Tarjetas Resumen -->
        <div class="row mb-4">
          <div class="col-lg-6 col-md-6" v-for="(data, index) in summaryCards" :key="index">
            <div class="card text-white mb-3">
              <div class="card-body">
                <div class="card-title text-black">{{ data.title }}</div>
                <div class="card-text text-black">{{ data.value }}</div>
              </div>
            </div>
          </div>
        </div>

        <!-- Acordeón con Secciones -->
        <div class="row">
          <div class="col-lg-4">
            <div class="accordion" id="accordionExample">
              <AccordionItem
                v-for="(item, index) in accordionData"
                :key="index"
                :title="item.title"
                :items="item.items"
                @showReport="showReport"
              />
            </div>
          </div>
          <div class="col-lg-8">
            <div class="card">
              <div class="card-header">
                <h2>Reporte Seleccionado</h2>
              </div>
              <div class="card-body" id="reportContent">
                <p v-if="!selectedReport">Seleccione un elemento del acordeón para ver el reporte.</p>
                <p v-else>{{ selectedReport }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
<br><br><br><br>
      <!-- Footer -->

        <FooterAdmin />

    </div>
  </body>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted, watch } from "vue";
import { Chart } from "chart.js";
import NavbarAdmin from '@/components/navbars/NavbarAdmin.vue';
import FooterAdmin from '@/components/footers/FooterAdmin.vue';

export default defineComponent({
  name: "MainComponent",
  components: {
    NavbarAdmin,
    FooterAdmin,
  },
  setup() {
    // Datos para las tarjetas resumen
    const summaryCards = [
      { title: "Total Usuarios", value: "120" },
      { title: "Clases Activas", value: "8" },
    ];

    // Datos para el acordeón
    const accordionData = [
      {
        title: "Clases",
        items: ["Clase 1", "Clase 2", "Clase 3"],
      },
      {
        title: "Profesores",
        items: ["Profesor 1", "Profesor 2", "Profesor 3"],
      },
      {
        title: "Estudiantes",
        items: ["Estudiante 1", "Estudiante 2", "Estudiante 3"],
      },
    ];

    // Reporte seleccionado
    const selectedReport = ref<string | null>(null);

    const showReport = (report: string) => {
      selectedReport.value = report;
    };

    // Datos del footer
    const socialIcons = [
      { name: "logo-youtube", link: "#" },
      { name: "logo-instagram", link: "#" },
      { name: "logo-tiktok", link: "#" },
      { name: "logo-facebook", link: "#" },
      { name: "logo-whatsapp", link: "#" },
    ];

    const footerMenu = ["Inicio", "Equipo", "Contacto", "Video"];
    const currentYear = new Date().getFullYear();

    // Datos para los reportes y gráficos
    const chartInstance = ref<Chart | null>(null);
    const chartCanvas = ref<HTMLCanvasElement | null>(null);

    const currentReport = ref({
      type: null as string | null,
      title: "",
      details: "",
      tableData: [] as Array<{ date: string; attendance: number; comments: string }>,
    });

    const showClassReport = (className: string) => {
      currentReport.value = {
        type: "class",
        title: className,
        details: "",
        tableData: [
          { date: "2024-09-01", attendance: 30, comments: "Excelente" },
          { date: "2024-09-08", attendance: 28, comments: "Buena" },
        ],
      };

      renderChart(["Semana 1", "Semana 2", "Semana 3", "Semana 4"], [30, 28, 32, 25]);
    };

    const showTeacherReport = (teacherName: string) => {
      currentReport.value = {
        type: "teacher",
        title: teacherName,
        details: `Este es el reporte relacionado a ${teacherName}.`,
        tableData: [],
      };
      destroyChart();
    };

    const showStudentReport = (studentName: string) => {
      currentReport.value = {
        type: "student",
        title: studentName,
        details: `Este es el reporte relacionado a ${studentName}.`,
        tableData: [],
      };
      destroyChart();
    };

    const renderChart = (labels: string[], data: number[]) => {
      if (chartInstance.value) {
        chartInstance.value.destroy();
      }

      if (chartCanvas.value) {
        chartInstance.value = new Chart(chartCanvas.value.getContext("2d")!, {
          type: "bar",
          data: {
            labels,
            datasets: [
              {
                label: "Asistentes",
                data,
                backgroundColor: "rgba(54, 162, 235, 0.2)",
                borderColor: "rgba(54, 162, 235, 1)",
                borderWidth: 1,
              },
            ],
          },
          options: {
            responsive: true,
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          },
        });
      }
    };

    const destroyChart = () => {
      if (chartInstance.value) {
        chartInstance.value.destroy();
        chartInstance.value = null;
      }
    };

    onMounted(() => {
      watch(currentReport, (newValue) => {
        if (newValue.type !== "class") {
          destroyChart();
        }
      });
    });

    return {
      summaryCards,
      accordionData,
      selectedReport,
      socialIcons,
      footerMenu,
      currentYear,
      currentReport,
      chartCanvas,
      showReport,
      showClassReport,
      showTeacherReport,
      showStudentReport,
    };
  },
});
</script>


<style scoped>

</style>
