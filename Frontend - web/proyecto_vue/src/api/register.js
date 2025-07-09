import axios from 'axios';
import { API_URL } from './api';

export const register = async (registerData) => {
  try {
    const response = await axios.post(`${API_URL}Register.php`, registerData, {
      headers: { 'Content-Type': 'application/json' }
    });
    return response.data;
  } catch (error) {
    console.error('Error en register:', error);
    throw error;
  }
};
