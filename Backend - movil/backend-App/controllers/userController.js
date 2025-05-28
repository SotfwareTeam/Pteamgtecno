const user = require('../models/user');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const key = require('../config/key')

module.exports = {
    login (req, res){
        const email = req.body.email;
        const password = req.body.password;

        user.findByEmail(email, async(err, myUser)=> {
            if(err){
                return res.status(501).json({
                    success: false,
                    message: 'Error al buscar usuario',
                    error: err
                });
            }
            if(!myUser){//Cliente no autorizado
                return res.status(401).json({
                    success: false,
                    message: 'El email no está registrado'
                });
            }

            const isPasswordValid = await bcrypt.compare(password, myUser.contraseña);
            if(isPasswordValid){
                const token = jwt.sign({id: myUser.id_usuario, correo: myUser.email}, key.secretOrKey, {})

                const data = {
                    id: myUser.id_usuario,
                    name: myUser.nombre,
                    lastname: myUser.apellido,
                    idType: myUser.tipo_documento,
                    idNumber: myUser.numero_documento,
                    phone: myUser.telefono,
                    gender: myUser.sexo,
                    bornDate: myUser.fecha_nacimiento,
                    email: myUser.correo,
                    session_token: `JWT ${token}`
                }
                return res.status(201).json({
                    success: true,
                    message: 'Usuario autenticado',
                    data: data //Id del usuario
                });
            } else {
                return res.status(401).json({
                    success: false,
                    message: 'Contraseña incorrecta'
                }
                );
            }
        })
    },

    register(req, res){
        const userData = req.body; //Datos del cliente
        user.create(userData, (err, data) => {
            if (err){
                return res.status(501).json({
                    success: false,
                    message: 'Error al crear usuario',
                    error: err
                });
            }
            return res.status(201).json({
                success: true,
                message: 'Usuario creado',
                data: data //Id del usuario
            });
        });
    }
};