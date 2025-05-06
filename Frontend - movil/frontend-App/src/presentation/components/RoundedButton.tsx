import React from 'react'
import { TouchableOpacity, Text, Button, StyleSheet } from 'react-native';
import { MyColors } from '../theme/appTheme';

interface Props{
    text: string,
    onPress: () => void
}

export const RoundedButton = ({text, onPress}:Props) => {
  return (
    <TouchableOpacity
    style={styles.RoundedButton}
    onPress={() => onPress()}
    >
        <Text style={styles.textButton}>{text}</Text>
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
    RoundedButton:{
        width: '100%',
        height: 50,
        marginBottom: 20,
        backgroundColor: '#9854b1',
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 15,
    },
    textButton:{
        color: '#fff'
    }
})