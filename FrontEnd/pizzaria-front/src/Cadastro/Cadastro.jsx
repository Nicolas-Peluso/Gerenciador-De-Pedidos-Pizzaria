import React, { useState } from "react";
import Input from "../input/Input";
import style from "./Cadastro.module.css";
import { Cadastrar } from "../services/Api";
import { findCep } from "../services/CepApi";

export default function Cadastro(){

    const [verSenha, setVerSenha] = useState();
    const [senha, setSenha] = useState();
    const [nome, setNome] = useState("");
    const [cargo, setCargo] = useState("");
    const [limiteSabor, setLimiteSabor] = useState(0);
    const [email, setEmail] = useState("");
    const [cep, setCep] = useState("");

    const [nomeRua, setNomeRua] = useState("");
    const [numeroCasa, setNumeroCasa] = useState("");
    const [bairro, setBairro] = useState("");
    const [nomePizzaria, setNomePizzaria] = useState("");
    const [telefone, setTelefone] = useState("");


    const [difetentPass, setDifetentPass] = useState(false);
    const [passReg, setPassReg] = useState(false);
    const [cepReg, setCepReg] = useState(false);
    const [message, setMessage] = useState("");
    
    const [loading, setLoading] = useState(false);

    async function BuscarCep(e){
            setBairro("");
            setNomeRua("");
            setCep(e.target.value);

            let regCep = /^\d{8}$/;

            if (!regCep.test(e.target.value)) {
                setCepReg(true);
                e.target.style.borderColor = "red";
            } else {
                setCepReg(false);
                setLoading(true);
                try {
                    let result = await findCep(e.target.value);
                    setBairro(result.bairro);
                    setNomeRua(result.logradouro);
                } finally {
                    setLoading(false);
                }
                e.target.style.borderColor = "black";
            }
    }

    function ValidaSenha(e){

            setSenha(e.target.value);
            let reg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;
            if (!reg.test(e.target.value)) {
                e.target.style.borderColor = "red";
                setPassReg(true);
            } else {
                e.target.style.borderColor = "black";
                setPassReg(false);
            }
    }

    async function HandleClickBtn(e) {
            e.preventDefault();
            setLoading(true);
            let endereco = nomeRua + ", " + bairro + ", " + numeroCasa;
            if (!(nome.length !== 0 && endereco.length !== 0 && cargo.length !== 0 && email.length !== 0 && senha.length !== 0 && nomePizzaria.length !== 0 && telefone.length !== 0)){
                setLoading(false);
                return false;
            }
            try {
                let result = await Cadastrar(nome, cargo, limiteSabor, email, senha, nomePizzaria, endereco, telefone);
                setMessage(result.Message);
                return true;
            } finally {
                setLoading(false);
            }
        }

    return(
        <form onChange={e => setMessage("")}>
            <Input type={"text"} name={"nome"} placheholder={"Nome"} Value={nome} required onChange={
                e => {
                    setNome(e.target.value);
                }
            }/>

            <Input type={"text"} name={"cargo"} Value={cargo} placheholder={"Cargo"} required onChange={
                e => {
                    setCargo(e.target.value);
                }
            } />

            <Input type={"number"} Value={limiteSabor} name={"limiteSabor"} placheholder={"limiteDeSabor"} required min="0" max="5" onChange={
                e => {
                    if (e.target.value > 5 || e.target.value < 0){
                        e.target.style.borderColor = "red";
                    }else{
                        e.target.style.borderColor = "black";
                        setLimiteSabor(e.target.value);
                }
                }
            }/>
            <p>Voce pode alterar essa informação depois</p>
            
            <Input type={"email"} Value={email} name={"email"} placheholder={"Email"} required onChange={ e => setEmail(e.target.value)} />
            
            <div className={style.versenha}>
                <label htmlFor="verSenha" id="labelVerSenha">mostrar senha</label>
                <input type="checkbox" name="verSenha" id="verSenha" onChange={() => setVerSenha(!verSenha)} />
            </div>
            
            <Input type={verSenha ? "text" : "password"} Value={senha} name={"senha"} placheholder={"Senha"} required onChange={ e => ValidaSenha(e) }/>
            
            {passReg ? <p>Senha deve ter mais de 8 digitos e menos de 16<br /> uma letra e um Numero</p> : null}
            
            <Input type={verSenha ? "text" : "password"} name={"senha2"} placheholder={"Confirmar Senha"} required onChange={
                    e => {
                        if(e.target.value != senha){
                            e.target.style.borderColor = "red"
                            setDifetentPass(true);
                        }
                        else{
                            e.target.style.borderColor = "black"
                            setDifetentPass(false);
                        }
                    }
            }/>

            {difetentPass ? <p>As senhas devem ser iguais</p> : null}

            <Input type={"text"} Value={cep} name={"cep"} placheholder={"Cep Do Restaurante"} disabled={loading} required onChange={ e => BuscarCep(e)}/>
            
            {cepReg ? <p>Cep nao deve conter o traço (-) apenas numeros</p> : null}

            <Input type={"text"} name={"nomeRua"} placheholder={"Nome Rua"} required value={nomeRua}/>
            
            <Input type={"number"} name={"numeroCasa"} placheholder={"Numero da Loja"} required min={0} onChange={e => setNumeroCasa(e.target.value)}/>
            
            <Input type={"text"} name={"bairro"} placheholder={"Nome do bairro"} required value={bairro}/>

            <Input type={"tel"} name={"teleone"} placheholder={"Telefone da loja"} required onChange={e => setTelefone(e.target.value)} />

            <Input type={"text"} name={"nomePizzaria"} placheholder={"Nome da loja"} required onChange={e => setNomePizzaria(e.target.value)} />

            <button style={{ color: loading ? "gray" : "black" }} disabled={loading} onClick={
                e =>  {
                let r = HandleClickBtn(e).then(e => {
                    if (!e) {
                        setMessage("Nenhum campo pode estar vazio!")
                    }
                });
            }}>Cadastrar</button>

            {message.length != 0 ? <p>{message}</p> : null}
        </form>
    );
}