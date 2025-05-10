const assistanceReportController = require('../controllers/assistanceReportController')

module.exports = (app) => {
    //Program new class
    app.post('/api/assistanceReport/', assistanceReportController.createAssistanceReport);
    //Get all classes
    app.get('/api/assistanceReport/', assistanceReportController.getAllAssistanceReports);
    //Get class
    app.get('/api/assistanceReport/:id', assistanceReportController.getAssistanceReport);
    //Update class
    app.put('/api/assistanceReport/:id', assistanceReportController.updateAssistanceReport);
    //Delete class
    app.delete('/api/assistanceReport/:id', assistanceReportController.deleteAssistanceReport);
}