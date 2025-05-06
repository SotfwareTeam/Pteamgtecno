import { StyleSheet } from "react-native";

const HomeStyles = StyleSheet.create({
  
  container: {
    flex: 1
  },
  linearGradient: {
    width: '100%',
    height: '100%',
  },
  form: {
    width: '100%',
    height: '50%',
    backgroundColor: '#fff',
    position: 'absolute',
    bottom: 0,
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    padding: 30
  },
  logoContainer: {
    position: 'absolute',
    alignSelf: 'center',
    top: '15%'
  },
  logoImage: {
    alignSelf: 'center',
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
    fontSize: 16,
    fontWeight: 'bold'
  },
  formInput: {
    flexDirection: 'row',
    marginTop: 30
  },
  formIcon: {
    width: 25,
    height: 25,
    marginTop: 10
  },
  formTextInput: {
    color: 'black',
    flex: 1,
    borderBottomWidth: 1,
    borderBottomColor: 'red',
    marginLeft: 15
  },
  formRegister: {
    position: 'relative',
    bottom: 20,
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

export default HomeStyles;