const express = require('express');
const passport = require('passport');
const app = express();
const http = require('http');
const server = http.createServer(app);
const logger = require('morgan');
const cors = require('cors');

//Importar las rutas
const userRoutes = require('./routes/userRoutes');
const classesRoutes = require('./routes/classesRoutes')
const assistanceReportRoutes = require('./routes/assistanceReportRoutes')

const ip = '192.168.1.11'





const port = process.env.PORT || 3000;
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extend: true}));
app.use(cors());
app.use(passport.initialize());
app.use(passport.session());

require('./config/passport')(passport);
app.disable('x-powered-by');


app.set('port', port);

//Llamada de rutas
userRoutes(app);
classesRoutes(app);
assistanceReportRoutes(app);

//Dirección ip V4 de la máquina consultar ipconfig
server.listen(3000, ip || 'localhost',
function(){
    console.log('App NodeJs: ' + process.pid + ' ejecutando en ' + server.address().address + ':' + server.address().port)
});

app.get('/', (req, res) => {
    res.send('Ruta raiz de Backend');
});

app.get('/test', (req, res) => {
    res.send('Ruta test');
});

//Error Handler
app.use((err, req, res, next) => {
    console.log(err);
    res.status(err.status || 500).send(err.stack);
});