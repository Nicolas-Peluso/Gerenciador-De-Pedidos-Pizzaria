import React, { useEffect, useState } from 'react';
import Input from '../../input/Input';
import {BuscarItens} from '../../services/Api'

function ItensPedido() {
    const [data, setData] = useState(undefined);
    const [itenAdicionado, setItenAdicionado] = useState(undefined);
    
    useEffect(() => {
        BuscarTodosItens();
    }, [])

    async function BuscarTodosItens() {
        let d = await BuscarItens("null", 0);
        if(d !== undefined){
            setData(d);
        }
    }

    return (
        <fieldset>
            <p>Itens</p>
            <Input list={"listaProdutos"} onChange={e => setItenAdicionado(e.target.value)} placheholder={"Digite o nome do item"} required/>
            <datalist id='listaProdutos'>
                {
                    data !== undefined && data.itens ? data.itens.map(iten => <option value={iten.nome} key={iten.nome}></option>) : <option value={"dd"}></option>
                }
            </datalist>
            <button onClick={e => {
                e.preventDefault();
                }}>Adicionar Item</button>
        </fieldset>
    );
}

export default ItensPedido;