import React, { useContext, useEffect } from 'react';
import style from './DashBoard.module.css';
import { Global } from '../Context/GlobalContext';
import { Link, Outlet, useNavigate } from 'react-router-dom';


function DashBoard() {
    const { isLogin, message, setMessage} = useContext(Global);
    let navigate = useNavigate();

    useEffect(() => {
        if(!isLogin){
            navigate("/entrar");
        }
    }, [isLogin, navigate])

    useEffect(() => {
        navigate("/dashboard/list");
    }, [])

    useEffect(() => {
        setMessage("");
    }, [])

    if(!isLogin) return <></>;

    return (
         <section className={style.container} >
                    <section className={style.containerButtonsOpts}>
                        <ul>
                            <li>
                                <Link to="/dashboard/list"><button>Listar Produtos</button></Link>
                            </li>
                            <li>
                                <Link to="/dashboard/add"><button>Adicionar Produtos</button></Link>
                            </li>
                            <li>
                                <button>Cadastrar Pedido</button>
                            </li>
                            <li>
                                <button>Gerenciar Perfil</button>
                            </li>
                        </ul>
                    </section>
                    <Outlet />

                    <p>{
                            message !== undefined ? message : null
                        }
                    </p>
                </section>  
    );
}

export default DashBoard;