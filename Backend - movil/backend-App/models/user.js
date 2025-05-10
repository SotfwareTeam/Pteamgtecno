const db = require('../config/config');
const bcrypt = require('bcryptjs');

const user = {};

    user.findId = (id, result) => {
        const sql = `SELECT id_usuario, correo, nombre, apellido, contraseña FROM usuarios WHERE id_usuario = ?;`
        
        db.query(sql, 
            [id], (err, user) => {
                if (err) {
                    console.log('Error al consultar: ', err);
                    result(err, null);
                } else {
                    console.log('Usuario Consultado: ', user[0]);
                    result(null, user[0]);
                }
            }
        )
    }

    user.findByEmail = (email, result) => {
        const sql = `SELECT id_usuario, correo, nombre, apellido, contraseña FROM usuarios WHERE correo = ?;`
        
        db.query(sql, 
            [email], (err, user) => {
                if (err) {
                    console.log('Error al consultar: ', err);
                    result(err, null);
                } else {
                    console.log('Usuario Consultado: ', user[0]);
                    result(null, user[0]);
                }
            }
        )
    }

    user.create = async (user, result) => {
        const hash = await bcrypt.hash(user.password, 10);
        const sql = `INSERT INTO usuarios (nombre, apellido, tipo_documento, numero_documento, telefono, sexo, fecha_nacimiento, correo, contraseña, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);`

        db.query(sql, [
            user.name,
            user.lastname,
            user.idType,
            user.idNumber,
            user.phone,
            user.gender,
            user.bornDate,
            user.email,
            hash,
            new Date()
        ],
        (err, res) => {
            if (err) {
                console.log('Error al crear usuario: ', err);
                result(err, null);
            } else {
                console.log('Usuario creado: ', {id: res.insertId, ...user});
                result(null, {id: res.insertId, ...user});
            }
        }
        )
    };

module.exports = user;
