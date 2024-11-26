// Código JavaScript personalizado

document.addEventListener("DOMContentLoaded", function () {
    // Lógica para cargar los modales (Espacios, Profesores, Competencias)
    document.getElementById('modals-container').innerHTML = `
        <!-- Modal Espacios -->
        <div class="modal fade" id="espaciosModal" tabindex="-1" aria-labelledby="espaciosModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="espaciosModalLabel">Detalles de Espacios</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">Información de los espacios...</div>
                </div>
            </div>
        </div>
        
        <!-- Modal Profesores -->
        <div class="modal fade" id="profesoresModal" tabindex="-1" aria-labelledby="profesoresModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="profesoresModalLabel">Detalles de Profesores</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="../../../PUBLIC/ASSETS/img/user.jpg" class="card-img-top" alt="Profesor 1">
                                    <div class="card-body">
                                        <h5 class="card-title">Profesor 1</h5>
                                        <p class="card-text">Información del Profesor 1.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="../../../PUBLIC/ASSETS/img/user.jpg" class="card-img-top" alt="Profesor 2">
                                    <div class="card-body">
                                        <h5 class="card-title">Profesor 2</h5>
                                        <p class="card-text">Información del Profesor 2.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="../../../PUBLIC/ASSETS/img/user.jpg" class="card-img-top" alt="Profesor 3">
                                    <div class="card-body">
                                        <h5 class="card-title">Profesor 3</h5>
                                        <p class="card-text">Información del Profesor 3.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Modal Competencias -->
        <div class="modal fade" id="competenciasModal" tabindex="-1" aria-labelledby="competenciasModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="competenciasModalLabel">Detalles de Competencias</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="../../../PUBLIC/ASSETS/img/competencia1.jpg" class="card-img-top" alt="Competencia 1">
                                    <div class="card-body">
                                        <h5 class="card-title">Competencia 1</h5>
                                        <p class="card-text">Detalles de la competencia.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="../../../PUBLIC/ASSETS/img/competencia2.jpg" class="card-img-top" alt="Competencia 2">
                                    <div class="card-body">
                                        <h5 class="card-title">Competencia 2</h5>
                                        <p class="card-text">Detalles de la competencia.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="../../../PUBLIC/ASSETS/img/competencia3.jpg" class="card-img-top" alt="Competencia 3">
                                    <div class="card-body">
                                        <h5 class="card-title">Competencia 3</h5>
                                        <p class="card-text">Detalles de la competencia.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
});
