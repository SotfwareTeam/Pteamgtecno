import { UserLocalRepositoryImp } from "../../../data/repositories/UserLocalRepository";
import AsyncStorage from "@react-native-async-storage/async-storage";

const { remove } = new UserLocalRepositoryImp();

export const RemoveUserLocalUseCase = async () => {
    await AsyncStorage.removeItem('user');
    return await remove();
}