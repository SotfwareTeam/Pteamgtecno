import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import { HomeScreen } from './src/presentation/views/home/home';
import { RegisterScreen } from './src/presentation/views/register/register';
import ClassesScreen from './src/presentation/views/classesViews/classes';

export type RootStackParamList = {
  HomeScreen: undefined;
  RegisterScreen: undefined;
  ClassesScreen: undefined;
};
const Stack = createNativeStackNavigator <RootStackParamList>();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
        <Stack.Screen
          name="HomeScreen"
          component={HomeScreen}
        />
        <Stack.Screen
          name="RegisterScreen"
          component={RegisterScreen}
        />
        <Stack.Screen
          name="ClassesScreen"
          component={ClassesScreen}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;