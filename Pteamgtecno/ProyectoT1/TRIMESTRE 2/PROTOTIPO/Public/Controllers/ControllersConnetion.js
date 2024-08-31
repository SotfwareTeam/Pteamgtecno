
// JavaScript For The about us section 

// Abre el video en un modal al hacer doble click
document.addEventListener('DOMContentLoaded', function () {
    const videoPlayer = document.getElementById('aboutUsVideoPlayer');

    videoPlayer.addEventListener('dblclick', function () {
        const videoSrc = videoPlayer.getAttribute('src');
        const modalHtml = `
            <div class="modal fade" id="about-us-video-modal" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <video src="${videoSrc}" controls autoplay class="w-100"></video>
                        </div>
                    </div>
                </div>
            </div>
        `;
        document.body.insertAdjacentHTML('beforeend', modalHtml);
        const videoModal = new bootstrap.Modal(document.getElementById('about-us-video-modal'));
        videoModal.show();

        // Remueve el modal cuando se cierra
        document.getElementById('about-us-video-modal').addEventListener('hidden.bs.modal', function () {
            document.getElementById('about-us-video-modal').remove();
        });
    });
});



// JavaScript For The Our Team /////////////////////////////////////////////////////////


document.querySelectorAll('.instructor-img').forEach(image => {
    image.addEventListener('dblclick', function () {
        const parentCard = this.closest('.instructor-card');
        parentCard.classList.toggle('flip');
    });
});

// JavaScript For The Gender Dance /////////////////////////////////////////////////////////
document.addEventListener('DOMContentLoaded', function () {
    var carouselElement = document.querySelector('#danceCarousel');
    var carouselInfo = document.querySelector('#carouselInfo');

    // Actualizar el texto de la información cuando el carrusel cambia
    carouselElement.addEventListener('slid.bs.carousel', function () {
        var activeItem = document.querySelector('.carousel-item.active');
        var infoText = activeItem.getAttribute('data-info');
        carouselInfo.textContent = infoText;
    });

    // Detener la rotación del carrusel cuando el cursor esté encima
    var carouselItems = document.querySelectorAll('.carousel-item');
    carouselItems.forEach(function (item) {
        item.addEventListener('mouseenter', function () {
            var carousel = bootstrap.Carousel.getInstance(carouselElement);
            carousel.pause();
        });
        item.addEventListener('mouseleave', function () {
            var carousel = bootstrap.Carousel.getInstance(carouselElement);
            carousel.cycle();
        });
    });

    // Iniciar el carrusel con la actualización automática del texto
    var carouselInstance = new bootstrap.Carousel(carouselElement, {
        interval: 5000, // Cambiar imagen cada 5 segundos
        ride: 'carousel'
    });

    // Forzar actualización del texto en la primera carga
    var activeItem = document.querySelector('.carousel-item.active');
    var infoText = activeItem.getAttribute('data-info');
    carouselInfo.textContent = infoText;
});

// JavaScript For The PQR /////////////////////////////////////////////////////////

