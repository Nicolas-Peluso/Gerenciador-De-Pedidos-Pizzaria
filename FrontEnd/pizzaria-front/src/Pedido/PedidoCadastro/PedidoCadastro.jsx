import React from 'react';
import Input from '../../input/Input';

function PedidoCadastro() {
    return (
        <fieldset>
            <p>Pedido</p>
            <Input type={"text"} placheholder={"Forma de pagamento"} required />
            <Input type={"text"} placheholder={"Dinheiro"} required />
            <Input type={"text"} placheholder={"pix Pedido"} required />
            <Input type={"text"} placheholder={"pagamentoConfirmadoPedido"} required />
            <Input type={"text"} placheholder={"valor do cliente"} required />
        </fieldset>
    );
}

export default PedidoCadastro;