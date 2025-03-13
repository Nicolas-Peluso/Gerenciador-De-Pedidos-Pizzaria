import {React, useState, useEffect, useContext} from 'react';
import Input from '../../input/Input';
import { BuscarItens } from '../../services/Api';
import style from './DashBoardListarItens.module.css'
import { DeletarItem } from '../../services/Api';
import { Global } from '../../Context/GlobalContext';
import Confirmar from '../../Util/ConfirmarAcao/Confirmar';

function DashBoardListarItens() {
    const [filtro, setFiltro] = useState("all");
    const [data, setData] = useState(null);
    const {setMessage, confirmar, setConfirmar} = useContext(Global);
    const [del, setDel] = useState(false);
    const [nome, setNome] = useState();
    const [sabor, setSabor] = useState();
    const [limite, setlimite] = useState(1);

    useEffect(() => {
            setMessage("");
        }, [])

    useEffect(() => {
            AttData();
    }, [filtro, limite])
    
    function setCheckBoxUm(valor) {
        setFiltro(valor === filtro ? null : valor);
    }

    async function AttData() {
        let dataReq = await BuscarItens(filtro, limite);
        if (dataReq !== undefined){
            setData(dataReq);
        }
    }

    async function handleClick(nome, att){
        setDel(false);
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

    useEffect(() => {
        if(del && confirmar){
            handleClick(nome, sabor);
            setConfirmar(false);
        }
    }, [confirmar, del])

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
                 {data !== null && data.itens.length > 0 ? <tr>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Preco</th>
                        <th>obs</th>
                        {filtro !== "acompanhamento" ? <th>Sabor</th> : null}    
                        </tr> : null }
                
                </thead>

                <tbody>
                    {
                        (data != null && data.itens.length > 0 ? data.itens : []).map((iten) => (
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
                                    {iten.obs}
                                </td>
                                <td>
                                    {iten.sabor !== null ? iten.sabor : ""}
                                </td>
                                <td>
                                    <button>editar</button>
                                </td>
                                <td>
                                    <button onClick={() => {
                                        setDel(true);
                                        setNome(iten.nome);
                                        setSabor(iten.sabor);
                                    }}>excluir</button>
                                </td>
                            </tr>
                        ))                        
                    }
                    {data != null && data.itens.length === 0 ? <h1>Nenhum produto encontrado</h1> : null}
                </tbody>
            </table>
               {del ? <Confirmar fun={setDel} /> : null}
               
               <section className={style.ContainerPag}>
                    <span className={style.anterior}>
                        <button onClick={e => {
                            e.preventDefault();
                            if(limite > 1){
                                setlimite(limite - 1);
                            }
                        }}>{"<"}</button>
                    </span>
                    <span className={style.proximo}>
                        <button onClick={ e => {
                            e.preventDefault();
                            if(data !== null && data.itens.length !== 0){
                                setlimite(limite + 1);
                            }
                         }}>{">"}</button>
                    </span>
               </section>
        </section> 
    );
}

export default DashBoardListarItens;