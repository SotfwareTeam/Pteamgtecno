// routes/profesorRoutes.js
const express = require('express');
const router = express.Router();
const db = require('../config/db');

router.get('/profesores', (req, res) => {
  const sql = 'SELECT nombre, descripcion, imagen FROM profesores';
  db.query(sql, (err, results) => {
    if (err) return res.status(500).json({ error: 'Error al obtener profesores' });
    res.json(results);
  });
});

module.exports = router;
