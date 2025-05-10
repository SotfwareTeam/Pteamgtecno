const classesController = require('../controllers/classesController');
const authMiddleware = require('../config/authMiddleware.js');

module.exports = (app) => {
    app.get('/api/classes', authMiddleware, classesController.getClasses);
    app.get(`/api/classes/search/:parameters`, authMiddleware, classesController.getClassByParameters);
    app.post('/api/classes', authMiddleware, classesController.createClass);
    app.put(`/api/classes/:id`, classesController.updateClass);
    app.delete(`/api/classes/:id`, classesController.deleteClass);
}