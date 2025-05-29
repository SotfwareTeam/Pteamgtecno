<template>
    <body>
        <NavBar />
        <div class="content">
            <section id="about-us-section" class="py-5 aboutUs">
                <div class="col-md-6">
                    <h2 class="section-title-about">Quiénes Somos</h2>
                    <p class="about-us-text">Somos una academia dedicada a enseñar los mejores estilos de baile,
                        brindando una experiencia única para todos los niveles de habilidad. Nuestro equipo de
                        instructores profesionales está comprometido con el éxito y el crecimiento personal de cada
                        estudiante.</p>
                    <a href="https://www.instagram.com/ln7dancestudio_/" class="conocenos">Conócenos.</a>
                </div>
                <div class="about-video-container">
                    <video id="aboutUsVideoPlayer" class="about-us-video img-fluid"
                    src="../../public/video/Dance.mp4" controls></video>
                </div>
            </section>

            <section id="team" class="py-5 team">
                <h2 class="text-center text-white ourteam">Nuestro Equipo</h2>
                <div class="team-cards">
                    <div class="col-md-4 mb-3">
                        <div class="instructor-card">
                            <div class="instructor-img">
                                <img src="../../public/images/Afro_luis.png" alt="">
                            </div>
                            <div class="instructor-info">
                                <h3>Luis</h3>
                                <p><strong>Habilidades:</strong> Afro Dance</p>
                                <p><strong>Descripción:</strong> Experto en ritmos afro.</p>
                                <p><strong>Experiencia:</strong> 5 años.</p>
                                <p><strong>Mejor Movimiento:</strong> Afro Beats.</p>
                                <p><strong>Popularidad:</strong></p>
                                <div class="star-rating">★★★★★</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="instructor-card">
                            <div class="instructor-img">
                                <img src="../../public/images/Chokola_t.png" alt="">
                            </div>
                            <div class="instructor-info">
                                <h3>Chokola T</h3>
                                <p><strong>Habilidades:</strong> Perreo</p>
                                <p><strong>Descripción:</strong> Experto en ritmos afro.</p>
                                <p><strong>Experiencia:</strong> 8 años.</p>
                                <p><strong>Mejor Movimiento:</strong> Movimiento de cadera.</p>
                                <p><strong>Popularidad:</strong></p>
                                <div class="star-rating">★★★★</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="instructor-card">
                            <div class="instructor-img">
                                <img src="../../public/images/Brandon.png" alt="">
                            </div>
                            <div class="instructor-info">
                                <h3>Brandon</h3>
                                <p><strong>Habilidades:</strong> Electro Dance</p>
                                <p><strong>Descripción:</strong> Experto en ritmos Electro.</p>
                                <p><strong>Experiencia:</strong> 6 años.</p>
                                <p><strong>Mejor Movimiento:</strong> handstand.</p>
                                <p><strong>Popularidad:</strong></p>
                                <div class="star-rating">★★</div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="classes">
                <h2 class="text-center text-white">Nuestras Clases</h2>
                <p class="text-center text-white">Ven y disfruta de nuestras clases presenciales grupales, personalizadas,
                    semipersonalizadas y recuerda que también tenemos clases a domicilio!</p>
                
                <div id="clasesCarousel" class="carousel-container">
                    <div class="carousel-images">
                        <img src="../../public/images/Bachata.png" alt="Bachata">
                        <img src="../../public/images/Salsa.png" alt="Salsa">
                        <img src="../../public/images/AfroDance.png" alt="Afrobeat">
                        <img src="../../public/images/Champeta.jpg" alt="Champeta">
                        <img src="../../public/images/merengue.jpg" alt="Merengue">
                        <img src="../../public/images/PolDance.png" alt="Pole Dance">
                    </div>
                </div>
            </section>
        </div>
        <FooterBar />
    </body>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
import FooterBar from "@/components/FooterBar.vue"
export default {
    components: {
        NavBar,
        FooterBar,
    }
}
</script>

<style scoped>
    @import '../assets/styles/Main.css';

    body{
        background: linear-gradient(317deg, #5f2f9a, #076a81, #b269cd);
        display: flex;
        flex-direction: column;
        overflow: hidden;
    }

    .content{
        display: flex;
        flex-direction: column;
        
    }


/*//////////////////////////////////////////////////////////////*/
    .aboutUs{
        padding: 120px 40px 30px;
        gap: 30px;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }

    .about-video-container {
        max-width: 100%;
    }

    .section-title-about {
        font-size: 2.5rem;
        margin-bottom: 20px;
    }

    .about-us-text {
        font-size: 1.2rem;
        line-height: 1.6;
    }

    .conocenos{
        color: black;
        opacity: 0.7;
        transition: 0.3s;
    }

    .conocenos:hover{
        color: rgb(255, 255, 255);
        opacity: 1;
    }


/*//////////////////////////////////////////////////////////////*/
    .team{
        width: 90%;
        margin: 5%;
        border-radius: 30px;
        padding-bottom: 50px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: rgb(255, 255, 255);
        background-color: #2b2b2b;
        background: linear-gradient(180deg, #2b2b2b, #076a81, #2b2b2b);
    }

    .ourteam{
        padding: 35px;
        font-size: 2rem;
        justify-content: space-between;
    }

    .team-cards{
        justify-content: center;
        gap: 30px;
        width: 90%;
        display: flex;
        flex-direction: row;
    }

    .instructor-card {
        perspective: 1000px;
        height: 200px;
        position: relative;
        width: 100%;
        height: auto;

    }
    
    .instructor-img img {
        width: 100%;
        height: 400px;
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

    .instructor-card:hover .instructor-img img{
        transition: 0.5s;
    }

    .instructor-card:hover .instructor-info {
        opacity: 1;
        transform: scale(1);
    }

    .instructor-card .star-rating {
        color: #ffd700;
        font-size: 1.5em;
    }


/*//////////////////////////////////////////////////////////////*/
    .classes{
        width: 90%;
        margin: 0 5%;
        text-align: center;
        padding-bottom: 50px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: rgb(255, 255, 255);
    }

    .classes h2{
        padding: 35px;
        font-size: 2rem;
    }

    .classes p{
        font-size: 1rem;
        font-weight: 500;
        color: #000000;
    }

    .carousel-container {
        position: relative;
        width: 100%;
        height: 400px;
    }

    .carousel-images {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 90%;
        height: 300px;
        transform: translate(-50%, -50%);
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    .carousel-images img {
        position: absolute;
        width: 90%;
        border-radius: 25px;
        height: 100%;
        opacity: 0.9;
        transition: transform 1s ease, opacity 1s ease;
    }

    .carousel-images img.active {
        z-index: 5;
        opacity: 1;
        transform: scale(1.2);
    }

    @media(max-width: 900px){
        .aboutUs {
            flex-direction: column;
            text-align: center;
            padding-top: 60px;
        }

        .about-video-container {
            width: 95%;
        }

        .about-video-container video {
            width: 100%;
        }

        .team-cards {
            flex-direction: column;
        }
    }

</style>