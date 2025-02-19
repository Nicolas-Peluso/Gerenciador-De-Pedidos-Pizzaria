import React, { useContext, useEffect, useState } from 'react';
import Input from '../../input/Input';
import style from './DashBoardAdd.module.css';
import { CadastrarItem } from '../../services/Api';
import { Global } from '../../Context/GlobalContext';

function DashBoardAdd() {
    const [tipo, setTipo] = useState("pizza");
    const [nome, setNome] = useState("");
    const [obs, setObs] = useState("");
    const [cat, setCat] = useState("");
    const [preco, setPreco] = useState("");
    const [sabor, setSabor] = useState("");
    
    const [nomeEl, setNomeEl] = useState();
    const [obsEl, setObsEL] = useState();
    const [catEl, setCatEl] = useState();
    const [precoEl, setPrecoEl] = useState();

    const {setMessage, setLoading, loading} = useContext(Global);

    function handleCheck(valor){
        if(tipo === valor){
            setTipo("")
        } else{
            setTipo(valor)
        }
    }

    useEffect(() => {
        setLoading(false);
        setMessage("");
    }, [])

    return (
        <form onChange={() => setMessage("")}>
            <span className={style.containerCheck}>
                <label htmlFor="pizza">pizza</label>
                <Input type={"checkbox"} name={"pizza"} checked={tipo === "pizza"} onChange={() => handleCheck("pizza")}/>
                <label htmlFor="acompanhamento">acompanhamento</label>
                <Input type={"checkbox"} name={"acompanhamento"} checked={tipo === "Acompanhamento"} onChange={() => handleCheck("Acompanhamento")} />
            </span>
            <Input type={"text"} placheholder={"nome"} name={"nomeItem"} value={nome} onChange={e => {setNome(e.target.value); setNomeEl(e)}}/>
            {tipo === "pizza" ? null : <Input type={"text"} placheholder={"obs"} name={"obsItem"} value={obs} onChange={e => {setObs(e.target.value); setObsEL(e)}}/>}
            <Input type={"text"} placheholder={"categoria"} name={"categoria"} value={cat} onChange={e => {setCat(e.target.value); setCatEl(e)}}/>
            <Input type={"number"} placheholder={"Preco"} name={"preco"} min={0} value={preco} onChange={e => {setPreco(e.target.value); setPrecoEl(e)}}/>
            {tipo === "pizza" ? <Input type={"text"} name={"sabor"} placheholder={"Ingredientes"} onChange={e => setSabor(e.target.value)} value={sabor}/> : null}

            <button disabled={loading} onClick={
                async (e) => {
                    e.preventDefault();
                    setLoading(true);
                    e.target.style.color = "grey";
                    let obj;

                    let keyCat = "";
                    if(tipo === "pizza"){
                        keyCat = "tipoPizza"
                        obj = { nome, sabor, [keyCat]: cat, preco, tipo };
                    } else{
                        keyCat = "tipoAcompanhamento"
                        obj = { nome, obs, [keyCat]: cat, preco, tipo };
                    }
                    
                    let res = await CadastrarItem(obj);
                    
                    if(res !== undefined && res.Message !== undefined){
                        setMessage(res.Message);
                        setLoading(false);
                        e.target.style.color = "black";
                    }

                }}>Cadastrar</button>
        </form>
    );
}

export default DashBoardAdd;