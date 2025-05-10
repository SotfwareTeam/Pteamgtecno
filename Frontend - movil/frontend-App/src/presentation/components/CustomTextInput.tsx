import React from 'react'
import { View, Image, TextInput,  StyleSheet, KeyboardType} from 'react-native'
import Icon from 'react-native-vector-icons/FontAwesome5'

interface Props {
    iconName: any,
    placeholder: string,
    value: string,
    keyboardType: KeyboardType,
    secureTextEntry?: boolean,
    property: string,
    onChangeText: (property: string, value: any) => void
}

export const CustomTextInput = ({
    iconName,
    placeholder,
    value,
    keyboardType,
    secureTextEntry = false,
    property,
    onChangeText
}: Props) => {
  return (
    <View style = {styles.formInput}>
      <Icon style = {styles.formIcon} name={iconName} size={15} solid>
      </Icon>
      <TextInput
      style = {styles.formTextInput}
      placeholder={placeholder}
      keyboardType={keyboardType}
      value={value}
      onChangeText={text => onChangeText(property, text)}
      secureTextEntry={secureTextEntry}>
      </TextInput>
    </View>
  )
}

const styles = StyleSheet.create({
    formInput: {
      backgroundColor: '#eee',
      flexDirection: 'row',
      marginTop: 20,
      borderRadius: 10
    },
    formIcon: {
      color: '#212529',
      alignSelf: 'center',
      marginLeft: 20,
    },
    formTextInput: {
      color: 'black',
      flex: 1,
      marginLeft: 15
    }
  });
  