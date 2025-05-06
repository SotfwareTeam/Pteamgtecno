import { StyleSheet } from "react-native";

const RegisterStyles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black'
  },
  linearGradient: {
    width: '100%',
    height: '100%'
  },
  form: {
    zIndex: 0,
    width: '100%',
    height: '67%',
    backgroundColor: '#fff',
    position: 'absolute',
    bottom: 0,
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    padding: 30
  },
  scroll: {
    minHeight: '65%'
  },
  logoContainer: {
    position: 'absolute',
    alignSelf: 'center',
    top: '5%'
  },
  logoImage: {
    alignSelf: 'center'
  },
  logoText: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 25,
    marginTop: 20,
    fontWeight: 'bold'
  },
  logoSubText: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 16,
    marginTop: 15,
    paddingHorizontal: 30
  },
  formText: {
    textAlign: 'center',
    fontWeight: 'bold',
    fontSize: 17,
  },
  formInput: {
    flexDirection: 'row',
    marginTop: 25
  },
  formIcon: {
    width: 25,
    height: 25,
    marginTop: 5
  },
  formTextInput: {
    flex: 1,
  },
  formRegister: {
    position: 'relative',
    bottom: 25,
    color: '#fff',
    width: '120%',
    alignSelf: 'center',
    backgroundColor: '#076a81',
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: 40,
    paddingTop: 30,
    paddingBottom: 125,
    borderRadius: '50%'
  },
  formRegisterText: {
    fontStyle: 'italic',
    color: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#fff',
    fontWeight: 'bold',
    marginLeft: 10
  }
});

export default RegisterStyles;