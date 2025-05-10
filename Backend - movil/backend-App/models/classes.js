const db = require('../config/config');
const products = {};

products.findAll = (idUser, response) => {
    const sql = `SELECT c.id_clase, c.nombre, c.tipo_clase, c.duracion, c.generos, c.niveles, c.fecha FROM clases as c
    JOIN clases_usuarios as cu ON c.id_clase = cu.id_clase
    WHERE cu.id_usuario = ?;`
    db.query(sql, [idUser], (err, results) => {
        if (err) {
            console.log('Error al consultar: ', err);
            response(err, null);
        } else {
            const mappedResults = results.map(row => ({
                id: row.id_clase,
                name: row.nombre,
                classType: row.tipo_clase,
                duration: row.duracion,
                genres: row.generos,
                levels: row.niveles,
                date: row.fecha
            }));
            console.log('Clases consultadas: ', mappedResults);
            response(null, mappedResults);
        }
    })
};

products.findParameters = (idUser, parameters, response) => {
    const sql = `SELECT c.id_clase, c.nombre, c.tipo_clase, c.duracion, c.generos, c.niveles, c.fecha FROM clases as c
    JOIN clases_usuarios as cu ON c.id_clase = cu.id_clase
    WHERE cu.id_usuario = ? AND
        (
            c.nombre LIKE ? OR
            c.tipo_clase LIKE ? OR
            c.duracion LIKE ? OR
            c.generos LIKE ? OR
            c.niveles LIKE ?
        );`
    const searchValue = `%${parameters}%`;

    db.query(sql, [idUser, searchValue, searchValue, searchValue, searchValue, searchValue], (err, results) => {
        if(err) {
            console.log('Error al consultar: ', err);
            response(err, null);
        } else {
            const mappedResults = results.map(row => ({
                id: row.id_clase,
                name: row.nombre,
                classType: row.tipo_clase,
                duration: row.duracion,
                genres: row.generos,
                levels: row.niveles,
                date: row.fecha
            }));
            console.log('Clases consultadas: ', mappedResults);
            response(null, mappedResults)
        }
    })
};

products.create = (name, classType, duration, genres, levels, date, idUser, response) => {
    const sql1 = `INSERT INTO clases (nombre, tipo_clase, duracion, generos, niveles, fecha)
        VALUES (?, ?, ?, ?, ?, ?);`
    db.query(sql1, [name, classType, duration, genres, levels, date], (err, results) => {
        if(err) {
            console.log('Error al crear: ', results);
            response(err, results);
        }

        const sql2 = `INSERT INTO clases_usuarios (id_clase, id_usuario)
            VALUES (LAST_INSERT_ID(), ?);`
        db.query(sql2, [idUser], (err, results) => {
            if(err) {
                console.log('Error al programar la clase: ', err);
                response(err, null);
            } else {
                console.log('Clase programada: ', results);
                response(null, results)
            }
        })
    })
};

products.edit = (id, name, classType, duration, genres, levels, date, response) => {
    const sql = `UPDATE clases SET nombre = ?, tipo_clase = ?, duracion = ?, generos = ?, niveles = ?, fecha = ? WHERE id_clase = ?;`
    db.query(sql, [name, classType, duration, genres, levels, date, id], (err, results) => {
        if(err) {
            console.log('Error al actualizar la clase: ', err);
            response(err, null);
        } else {
            console.log('Clase actualizada: ', results);
            response(null, results);
        }
    })
};

products.drop = (id, response) => {
    const sql = `DELETE FROM clases WHERE id_clase = ?;`
    db.query(sql, [id], (err, results) => {
        if(err){
            console.log('Error al eliminar: ', err);
            response(err, null);
        } else {
            console.log('Clase eliminada: ', results);
            response(null, results);
        }
    })
};

module.exports = products;