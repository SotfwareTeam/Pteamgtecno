import { StackScreenProps } from "@react-navigation/stack";
import React from "react";
import { View, Text, FlatList, Button, TextInput, StyleSheet, TouchableOpacity, ScrollView } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import Icon from 'react-native-vector-icons/FontAwesome5';
import { RootStackParamList } from "../../../../App";
import useViewModel from './viewModel';
import Modal from 'react-native-modal';
import styles from "./styles";

interface Props extends StackScreenProps<RootStackParamList, 'ClassesScreen'> { };

export const ClassesScreen = ({ navigation, route }: Props) => {
    const {
        classes,
        name,
        classType,
        duration,
        genres,
        levels,
        date,
        search,
        selectedClassId,
        isModalVisible,
        isModalVisible2,
        searchedClass,
        setName,
        setClassType,
        setDuration,
        setGenres,
        setLevels,
        setDate,
        setSearch,
        setSelectedClassId,
        toggleModal,
        toggleModal2,
        handleAddClass,
        handleUpdateClass,
        handleDeleteClass,
        handleSearchClass,
        removeSession
    } = useViewModel();

    return (
        <View style={styles.container}>
            <LinearGradient style={styles.linearGradient}
                colors={['#b269cd', '#076a81', '#5f2f9a']}
                start={{x: 0.1, y: 0.1}} end={{x: 0.9, y: 0.9}}>
            </LinearGradient>
            <View style={styles.buttonContainer}>
                <TouchableOpacity style={styles.buttonCloseModal} onPress={toggleModal}>
                    <Icon name='user-cog' size={15} solid>
                    </Icon>
                </TouchableOpacity>
                <Modal isVisible={isModalVisible}>
                    <View style={styles.modalContent}>
                        <Text style={styles.modalTitle}>Sesión de Usuario</Text>
                        {classes.map((clase, index) => (
                            <Text key={clase?.id?.toString() ?? `clase-${index}`} style={styles.modalText}>
                                {clase.name +` - Tipo de Clase: ` + clase.classType + `\nDuración: ` + clase.duration + ` min - ` + clase.genres + ` nivel ` + clase.levels + `\n` + clase.date.split('T')[0]}
                            </Text>
                        ))}
                        <TouchableOpacity style={styles.buttonLogOut}
                            onPress={() => {
                                removeSession();
                                navigation.navigate('HomeScreen');
                            }}>
                            <Text style={styles.buttonTextLogOut}>Cerrar Sesión</Text>
                        </TouchableOpacity>
                        <TouchableOpacity onPress={toggleModal} style={styles.closeButton}>
                            <Icon name='times' size={15} solid>
                            </Icon>
                        </TouchableOpacity>
                    </View>
                </Modal>
            </View>
            <Text style={styles.title}>Clases</Text>
            <TextInput
                style={styles.input}
                placeholder="Búsqueda general"
                value={search}
                onChangeText={setSearch}
            />
            <TouchableOpacity style={styles.searchButton} onPress={handleSearchClass}>
                <Text style={styles.searchButtonText}>Buscar Clases</Text>
            </TouchableOpacity>
            {Array.isArray(searchedClass) && searchedClass.length > 0 && (
                <View style={styles.searchedClassContainer}>
                <ScrollView>
                    <Text style={styles.searchTitle}>Clases Encontradas:</Text>
                    {searchedClass.map((clase) => (
                        <View key={clase.id} style={styles.searchedContainer}>
                            <Text style={styles.productText}>
                            {clase.name + `\nTipo de Clase: ` + clase.classType + `\nDuración: ` + clase.duration + ` min\n` + clase.genres + ` nivel ` + clase.levels + `\n` + clase.date.split('T')[0]}
                            </Text>
                        </View>
                    ))}
                </ScrollView>
                </View>
            )}
            <FlatList
                style={styles.flatList}
                data={classes}
                keyExtractor={(item, index) => item?.id ? item.id.toString() : index.toString()}
                renderItem={({ item }) => (
                    <View style={styles.productContainer}>
                        <Text style={styles.productText}>
                            {item.name + `\nTipo de Clase: ` + item.classType + `\nDuración: ` + item.duration + ` min\n` + item.genres + ` nivel ` + item.levels + `\n` + item.date.split('T')[0]}
                        </Text>
                        <View style={styles.buttonContainer}>
                            <TouchableOpacity style={styles.button} onPress={() => {
                                setName(item.name);
                                setClassType(item.classType);
                                setDuration(item.duration);
                                setGenres(item.genres);
                                setLevels(item.levels);
                                setDate(item.date);
                                setSelectedClassId(item.id);
                            }}>
                                <Text style={styles.buttonText}>Modificar</Text>
                            </TouchableOpacity>
                            <TouchableOpacity style={styles.buttonDelete} onPress={() => handleDeleteClass(item.id)}>
                                <Text style={styles.buttonText}>Eliminar</Text>
                            </TouchableOpacity>
                        </View>
                    </View>
                )}
            />
            <View style={styles.form}>
                <ScrollView style={styles.scroll}>
                    <TextInput
                        style={styles.input}
                        placeholder="Nombre"
                        value={name}
                        onChangeText={setName}
                    />
                    <TextInput
                        style={styles.input}
                        placeholder="Tipo de clase"
                        value={classType}
                        onChangeText={setClassType}
                    />
                    <TextInput
                        style={styles.input}
                        placeholder="Duración"
                        value={duration.toString()}
                        onChangeText={setDuration}
                    />
                    <TextInput
                        style={styles.input}
                        placeholder="Generos"
                        value={genres}
                        onChangeText={setGenres}
                    />
                    <TextInput
                        style={styles.input}
                        placeholder="Niveles"
                        value={levels}
                        onChangeText={setLevels}
                    />
                    <TextInput
                        style={styles.input}
                        placeholder="Fecha"
                        value={date}
                        onChangeText={setDate}
                    />
                </ScrollView>
                <View style={styles.lastButtons}>
                    <TouchableOpacity style={styles.addButton} onPress={selectedClassId ? handleUpdateClass : handleAddClass}>
                        <Text style={styles.addButtonText}>{selectedClassId ? "Editar Clase" : "Crear Clase"}</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.reportButton} onPress={toggleModal2}>
                        <Text style={styles.reportButtonText}>Generar Reporte</Text>
                    </TouchableOpacity>
                    <Modal isVisible={isModalVisible2} style={styles.modal}>
                        <ScrollView>
                            <View style={styles.modalContent}>
                                <Text style={styles.modalTitle}>Reporte de Clases</Text>
                                {classes.map((clase, index) => (
                                    <Text key={clase?.id?.toString() ?? `clase-${index}`} style={styles.modalText}>
                                        {clase.name + ` - Tipo de Clase: ` + clase.classType + `\nDuración: ` + clase.duration + ` min - ` + clase.genres + ` nivel ` + clase.levels + `\n` + clase.date.split('T')[0]}
                                    </Text>
                                ))}
                                <TouchableOpacity onPress={toggleModal2} style={styles.closeButton}>
                                    <Icon name='times' size={15} solid>
                                    </Icon>
                                </TouchableOpacity>
                            </View>
                        </ScrollView>
                    </Modal>
                </View>
            </View>
        </View>
    )
};
export default ClassesScreen;