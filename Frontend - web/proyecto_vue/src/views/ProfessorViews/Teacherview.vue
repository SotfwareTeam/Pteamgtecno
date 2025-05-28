<template>
    <div>
      <NavbarTeachers />
      <br><br><br>
      <!-- Sección Quiénes Somos -->
      <section id="about-us-section" class="py-5">
        <div class="container">
          <div class="row align-items-center">
            <div class="col-md-6">
              <h2 class="section-title-about">Quiénes Somos</h2>
              <p class="about-us-text">
                Somos una academia dedicada a enseñar los mejores estilos de baile,
                brindando una experiencia única para todos los niveles de habilidad.
              </p>
              <a
                href="https://www.instagram.com/ln7dancestudio_/"
                class="btn btn-primary mt-4"
                >Conócenos</a
              >
            </div>
            <div class="col-md-6">
              <div class="about-video-container">
                <video
                  id="aboutUsVideoPlayer"
                  class="about-us-video img-fluid"
                  src="../../../Public/Assets/img/Video/Dance.mp4"
                  controls
                  @dblclick="openVideoModal"
                ></video>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Sección Nuestro Equipo -->
      <section id="team" class="py-5">
        <div class="container">
          <h2 class="text-center text-white">Nuestro Equipo</h2>
          <div class="row">
            <div
              v-for="(instructor, index) in instructors"
              :key="index"
              class="col-md-4 mb-3"
            >
              <div class="instructor-card" @dblclick="toggleCard(index)">
                <div
                  class="instructor-img"
                  :style="{
                    'background-image': `url('${instructor.image}')`,
                  }"
                ></div>
                <div
                  class="instructor-info"
                  :class="{ active: flippedCards[index] }"
                >
                  <h3>{{ instructor.name }}</h3>
                  <p><strong>Habilidades:</strong> {{ instructor.skills }}</p>
                  <p><strong>Descripción:</strong> {{ instructor.description }}</p>
                  <p><strong>Experiencia:</strong> {{ instructor.experience }}</p>
                  <p><strong>Mejor Movimiento:</strong> {{ instructor.bestMove }}</p>
                  <p><strong>Popularidad:</strong></p>
                  <div class="star-rating">
                    {{ instructor.rating }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- Sección Nuestras Clases -->
      <section class="container my-5">
        <h2 class="text-center text-white">Nuestras Clases</h2>
        <p class="text-center text-white">
          Ven y disfruta de nuestras clases presenciales grupales, personalizadas,
          semipersonalizadas y recuerda que también tenemos clases a domicilio!
        </p>
  
        <div id="clasesCarousel" class="carousel-container">
          <div class="carousel-images">
            <img
              v-for="(image, index) in danceImages"
              :key="index"
              :src="image.src"
              :alt="image.alt"
              :class="{ active: currentImageIndex === index }"
            />
          </div>
          <div class="carousel-text">
            <h3 id="carouselTitle" class="text-white">
              {{ danceImages[currentImageIndex].title }}
            </h3>
            <p id="carouselDescription">
              {{ danceImages[currentImageIndex].description }}
            </p>
          </div>
        </div>
      </section>
  
      <!-- Preguntas Frecuentes -->
      <section id="ln7-faqs" class="container mt-5 mb-5">
        <h2 class="text-center mb-4">Preguntas más frecuentes</h2>
  
        <!-- Pestañas -->
        <ul class="nav nav-tabs mb-4" id="ln7-faq-tabs">
          <li class="nav-item">
            <a
              class="nav-link active"
              data-bs-toggle="tab"
              href="#faq-category-1"
              >LN7 FAQs</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" data-bs-toggle="tab" href="#faq-category-2"
              >General</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" data-bs-toggle="tab" href="#faq-category-3"
              >Ubicación</a
            >
          </li>
        </ul>
  
        <!-- Contenido de pestañas -->
        <div class="tab-content">
           <!-- LN7 FAQs -->
        <div class="tab-pane fade show active" id="faq-category-1">
            <div class="accordion" id="ln7-faqs-ln7">
                <!-- Pregunta 1 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ln7-q1">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ln7-a1" aria-expanded="true" aria-controls="ln7-faq-ln7-a1">
                            ¿Cómo puedo ver los horarios de clases?
                        </button>
                    </h2>
                    <div id="ln7-faq-ln7-a1" class="accordion-collapse collapse show"
                        aria-labelledby="ln7-faq-ln7-q1" data-bs-parent="#ln7-faqs-ln7">
                        <div class="accordion-body">
                            Puedes ver los horarios de clases en la sección de horarios de nuestra página web.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 2 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ln7-q2">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ln7-a2" aria-expanded="false" aria-controls="ln7-faq-ln7-a2">
                            ¿Cómo puedo hacer una reserva?
                        </button>
                    </h2>
                    <div id="ln7-faq-ln7-a2" class="accordion-collapse collapse" aria-labelledby="ln7-faq-ln7-q2"
                        data-bs-parent="#ln7-faqs-ln7">
                        <div class="accordion-body">
                            Puedes hacer una reserva a través de nuestra aplicación o en la recepción del estudio.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 3 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ln7-q3">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ln7-a3" aria-expanded="false" aria-controls="ln7-faq-ln7-a3">
                            ¿Es necesario comprar matrícula?
                        </button>
                    </h2>
                    <div id="ln7-faq-ln7-a3" class="accordion-collapse collapse" aria-labelledby="ln7-faq-ln7-q3"
                        data-bs-parent="#ln7-faqs-ln7">
                        <div class="accordion-body">
                            No es necesario comprar matrícula, pero te recomendamos registrarte para recibir
                            beneficios adicionales.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 4 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ln7-q4">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ln7-a4" aria-expanded="false" aria-controls="ln7-faq-ln7-a4">
                            ¿Qué significa: BAS, INT, AVAN?
                        </button>
                    </h2>
                    <div id="ln7-faq-ln7-a4" class="accordion-collapse collapse" aria-labelledby="ln7-faq-ln7-q4"
                        data-bs-parent="#ln7-faqs-ln7">
                        <div class="accordion-body">
                            Estos términos se refieren a los niveles de las clases: Básico (BAS), Intermedio (INT),
                            y Avanzado (AVAN).
                        </div>
                    </div>
                </div>
                <!-- Pregunta 5 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ln7-q5">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ln7-a5" aria-expanded="false" aria-controls="ln7-faq-ln7-a5">
                            ¿Qué es Elenco?
                        </button>
                    </h2>
                    <div id="ln7-faq-ln7-a5" class="accordion-collapse collapse" aria-labelledby="ln7-faq-ln7-q5"
                        data-bs-parent="#ln7-faqs-ln7">
                        <div class="accordion-body">
                            Elenco es un grupo de bailarines seleccionados para representar al estudio en
                            competiciones y eventos especiales.
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- General -->
        <div class="tab-pane fade general-faq" id="faq-category-2">
            <div class="accordion" id="ln7-faqs-general">
                <!-- Pregunta 1 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-general-q1">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-general-a1" aria-expanded="true"
                            aria-controls="ln7-faq-general-a1">
                            ¿Cuáles son los horarios de Bureo Dance Studio?
                        </button>
                    </h2>
                    <div id="ln7-faq-general-a1" class="accordion-collapse collapse show"
                        aria-labelledby="ln7-faq-general-q1" data-bs-parent="#ln7-faqs-general">
                        <div class="accordion-body">
                            Los horarios varían según el día y el tipo de clase. Consulta nuestra página web para
                            ver el horario completo.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 2 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-general-q2">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-general-a2" aria-expanded="false"
                            aria-controls="ln7-faq-general-a2">
                            ¿Cómo hago para matricularme?
                        </button>
                    </h2>
                    <div id="ln7-faq-general-a2" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-general-q2" data-bs-parent="#ln7-faqs-general">
                        <div class="accordion-body">
                            Puedes matricularte en línea a través de nuestro sitio web o visitando el estudio en
                            persona.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 3 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-general-q3">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-general-a3" aria-expanded="false"
                            aria-controls="ln7-faq-general-a3">
                            ¿Cómo puedo comprar un paquete de clases?
                        </button>
                    </h2>
                    <div id="ln7-faq-general-a3" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-general-q3" data-bs-parent="#ln7-faqs-general">
                        <div class="accordion-body">
                            Los paquetes de clases se pueden comprar en línea o en la recepción del estudio.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 4 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-general-q4">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-general-a4" aria-expanded="false"
                            aria-controls="ln7-faq-general-a4">
                            ¿Qué métodos de pago aceptan?
                        </button>
                    </h2>
                    <div id="ln7-faq-general-a4" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-general-q4" data-bs-parent="#ln7-faqs-general">
                        <div class="accordion-body">
                            Aceptamos pagos con tarjeta de crédito, débito y transferencias bancarias.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 5 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-general-q5">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-general-a5" aria-expanded="false"
                            aria-controls="ln7-faq-general-a5">
                            ¿Ofrecen clases para principiantes?
                        </button>
                    </h2>
                    <div id="ln7-faq-general-a5" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-general-q5" data-bs-parent="#ln7-faqs-general">
                        <div class="accordion-body">
                            Sí, ofrecemos una variedad de clases diseñadas específicamente para principiantes.
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Ubicación -->
        <div class="tab-pane fade" id="faq-category-3">
            <div class="accordion" id="ln7-faqs-ubicacion">
                <!-- Pregunta 1 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ubicacion-q1">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ubicacion-a1" aria-expanded="true"
                            aria-controls="ln7-faq-ubicacion-a1">
                            ¿Dónde están ubicados?
                        </button>
                    </h2>
                    <div id="ln7-faq-ubicacion-a1" class="accordion-collapse collapse show"
                        aria-labelledby="ln7-faq-ubicacion-q1" data-bs-parent="#ln7-faqs-ubicacion">
                        <div class="accordion-body">
                            Estamos ubicados en el centro de la ciudad, en la Calle 123, Edificio ABC.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 2 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ubicacion-q2">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ubicacion-a2" aria-expanded="false"
                            aria-controls="ln7-faq-ubicacion-a2">
                            ¿Tienen estacionamiento?
                        </button>
                    </h2>
                    <div id="ln7-faq-ubicacion-a2" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-ubicacion-q2" data-bs-parent="#ln7-faqs-ubicacion">
                        <div class="accordion-body">
                            Sí, contamos con un estacionamiento subterráneo para nuestros clientes.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 3 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ubicacion-q3">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ubicacion-a3" aria-expanded="false"
                            aria-controls="ln7-faq-ubicacion-a3">
                            ¿Cómo puedo llegar en transporte público?
                        </button>
                    </h2>
                    <div id="ln7-faq-ubicacion-a3" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-ubicacion-q3" data-bs-parent="#ln7-faqs-ubicacion">
                        <div class="accordion-body">
                            Puedes llegar en metro, autobús o taxi. La estación de metro más cercana es la Estación
                            XYZ.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 4 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ubicacion-q4">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ubicacion-a4" aria-expanded="false"
                            aria-controls="ln7-faq-ubicacion-a4">
                            ¿Tienen sucursales en otras ciudades?
                        </button>
                    </h2>
                    <div id="ln7-faq-ubicacion-a4" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-ubicacion-q4" data-bs-parent="#ln7-faqs-ubicacion">
                        <div class="accordion-body">
                            Por el momento, solo tenemos esta ubicación, pero estamos planeando expandirnos en el
                            futuro.
                        </div>
                    </div>
                </div>
                <!-- Pregunta 5 -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="ln7-faq-ubicacion-q5">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#ln7-faq-ubicacion-a5" aria-expanded="false"
                            aria-controls="ln7-faq-ubicacion-a5">
                            ¿Cuáles son sus horas de operación?
                        </button>
                    </h2>
                    <div id="ln7-faq-ubicacion-a5" class="accordion-collapse collapse"
                        aria-labelledby="ln7-faq-ubicacion-q5" data-bs-parent="#ln7-faqs-ubicacion">
                        <div class="accordion-body">
                            Estamos abiertos de lunes a viernes de 9:00 AM a 9:00 PM y los sábados de 10:00 AM a
                            6:00 PM.
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
      </section>
  
      <!-- Nuestros Precios -->
      <section id="pricing" class="py-5">
    <div class="container">
        <h2 class="text-center mb-4">Nuestros Planes</h2>
        <div class="row">
            <!-- Plan Básico -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title text-black">Plan Básico</h5>
                        <p class="card-text text-black">
                            Ideal para quienes están empezando. Incluye acceso limitado a funciones básicas:
                            <ul>
                                <li>Acceso a 5 clases mensuales</li>
                                <li>Soporte por correo electrónico</li>
                                <li>Acceso a la comunidad de usuarios</li>
                            </ul>
                        </p>
                        <a href="https://wa.me/3184217657?text=Estoy%20interesado%20en%20el%20Plan%20Básico" target="_blank" class="btn btn-primary">Elegir Plan</a>
                    </div>
                </div>
            </div>

            <!-- Plan Intermedio -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title text-black">Plan Intermedio</h5>
                        <p class="card-text text-black">
                            Perfecto para quienes desean más flexibilidad. Incluye funciones adicionales:
                            <ul>
                                <li>Acceso a 15 clases mensuales</li>
                                <li>Soporte por correo electrónico y chat</li>
                                <li>Acceso prioritario a eventos en vivo</li>
                            </ul>
                        </p>
                        <a href="https://wa.me/3184217657?text=Estoy%20interesado%20en%20el%20Plan%20Intermedio" target="_blank" class="btn btn-primary">Elegir Plan</a>
                    </div>
                </div>
            </div>

            <!-- Plan Avanzado -->
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title text-black">Plan Avanzado</h5>
                        <p class="card-text text-black">
                            Para los usuarios más exigentes. Incluye todas las funciones premium:
                            <ul>
                                <li>Acceso ilimitado a clases</li>
                                <li>Soporte personalizado 24/7</li>
                                <li>Acceso VIP a todos los eventos y recursos</li>
                            </ul>
                        </p>
                        <a href="https://wa.me/3184217657?text=Estoy%20interesado%20en%20el%20Plan%20Avanzado" target="_blank" class="btn btn-primary">Elegir Plan</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

  
      <!-- Modal para el video -->
      <div
        v-if="showVideoModal"
        class="modal fade show"
        id="about-us-video-modal"
        tabindex="-1"
        style="display: block"
        aria-modal="true"
        role="dialog"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-body">
              <video
                :src="videoSrc"
                controls
                autoplay
                class="w-100"
              ></video>
              <button
                type="button"
                class="btn-close position-absolute top-0 end-0 m-2"
                @click="closeVideoModal"
                aria-label="Close"
              ></button>
            </div>
          </div>
        </div>
      </div>
      <div
        v-if="showVideoModal"
        class="modal-backdrop fade show"
      ></div>

      <FooterTeachers />
    </div>
  </template>
  
  <script>

import FooterTeachers from '@/components/footers/FooterTeachers.vue';
import NavbarTeachers from '@/components/navbars/NavbarTeachers.vue';


  export default {
    name: 'HomeAdmin',
    components: {
        NavbarTeachers,
      FooterTeachers,
  },
    data() {
      return {
        currentImageIndex: 0,
        flippedCards: [false, false, false],
        showVideoModal: false,
        videoSrc: '../../../Public/Assets/img/Video/Dance.mp4',
        instructors: [
          {
            name: 'Luis',
            image: '../../../Public/Assets/img/logos/Afro_Luis.png',
            skills: 'Afro Dance',
            description: 'Experto en ritmos afro.',
            experience: '5 años',
            bestMove: 'Afro Beats',
            rating: '★★★★★'
          },
          {
            name: 'Chokola T',
            image: '../../../Public/Assets/img/logos/Chokola_T.png',
            skills: 'Perreo',
            description: 'Experto en ritmos afro.',
            experience: '8 años',
            bestMove: 'Movimiento de cadera',
            rating: '★★★★'
          },
          {
            name: 'Brandon',
            image: '../../../Public/Assets/img/logos/Brandon.png',
            skills: 'Electro Dance',
            description: 'Experto en ritmos Electro.',
            experience: '6 años',
            bestMove: 'handstand',
            rating: '★★'
          }
        ],
        danceImages: [
          {
            src: '../../../PUBLIC/ASSETS/img/logos/Bachata.png',
            alt: 'Bachata',
            title: 'Bachata',
            description: 'La bachata es un género musical bailable originario de la República Dominicana, que se ha popularizado en todo el mundo.'
          },
          {
            src: '../../../PUBLIC/ASSETS/img/logos/Salsa.png',
            alt: 'Salsa',
            title: 'Salsa',
            description: 'La salsa es un género bailable que combina ritmos afrocaribeños y se baila con mucho sabor.'
          },
          {
            src: '../../../PUBLIC/ASSETS/img/logos/AfroDance.png',
            alt: 'Afrobeat',
            title: 'Afrobeat',
            description: 'El Afrobeat combina música africana con influencias occidentales, caracterizado por ritmos fuertes y bailes enérgicos.'
          },
          {
            src: '../../../PUBLIC/ASSETS/img/logos/Champeta.jpg',
            alt: 'Champeta',
            title: 'Champeta',
            description: 'La champeta es un género musical de la costa caribeña colombiana con influencias africanas.'
          },
          {
            src: '../../../PUBLIC/ASSETS/img/logos/merengue.jpg',
            alt: 'Merengue',
            title: 'Merengue',
            description: 'El merengue es un ritmo alegre y rápido originario de la República Dominicana, muy popular en América Latina.'
          },
          {
            src: '../../../PUBLIC/ASSETS/img/logos/PolDance.png',
            alt: 'Pole Dance',
            title: 'Pole Dance',
            description: 'Pole Dance combina acrobacias y danza, ofreciendo una forma de ejercicio intensa y desafiante.'
          }
        ],
        faqs: {
          ln7: [
            {
              question: '¿Cómo puedo ver los horarios de clases?',
              answer: 'Puedes ver los horarios de clases en la sección de horarios de nuestra página web.'
            },
            // ... otras preguntas LN7
          ],
          general: [
            {
              question: '¿Cuáles son los horarios de Bureo Dance Studio?',
              answer: 'Los horarios varían según el día y el tipo de clase. Consulta nuestra página web para ver el horario completo.'
            },
            // ... otras preguntas generales
          ],
          location: [
            {
              question: '¿Dónde están ubicados?',
              answer: 'Estamos ubicados en el centro de la ciudad, en la Calle 123, Edificio ABC.'
            }
            // ... otras preguntas de ubicación
          ]
        },
        pricingPlans: [
          {
            name: 'Plan Básico',
            description: 'Ideal para quienes están empezando. Incluye acceso limitado a funciones básicas:',
            features: [
              'Acceso a 5 clases mensuales',
              'Soporte por correo electrónico',
              'Acceso a la comunidad de usuarios'
            ],
            whatsappLink: 'https://wa.me/3184217657?text=Estoy%20interesado%20en%20el%20Plan%20Básico'
          },
          // ... otros planes
        ]
      }
    },
    mounted() {
      this.initCarousel();
    },
    methods: {
      initCarousel() {
        setInterval(() => {
          this.currentImageIndex = (this.currentImageIndex + 1) % this.danceImages.length;
        }, 5000);
      },
      toggleCard(index) {
        this.flippedCards[index] = !this.flippedCards[index];
      },
      openVideoModal() {
        this.showVideoModal = true;
      },
      closeVideoModal() {
        this.showVideoModal = false;
      }
    }
  }

  </script>
  
  <style scoped>
  #about-us-section {
    padding: 80px 0;
  }
  
  .section-title-about {
    font-size: 2.5rem;
    margin-bottom: 20px;
  }
  
  .about-us-text {
    font-size: 1.2rem;
    line-height: 1.6;
  }
  
  .about-video-container {
    max-width: 100%;
    padding-top: 10px;
  }
  
  #team {
    background-color: #2b2b2b;
    padding: 50px 0;
  }
  
  .instructor-card {
    perspective: 1000px;
    position: relative;
    width: 100%;
    height: auto;
  }
  
  .instructor-img {
    width: 100%;
    padding-top: 150%;
    background-size: cover;
    background-position: center;
    border-radius: 10px;
    cursor: pointer;
    transition: transform 0.6s;
    transform-style: preserve-3d;
    position: relative;
  }
  
  .instructor-info {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.85);
    color: white;
    border-radius: 10px;
    backface-visibility: hidden;
    transform: rotateY(180deg);
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    opacity: 0;
  }
  
  .instructor-card:hover .instructor-img {
    transform: rotateY(180deg);
  }
  
  .instructor-card:hover .instructor-info {
    opacity: 1;
    transform: rotateY(0deg);
  }
  
  .star-rating {
    color: #ffd700;
    font-size: 1.5em;
  }
  
  .carousel-container {
    position: relative;
    width: 100%;
    height: 400px;
    overflow: hidden;
  }
  
  .carousel-images {
    position: absolute;
    top: 40%;
    left: 50%;
    width: 600px;
    height: 300px;
    transform: translate(-50%, -50%);
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  
  .carousel-images img {
    position: absolute;
    width: 240px;
    border-radius: 25px;
    height: 300px;
    opacity: 0.9;
    transition: transform 1s ease, opacity 1s ease;
  }
  
  .carousel-images img.active {
    z-index: 5;
    opacity: 1;
    transform: scale(1.2);
  }
  
  .carousel-text {
    position: absolute;
    bottom: -600px;
    left: 50%;
    transform: translateX(-50%);
    text-align: center;
    transition: bottom 1s ease;
  }
  
  </style>