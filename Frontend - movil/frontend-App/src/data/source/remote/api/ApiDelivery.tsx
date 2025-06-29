import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";


const ApiDelivery = axios.create({
    baseURL: 'http://192.168.1.11:3000/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const getClasses = async () => {
    const session = await AsyncStorage.getItem('user');
    const user = session ? JSON.parse(session) : null;
    //NORMALIZAR API

    const response = await ApiDelivery.get('/classes', {
        headers: {
            Authorization: user.session_token ?? ''
        }
    });
    return response.data;
};

export const getClassByParameters = async (parameters) => {
    const session = await AsyncStorage.getItem('user');
    const user = session ? JSON.parse(session) : null;

    const response = await ApiDelivery.get(`/classes/search/${parameters}`, {
        headers: {
            Authorization: user.session_token ?? ''
        }
    });
    return response.data;
};

export const createClass = async (clase) => {
    const session = await AsyncStorage.getItem('user');
    const user = session ? JSON.parse(session) : null;
    
    const response = await ApiDelivery.post('/classes', clase, {
        headers: {
            Authorization: user.session_token ?? ''
        }
    });
    return response.data;
};

export const updateClass = async (id, clase) => {
    const response = await ApiDelivery.put(`/classes/${id}`, clase);
    return response.data;
};

export const deleteClass = async (id) => {
    const response = await ApiDelivery.delete(`/classes/${id}`);
    return response.data;
};
export {ApiDelivery};