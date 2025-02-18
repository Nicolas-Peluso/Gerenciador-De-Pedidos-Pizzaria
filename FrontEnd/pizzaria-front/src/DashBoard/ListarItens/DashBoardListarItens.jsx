import {React, useState, useEffect} from 'react';
import Input from '../../input/Input';
import { BuscarItens } from '../../services/Api';
import style from './DashBoardListarItens.module.css'

function DashBoardListarItens() {
    const [filtro, setFiltro] = useState("");
    const [data, setData] = useState([]);
    
    useEffect(() => {
        async function s() {
            let da = await BuscarItens(filtro);
            setData(da.itens);
        }
        s();
    }, [filtro])

    function setCheckBoxUm(valor) {
        setFiltro(valor === filtro ? null : valor);
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
                        data.map((iten) => (
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
                                    <button>excluir</button>
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