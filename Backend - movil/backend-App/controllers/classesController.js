const products = require('../models/classes');
module.exports = {
    getClasses(req, res) {
        const idUser = parseInt(req.user.id);
        products.findAll(idUser, (err, results) => {
            if(err) throw err;
            res.json(results);
        });
    },

    getClassByParameters(req, res) {
        const idUser = parseInt(req.user.id);
        const { parameters } = req.params;
        products.findParameters(idUser, parameters, (err, results) => {
            if(err) throw err;
            res.json(results);
            //res.json(results[0]);
        })
    },

    createClass(req, res) {
        const idUser = parseInt(req.user.id);
        const { name, classType, duration, genres, levels, date } = req.body;
        products.create(name, classType, duration, genres, levels, date, idUser, (err, results) => {
            if(err) throw err;
            res.json({ id: results.insertId, name, classType, duration, genres, levels, date, idUser });
        })
    }, 

    updateClass(req, res) {
        const { id } = req.params;
        const { name, classType, duration, genres, levels, date } = req.body;
        products.edit(id, name, classType, duration, genres, levels, date, (err, results) => {
            if(err) throw err;
            res.json({ id, name, classType, duration, genres, levels, date})
        })
    },

    deleteClass(req, res) {
        const { id } = req.params;
        products.drop(id, (err, results) => {
            if(err) throw err;
            res.json({message: "Eliminado id: " + id});
        })
    }
};