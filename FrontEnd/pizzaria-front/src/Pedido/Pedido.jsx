import React from 'react';
import style from './Pedido.module.css';
import ClienteCadastro from './Cliente/ClienteCadastro';
import ItensPedido from './Itens/ItensPedido';
import PedidoCadastro from './PedidoCadastro/PedidoCadastro';
import DashBoard from '../DashBoard/DashBoard';

function Pedido() {
    return (
        <section className={style.ContainerPedido}>
            <DashBoard />
            <form>
                <ClienteCadastro />
                <ItensPedido />
                <PedidoCadastro />
                <button>Cadastrar Pedido</button>
            </form>

        </section>
    );
}

export default Pedido;