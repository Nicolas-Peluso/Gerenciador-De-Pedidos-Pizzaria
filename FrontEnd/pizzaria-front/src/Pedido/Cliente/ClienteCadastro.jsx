import React from 'react';
import Input from '../../input/Input';
import style from './ClienteCadastro.module.css';

function ClienteCadastro() {
    return (
        <fieldset>
        <p>Cliente</p>
            <Input type={"text"} placheholder={"Nome"} required />
            <Input type={"text"} placheholder={"Cep"} required />
            <Input type={"text"} placheholder={"Bairro"} required />
            <Input type={"text"} placheholder={"Nome da rua"} required />
            <Input type={"text"} placheholder={"Obs"} required />
            <Input type={"number"} placheholder={"Numero"} required min={0}/>
            <Input type={"number"} placheholder={"Numero Apto"} />
        </fieldset>

    );
}

export default ClienteCadastro;