
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

// JavaScript For The Gender Dance /////////////////////////////////////////////////////////document.addEventListener("DOMContentLoaded", function () {
document.addEventListener("DOMContentLoaded", function () {
    const images = document.querySelectorAll('.carousel-images img');
    const titles = ["Bachata", "Salsa", "Afrobeat", "Champeta", "Merengue", "Pole Dance"];
    const descriptions = [
        "La bachata es un género musical bailable originario de la República Dominicana, que se ha popularizado en todo el mundo.",
        "La salsa es un género bailable que combina ritmos afrocaribeños y se baila con mucho sabor.",
        "El Afrobeat combina música africana con influencias occidentales, caracterizado por ritmos fuertes y bailes enérgicos.",
        "La champeta es un género musical de la costa caribeña colombiana con influencias africanas.",
        "El merengue es un ritmo alegre y rápido originario de la República Dominicana, muy popular en América Latina.",
        "Pole Dance combina acrobacias y danza, ofreciendo una forma de ejercicio intensa y desafiante."
    ];

    let currentIndex = 0;

    function updateCarousel() {
        images.forEach((img, index) => {
            img.classList.remove('active');
            img.style.transform = `rotateY(${(index - currentIndex) * 60}deg) translateZ(300px)`;
        });

        images[currentIndex].classList.add('active');
        document.getElementById("carouselTitle").textContent = titles[currentIndex];
        document.getElementById("carouselDescription").textContent = descriptions[currentIndex];
    }

    function rotateCarousel() {
        currentIndex = (currentIndex + 1) % images.length;
        updateCarousel();
    }

    updateCarousel();
    setInterval(rotateCarousel, 5000);
});

// JavaScript For The PQR /////////////////////////////////////////////////////////

