const jwt = require('jsonwebtoken');
const key = require('./key');

function authMiddleware(req, res, next) {
    const authHeader = req.headers.authorization;

    if (!authHeader) {
        return res.status(401).json({ success: false, message: 'Token no proporcionado' });
    }

    const token = authHeader.split(' ')[1];

    jwt.verify(token, key.secretOrKey, (err, decoded) => {
        if (err) {
            return res.status(403).json({ success: false, message: 'Token inválido' });
        }
        req.user = decoded;
        next();
    });
}

module.exports = authMiddleware;