import React from 'react'
import { useState, useEffect } from 'react';
import { getClasses, getClassByParameters, createClass, updateClass, deleteClass } from '../../../data/source/remote/api/ApiDelivery';
import { RemoveUserLocalUseCase } from "../../../domain/useCases/userLocal/removeUserLocal";

interface Classes {
    id: number;
    name: string;
    classType: string;
    duration: string;
    genres: string;
    levels: string;
    date: string;
}

const ClassesViewModel = () => {
    const [classes, setClasses] = useState<Classes[]>([]);
    const [name, setName] = useState('');
    const [classType, setClassType] = useState('');
    const [duration, setDuration] = useState('');
    const [genres, setGenres] = useState('');
    const [levels, setLevels] = useState('');
    const [date, setDate] = useState('');
    const [search, setSearch] = useState('')
    const [selectedClassId, setSelectedClassId] = useState<number | null>(null);
    const [isModalVisible, setModalVisible] = useState(false);
    const [isModalVisible2, setModalVisible2] = useState(false);
    const [searchedClass, setSearchedClass] = useState<Classes[]>([]); //useState<Classes | null>(null);

    useEffect(() => {
        fetchClasses();;
    }, []);

    const fetchClasses = async () => {
        const data = await getClasses();
        setClasses(data);
    };

    const handleAddClass = async () => {
        const newClass = await createClass({ name, classType, duration, genres, levels, date });
        setClasses([...classes, newClass]);
        setName('');
        setClassType('');
        setDuration('');
        setGenres('');
        setLevels('');
        setDate('');
    };

    const handleUpdateClass = async () => {
        if (selectedClassId !== null) {
            const updatedClass = await updateClass(selectedClassId, { name, classType, duration, genres, levels, date });
            setClasses(classes.map(clase => clase.id === selectedClassId ? updatedClass : clase));
            setSelectedClassId(null);
            setName('');
            setClassType('');
            setDuration('');
            setGenres('');
            setLevels('');
            setDate('');
        }
    };
    

    const handleDeleteClass = async (id: number) => {
        await deleteClass(id);
        setClasses(classes.filter(clase => clase.id !== id));
    };

    const handleSearchClass = async () => {
        try {
            const response = await getClassByParameters(search);
            console.log('Respuesta:', response);
        
            if (Array.isArray(response)) {
                setSearchedClass(response);
            } else {
                setSearchedClass([]);
            }
        } catch (error) {
            console.error('Error al buscar clases:', error);
            setSearchedClass([]);
        }
    };

    const toggleModal = () => {
        setModalVisible(!isModalVisible);
    };
    

    const toggleModal2 = () => {
        setModalVisible2(!isModalVisible2);
    };
    
    const removeSession = async () => {
        await RemoveUserLocalUseCase();
    }
    return {
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
    }
}


export default ClassesViewModel;
