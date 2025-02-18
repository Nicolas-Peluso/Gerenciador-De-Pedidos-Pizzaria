import React, { useState } from 'react';
import Input from '../../input/Input';
import style from './DashBoardAdd.module.css';

function DashBoardAdd() {
    const [tipo, setTipo] = useState("");
    const [nome, setNome] = useState("");
    const [obs, setObs] = useState("");
    const [cat, setCat] = useState("");
    const [preco, setPreco] = useState("");

    const [nomeEl, setNomeEl] = useState();
    const [obsEl, setObsEL] = useState();
    const [catEl, setCatEl] = useState();
    const [precoEl, setPrecoEl] = useState();

    function handleCheck(valor){
        if(tipo === valor){
            setTipo(null)
        } else{
            setTipo(valor)
        }
    }

    return (
        <form>
            <span className={style.containerCheck}>
                <label htmlFor="pizza">pizza</label>
                <Input type={"checkbox"} name={"pizza"} checked={tipo === "pizza"} onChange={() => handleCheck("pizza")}/>
                <label htmlFor="acompanhamento">acompanhamento</label>
                <Input type={"checkbox"} name={"acompanhamento"} checked={tipo === "acompanhamento"} onChange={() => handleCheck("acompanhamento")} />
            </span>
            <Input type={"text"} placheholder={"nome"} name={"nomeItem"} value={nome} onChange={e => {setNome(e.target.value); setNomeEl(e)}}/>
            <Input type={"text"} placheholder={"obs"} name={"obsItem"} value={obs} onChange={e => {setObs(e.target.value); setObsEL(e)}}/>
            <Input type={"text"} placheholder={"categoria"} name={"categoria"} value={cat} onChange={e => {setCat(e.target.value); setCatEl(e)}}/>
            <Input type={"number"} placheholder={"Preco"} name={"preco"} min={0} value={preco} onChange={e => {setPreco(e.target.value); setPrecoEl(e)}}/>
            <button onClick={(e) => {e.preventDefault();}}>Cadastrar</button>
        </form>
    );
}

export default DashBoardAdd;