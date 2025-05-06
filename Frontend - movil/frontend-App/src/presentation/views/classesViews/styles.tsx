import { StyleSheet } from "react-native";

const profileStyles = StyleSheet.create({
    container: {
        flex: 10,
        padding: 20,
    },
    linearGradient: {
        position: 'absolute',
        width: '115%',
        height: '110%',
    },
    title: {
        paddingTop: 10,
        fontSize: 24,
        fontWeight: 'bold',
        textAlign: 'center',
    },
    productContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        padding: 15,
        borderRadius: 10,
        marginBottom: 5,
        shadowColor: '#000',
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.1,
        shadowRadius: 5,
        elevation: 3,
    },
    searchedContainer: {
        padding: 15,
        borderRadius: 10,
        marginBottom: 5,
        shadowColor: '#000',
        shadowOffset: { width: 0, height: 2 },
        shadowOpacity: 0.1,
        shadowRadius: 5,
        elevation: 3,
    },
    searchedClassContainer: {
        height: '47%',
    },
    productText: {
        width: '70%',
        fontSize: 14
    },
    searchTitle: {
        textAlign: 'center',
        fontSize: 14,
        fontWeight: 'bold'
    },
    searchText: {
        width: '70%',
        fontSize: 14
    },
    buttonContainer: {
        flexDirection: 'column',
        justifyContent: 'space-between'
    },
    form: {
        backgroundColor: '#076a81',
        width: '112%',
        height: '35%',
        position: 'absolute',
        bottom: 0,
        paddingTop: 10,
        borderTopLeftRadius: 20,
        borderTopRightRadius: 20,
        paddingHorizontal: 30
    },
    flatList: {
        maxHeight: '48%',
    },
    scroll: {
        minHeight: '75%',
        width: '100%'
    },
    button: {
        textAlign: 'center',
        backgroundColor: '#b269cd',
        padding: 10,
        borderRadius: 5,
        marginHorizontal: 5,
    },
    buttonDelete: {
        alignItems: 'center',
        padding: 10,
        borderRadius: 5,
        marginHorizontal: 5,
        backgroundColor: '#5f2f9a'
    },
    buttonText: {
        color: 'white',
        fontWeight: 'bold',
    },
    buttonTextLogOut: {
        color: 'white',
    },
    input: {
        height: 40,
        borderColor: '#ccc',
        borderWidth: 1,
        borderRadius: 5,
        marginVertical: 5,
        paddingHorizontal: 10,
        backgroundColor: '#eee',
    },
    addButton: {
        width: '50%',
        backgroundColor: '#9854b1',
        padding: 15,
        borderRadius: 5,
        alignItems: 'center',
        marginVertical: 10,
    },
    addButtonText: {
        color: 'white',
        fontWeight: 'bold',
    },
    buttonCloseModal: {
        zIndex: 1,
        position: 'absolute',
        justifyContent: 'center', 
        backgroundColor: '#87CEEB',
        borderRadius: 6,
        padding: 6,
        marginBlockStart: 10
    },
    buttonLogOut: {
        justifyContent: 'center', 
        backgroundColor: '#076a81',
        borderRadius: 6,
        padding: 6,
        marginBlockStart: 10
    },
    searchButton: {
        backgroundColor: '#076a81',
        padding: 15,
        borderRadius: 5,
        alignItems: 'center',
        marginBottom: 10,
    },
    searchButtonText: {
        color: 'white',
        fontWeight: 'bold',
    },
    reportButton: {
        width: '50%',
        backgroundColor: '#2196F3',
        padding: 15,
        borderRadius: 5,
        alignItems: 'center',
    },
    lastButtons: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        gap: '5%'
    },
    reportButtonText: {
        color: 'white',
        fontWeight: 'bold',
    },
    modal: {
        
    },
    modalContent: {
        backgroundColor: 'white',
        padding: 20,
        borderRadius: 10,
        alignItems: 'center',
    },
    modalTitle: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 10,
    },
    modalText: {
        textAlign: 'left',
        fontSize: 14,
        marginBottom: 5,
    },
    closeButton: {
        position: 'absolute',
        right: 2,
        padding: 10
    },
    closeButtonText: {
        color: 'white',
        fontWeight: 'bold',
    }
})

export default profileStyles;