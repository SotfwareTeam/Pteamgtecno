import { User } from '../../domain/entities/user';
import { AuthRepository } from '../../domain/repositories/authRepository';
import { ApiDelivery } from "../source/remote/api/ApiDelivery";
import { Axios, AxiosError } from "axios";
import { ResponseApiDelivery } from "../source/remote/models/responseApiDelivery";
import AsyncStorage from "@react-native-async-storage/async-storage";

export class AuthRepositoryImpl implements AuthRepository {
    async register(user: User) {
        try {
            const response = await
                ApiDelivery.post<ResponseApiDelivery>('/users/create', user);
            return Promise.resolve(response.data);
        } catch (error) {
            let e = (error as AxiosError);
            console.log('Error ' + JSON.stringify(e.response?.data));
            const apiError: ResponseApiDelivery = JSON.parse(JSON.stringify(e.response?.data));
            return Promise.resolve(apiError);
        }
    }
    async login(email: string, password: string): Promise<ResponseApiDelivery> {
        try {
            const response = await
                ApiDelivery.post<ResponseApiDelivery>('/users/login', {
                    email: email, password: password
                });
            if(response.data.success){
                await AsyncStorage.setItem('user', JSON.stringify(response.data.data));
            }
            return Promise.resolve(response.data);
        } catch (error) {
            let e = (error as AxiosError);
            console.log('Error ' + JSON.stringify(e.response?.data));
            const apiError: ResponseApiDelivery =
                JSON.parse(JSON.stringify(e.response?.data));
            return Promise.resolve(apiError);
        }
    }
}