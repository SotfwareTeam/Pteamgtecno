const db = require('../config/config');

const assistanceReport = {};

    //Create new assistance report
    assistanceReport.create = async (assistanceReport, result) => {
        const sql = `INSERT INTO reporte_asistencias (id_clase, id_usuario, asistencia) VALUES (?, ?, ?);`

        db.query(sql, [
            assistanceReport.idClass,
            assistanceReport.idUser,
            assistanceReport.assistance
        ],
        (err, res) => {
            if (err) {
                console.log('Error al crear reporte de asistencia: ', err);
                result(err, null);
            } else {
                console.log('Reporte de asistencia creado: ', {id: res.insertId, ...assistanceReport});
                result(null, {id: res.insertId, ...assistanceReport});
            }
        }
        )
    }

    //Get all assistance reports
    assistanceReport.findAll = (result) => {
        const sql = `SELECT * FROM reporte_asistencias;`
        
        db.query(sql, (err, assistanceReport) => {
                if (err) {
                    console.log('Error al consultar: ', err);
                    result(err, null);
                } else {
                    console.log('Reportes de asistencia consultados: ', assistanceReport);
                    result(null, assistanceReport);
                }
            }
        )
    }

    //Get assistance report
    assistanceReport.findId = (id, result) => {
        const sql = `SELECT * FROM reporte_asistencias WHERE id_reporte_asistencia = ?;`
        
        db.query(sql,
            [id], (err, assistanceReport) => {
                if (err) {
                    console.log('Error al consultar: ', err);
                    result(err, null);
                } else {
                    console.log('Reporte de asistencia consultada: ', assistanceReport);
                    result(null, assistanceReport);
                }
            }
        )
    }

    //Edit assistance report
    assistanceReport.edit = (assistanceReport, id, result) => {
        const sql = `UPDATE reporte_asistencias SET id_clase = ?, id_usuario = ?, asistencia = ? WHERE id_reporte_asistencia = ?;`
        
        db.query(sql, [
            assistanceReport.idClass,
            assistanceReport.idUser,
            assistanceReport.assistance,
            id
        ],
        (err, classes) => {
                if (err) {
                    console.log('Error al actualizar el reporte de asistencia: ', err);
                    result(err, null);
                } else {
                    console.log('Reporte de asistencia actualizado: ', classes[0]);
                    result(null, classes[0]);
                }
            }
        )
    };

    //Delete assistance report
    assistanceReport.drop = (id, result) => {
        const sql = `DELETE FROM reporte_asistencias WHERE id_reporte_asistencia = ?;`
        
        db.query(sql, [id], (err, assistanceReport) => {
                if (err) {
                    console.log('Error al eliminar el reporte de asistencia: ', err);
                    result(err, null);
                } else {
                    console.log('Reporte de asistencia eliminado: ', assistanceReport[0]);
                    result(null, assistanceReport[0]);
                }
            }
        )
    };

    module.exports = assistanceReport;