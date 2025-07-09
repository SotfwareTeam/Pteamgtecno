import axios from 'axios';
import { API_URL } from './api';

export const login = async (loginData) => {
  try {
    const response = await axios.post(`${API_URL}Login.php`, loginData, {
      headers: { 'Content-Type': 'application/json' }
    });
    return response.data;
  } catch (error) {
    console.error('Error en login:', error);
    throw error;
  }
};
