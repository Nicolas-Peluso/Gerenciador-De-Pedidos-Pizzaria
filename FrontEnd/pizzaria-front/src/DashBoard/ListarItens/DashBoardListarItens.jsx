import {React, useState, useEffect, useContext} from 'react';
import Input from '../../input/Input';
import { BuscarItens } from '../../services/Api';
import style from './DashBoardListarItens.module.css'
import { DeletarItem } from '../../services/Api';
import { Global } from '../../Context/GlobalContext';

function DashBoardListarItens() {
    const [filtro, setFiltro] = useState("");
    const [data, setData] = useState([]);
    const {setMessage} = useContext(Global);

    useEffect(() => {
            setMessage("");
        }, [])

    useEffect(() => {
            AttData();
    }, [filtro])
    
    function setCheckBoxUm(valor) {
        setFiltro(valor === filtro ? null : valor);
    }

    async function AttData() {
        let da = await BuscarItens(filtro);
        setData(da.itens);
    }

    async function handleClick(nome, att){
        let tipo = "";

        if(att !== undefined){
            tipo = "pizza"
        } else{
            tipo = "Acompanhamento"
        }

        let obj = {"tipo": tipo, "nome": nome};

        await DeletarItem(obj);
        AttData();
    }

    return (
        <section className={style.containerTable}>
            <section className={style.containerFiltro}>
                <p>filtrar por:</p>
                <ul>
                    <li>
                        <label htmlFor="pizza">pizza</label>
                        <Input type={"checkbox"} checked={filtro === "pizza"} name={"pizza"} onChange={() => setCheckBoxUm("pizza")} />
                    </li>
                    <li>
                        <label htmlFor="acompanhamento">acompanhamento</label>
                        <Input type={"checkbox"} checked={filtro === "acompanhamento"} name={"acompanhamento"} onChange={() => setCheckBoxUm("acompanhamento")} />
                    </li>
                </ul>
            </section>

            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Preco</th>
                        {filtro !== "acompanhamento" ? <th>Sabor</th> : null}
                    </tr>
                </thead>

                <tbody>
                    {
                        (data || []).map((iten) => (
                            <tr key={iten.id}>
                                <td>
                                    {iten.nome}
                                </td>
                                <td>
                                    {iten.tipo}
                                </td>
                                <td>
                                    {"R$" + iten.preco}
                                </td>
                                <td>
                                    {iten.sabor !== null ? iten.sabor : ""}
                                </td>
                                <td>
                                    <button>editar</button>
                                </td>
                                <td>
                                    <button onClick={() => handleClick(iten.nome, iten.sabor)}>excluir</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </section>
    );
}

export default DashBoardListarItens;