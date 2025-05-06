const assistanceReport = require('../models/assistanceReport');

module.exports = {
    
    //Create assistance report
    createAssistanceReport(req, res){
        const assistanceReportData = req.body;
        assistanceReport.create(assistanceReportData, (err, data) => {
            if (err){
                return res.status(501).json({
                    success: false,
                    message: 'Error al crear el reporte de asistencia',
                    error: err
                });
            }
            return res.status(201).json({
                success: true,
                message: 'Reporte de asistencia creado',
                data: data
            });
        });
    },

    //Get all assistance reports
    getAllAssistanceReports(req, res){
            assistanceReport.findAll(async(err, data)=> {
                if(err){
                    return res.status(501).json({
                        success: false,
                        message: 'No se encontraron reportes de asistencia',
                        error: err
                    });
                }
                return res.status(201).json({
                    success: true,
                    message: 'Reportes de asistencia encontrados',
                    data: data
                });
            })
        },

    //Get assistance report
    getAssistanceReport(req, res){        
        const id = parseInt(req.params.id);
        assistanceReport.findId(id, async(err, data)=> {
            if(err){
                return res.status(501).json({
                    success: false,
                    message: 'No hay un reporte de asistencia con ese id',
                    error: err
                });
            }
            return res.status(201).json({
                success: true,
                message: 'Reporte de asistencia encontrado',
                data: data
            });
        })
    },

    updateAssistanceReport(req, res){
        const id = parseInt(req.params.id);
        const assistanceReportData = req.body;
        assistanceReport.edit(assistanceReportData, id, (err, data) => {
            if (err){
                return res.status(501).json({
                    success: false,
                    message: 'Error al actualizar el reporte de asistencia',
                    error: err
                });
            }
            return res.status(201).json({
                success: true,
                message: 'Reporte de asistencia actualizado',
                data: data
            });
        })
    },  

    deleteAssistanceReport(req, res){
        const id = parseInt(req.params.id);
        assistanceReport.drop(id, (err, data) => {
            if (err){
                return res.status(501).json({
                    success: false,
                    message: 'Error al eliminar el reporte de asistencia',
                    error: err
                });
            }
            return res.status(201).json({
                success: true,
                message: 'Reporte de asistencia eliminado',
                data: data
            });
        })
    }
};