export interface User{
    id?: number,
    name: string,
    lastname: string,
    idNumber: string,
    idType: string,
    phone: string,
    gender: string,
    bornDate: string,
    email: string,
    password: string,
    confirmPassword: string;
    session_token?: string;
}