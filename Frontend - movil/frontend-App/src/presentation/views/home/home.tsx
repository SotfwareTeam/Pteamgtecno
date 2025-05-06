import React, { useEffect } from 'react'
import { Text, View, Image, ToastAndroid, TouchableOpacity } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { RoundedButton } from '../../components/RoundedButton';
import { CustomTextInput } from '../../components/CustomTextInput';
import { StackScreenProps } from '@react-navigation/stack';
import { RootStackParamList } from '../../../../App';
import useViewModel from './viewModel';
import styles from './styles';

interface Props extends StackScreenProps<RootStackParamList, 'HomeScreen'>{};

export const HomeScreen = ({navigation, route}: Props) => {
  const {email, password, errorMessage, user, onChange, login} = useViewModel();

  //const navigation = useNavigation<StackNavigationProp<RootStackParamList>>();

  //Validación de variable asignada
  useEffect(() => {
    if(errorMessage !== ''){
      ToastAndroid.show(errorMessage, ToastAndroid.LONG)
    }
  }, [errorMessage]);

  useEffect(() => {
    if(user?.id !== null && user?.id !== undefined){
      navigation.replace('ClassesScreen');
    }
  }, [user]);
  
  return (
      <View style={styles.container}>
        <LinearGradient style={styles.linearGradient}
          colors={['#b269cd', '#076a81', '#5f2f9a']}
          start={{x: 0, y: 0.1}} end={{x: 1, y: 0.45}}>
        </LinearGradient>
        <View style={styles.logoContainer}>
          <Image source={require('../../../../assets/logo.png')}
          style={styles.logoImage}>
          </Image>
          <Text style={styles.logoText}>Bienvenido</Text>
          <Text style={styles.logoSubText}>Ingresa tus datos personales para usar todas las funcionalidades del sitio</Text>
        </View>
        <View style={styles.form}>
          <Text style={styles.formText}>Iniciar Sesión</Text>
          <CustomTextInput
          iconName='envelope'
          placeholder='Correo Electrónico'
          keyboardType='email-address'
          property='email'
          onChangeText={onChange}
          value={email}>
          </CustomTextInput>
          <CustomTextInput
          iconName='lock'
          placeholder='Contraseña'
          keyboardType='default'
          property='password'
          onChangeText={onChange}
          value={password}
          secureTextEntry={true}>
          </CustomTextInput>
          <View style={{ marginTop: 30 }}>
            <RoundedButton text='Iniciar Sesión' onPress={ () => login()}>
            </RoundedButton>
          </View>
          <View style={styles.formRegister}>
            <Text>¿No tienes cuenta?</Text>
            <TouchableOpacity onPress={() => navigation.navigate('RegisterScreen')}>
              <Text style={styles.formRegisterText}>Registrate</Text>
            </TouchableOpacity>
          </View>
        </View>
      </View>
    );
}  