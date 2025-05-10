import React, { useEffect } from 'react'
import { Text, View, Image, ToastAndroid, ScrollView, TouchableOpacity } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { RoundedButton } from '../../components/RoundedButton';
import { StackScreenProps } from '@react-navigation/stack';
import { RootStackParamList } from '../../../../App';
import { CustomTextInput } from '../../components/CustomTextInput';
import useViewModel from './viewModel';
import styles from './styles';

interface Props extends StackScreenProps<RootStackParamList, 'RegisterScreen'>{};

export const RegisterScreen = ({navigation, route}: Props) => {
    const {name, lastname, idNumber, idType, phone, gender, bornDate, email, password, confirmPassword, errorMessage, onChange, register} = useViewModel();
    //Validación de variable asignada
    useEffect(() => {
      if(errorMessage !== ''){
        ToastAndroid.show(errorMessage, ToastAndroid.LONG)
      }
    }, [errorMessage]);
    
    return (
        <View style={styles.container}>
          <LinearGradient style={styles.linearGradient}
            colors={['#b269cd', '#076a81', '#5f2f9a']}
            start={{x: 0, y: 0.1}} end={{x: 1, y: 0.25}}>
          </LinearGradient>
          <View style={styles.logoContainer}>
            <Image source={require('../../../../assets/logo.png')}
            style={styles.logoImage}>
            </Image>
            <Text style={styles.logoText}>¡Hola!</Text>
            <Text style={styles.logoSubText}>Registrate con tus datos personales para usar todas las funcionalidades del sitio</Text>
          </View>
          <View style={styles.form}>
            <ScrollView style={styles.scroll}>
              <Text style={styles.formText}>Registrarse</Text>
              <CustomTextInput
              iconName='user'
              placeholder='Nombres'
              keyboardType='default'
              property='name'
              onChangeText={onChange}
              value={name}>
              </CustomTextInput>
              <CustomTextInput
              iconName='user'
              placeholder='Apellidos'
              keyboardType='default'
              property='lastname'
              onChangeText={onChange}
              value={lastname}>
              </CustomTextInput>
              <CustomTextInput
              iconName='id-card'
              placeholder='Tipo de Documento'
              keyboardType='numeric'
              property='idType'
              onChangeText={onChange}
              value={idType}>
              </CustomTextInput>
              <CustomTextInput
              iconName='id-card'
              placeholder='Número de Identificación'
              keyboardType='numeric'
              property='idNumber'
              onChangeText={onChange}
              value={idNumber}>
              </CustomTextInput>
              <CustomTextInput
              iconName='phone'
              placeholder='Teléfono'
              keyboardType='numeric'
              property='phone'
              onChangeText={onChange}
              value={phone}>
              </CustomTextInput>
              <CustomTextInput
              iconName='venus-mars'
              placeholder='Género'
              keyboardType='default'
              property='gender'
              onChangeText={onChange}
              value={gender}>
              </CustomTextInput>
              <CustomTextInput
              iconName='calendar'
              placeholder='Fecha de nacimiento'
              keyboardType='numeric'
              property='bornDate'
              onChangeText={onChange}
              value={bornDate}>
              </CustomTextInput>
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
              <CustomTextInput
              iconName='lock'
              placeholder='Confirmar Contraseña'
              keyboardType='default'
              property='confirmPassword'
              onChangeText={onChange}
              value={confirmPassword}
              secureTextEntry={true}>
              </CustomTextInput>
            </ScrollView>
            <View style={{ marginTop: 30 }}>
              <RoundedButton text='Registrarse' onPress={ () => register()}>
              </RoundedButton>
            </View>
            <View style={styles.formRegister}>
              <TouchableOpacity onPress={() => navigation.navigate('HomeScreen')}>
                <Text style={styles.formRegisterText}>Iniciar Sesión</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      );
  }
