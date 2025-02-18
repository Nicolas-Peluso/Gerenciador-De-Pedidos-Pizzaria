import React, { useContext, useEffect, useState } from 'react';
import style from './DashBoard.module.css';
import { Global } from '../Context/GlobalContext';
import { useNavigate } from 'react-router';
import Input from '../input/Input';
import { BuscarItens } from '../services/Api';

function DashBoard() {
    const [filtro, setFiltro] = useState("");
    const [data, setData] = useState([]);

    const { isLogin } = useContext(Global);
    let navigate = useNavigate();

    function setCheckBoxUm(valor){
        setFiltro(valor === filtro ? null : valor);
    }

    useEffect(() => {
        if(!isLogin){
            navigate("/entrar");
        }
    }, [isLogin, navigate])

    useEffect(() => {
        async function s(){
            let da = await BuscarItens(filtro);
            setData(da.itens);
        }
        s();
    }, [filtro])

    if(!isLogin) return <></>;
    
    return (
         <section className={style.container} >
                    <section className={style.containerButtonsOpts}>
                        <ul>
                            <li>
                                <button>Listar Produtos</button>
                            </li>
                            <li>
                                <button>Adicionar Produtos</button>
                            </li>
                            <li>
                                <button>Cadastrar Pedido</button>
                            </li>
                            <li>
                                <button>Gerenciar Perfil</button>
                            </li>
                        </ul>
                    </section>
                    <section className={style.containerFiltro}>
                        <p>filtrar por:</p>
                        <ul>
                            <li>
                                <label htmlFor="pizza">pizza</label>
                                <Input type={"checkbox"} checked={filtro === "pizza"} name={"pizza"} onChange={() => setCheckBoxUm("pizza")}/>
                            </li>
                            <li>
                        <label htmlFor="acompanhamento">acompanhamento</label>
                                <Input type={"checkbox"} checked={filtro === "acompanhamento"}  name={"acompanhamento" }onChange={() => setCheckBoxUm("acompanhamento")} />
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
                                            {"R$"+iten.preco}
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
                </section >  
    );
}

export default DashBoard;