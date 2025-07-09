import axios from "axios";

export const API_URL = 'http://localhost/backend/';

const api = axios.create({
    baseURL: API_URL,
    headers: {
        'Content-Type': 'application/json'
    }
});

api.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status ===401){
            localStorage.clear();
            window.location.href = '/'
        }
        return Promise.reject(error);
    }
);