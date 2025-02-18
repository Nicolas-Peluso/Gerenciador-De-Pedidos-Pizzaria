import React, { useContext, useState } from "react";
import Input from "../input/Input";
import style from "./Cadastro.module.css";
import { Cadastrar } from "../services/Api";
import { findCep } from "../services/CepApi";
import { EntrarPage } from "../Entrar/Entrar";

export default function Cadastro(){

    const [verSenha, setVerSenha] = useState();
    const [senha, setSenha] = useState();
    const [senhaElement, setSenhaElement] = useState();

    const [nome, setNome] = useState("");
    const [cargo, setCargo] = useState("");
    const [email, setEmail] = useState("");
    const [cep, setCep] = useState("");
    const [emailElement, setEmailElement] = useState();
    
    const [nomeRua, setNomeRua] = useState("");
    const [numeroCasa, setNumeroCasa] = useState("");
    const [bairro, setBairro] = useState("");
    const [nomePizzaria, setNomePizzaria] = useState("");
    const [telefone, setTelefone] = useState("");

    const [difetentPass, setDifetentPass] = useState(false);
    const [passReg, setPassReg] = useState(false);
    const [cepReg, setCepReg] = useState(false);
    
    const { loading, setLoading, HandleClickBtn, setMessage } = useContext(EntrarPage);

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

    return(
        <form onChange={e => {setMessage(""); setLoading(false)}}>
            <Input type={"text"} name={"nome"} placheholder={"Nome"} Value={nome} required onChange={
                e => {
                    setNome(e.target.value);
                }
            }/>

            <section className={style.containerCargoLimite}>
                <Input type={"text"} name={"cargo"} Value={cargo} placheholder={"Cargo"} required onChange={
                    e => {
                        setCargo(e.target.value);
                    }
                } />
            </section>
            
            <Input type={"email"} Value={email} name={"email"} placheholder={"Email"} required onChange={e => { setEmail(e.target.value); setEmailElement(e); e.target.style.borderColor = "black"}} />
            
            <div className={style.versenha}>
                <label htmlFor="verSenha" id="labelVerSenha">mostrar senha</label>
                <input type="checkbox" name="verSenha" id="verSenha" onChange={e => setVerSenha(!verSenha)} />
            </div>
            
            <Input type={verSenha ? "text" : "password"} Value={senha} name={"senha"} placheholder={"Senha"} required onChange={e => {ValidaSenha(e);setSenhaElement(e); e.target.style.borderColor = "black"}}/>
            
            {passReg ? <p style={{color: "red"}}>Senha deve ter mais de 8 digitos <br /> menos de 16<br /> uma letra e um Numero</p> : null}

            <Input type={verSenha ? "text" : "password"} name={"senha2"} placheholder={"Confirmar Senha"} required onChange={
                    e => {
                        if(e.target.value !== senha){
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
            
            {cepReg ? <p>Cep nao deve conter o traço (-) <br /> apenas numeros</p> : null}

            <section className={style.enderecoCont}>
                <Input type={"number"} name={"numeroCasa"} placheholder={"Numero"} required min={0} onChange={e => setNumeroCasa(e.target.value)} />
                <Input type={"text"} name={"nomeRua"} placheholder={"Nome da Rua"} required value={nomeRua}/>
            </section>
            
            <Input type={"text"} name={"bairro"} placheholder={"Nome do bairro"} required value={bairro}/>

            <Input type={"tel"} name={"teleone"} placheholder={"Telefone da loja"} required onChange={e => setTelefone(e.target.value)} />

            <Input type={"text"} name={"nomePizzaria"} placheholder={"Nome da loja"} required onChange={e => setNomePizzaria(e.target.value)} />

            <button style={{ color: loading ? "gray" : "black" }} disabled={loading} onClick={
                async e =>  {
                    e.preventDefault();
                    let endereco = nomeRua + ", " + bairro + ", " + numeroCasa;

                    let obj = { nome, email, senha, endereco, telefone, cargo, nomePizzaria};
                    let res = false;
                    
                    res = HandleClickBtn(emailElement, obj);

                    if (!res) {
                        setLoading(true);
                        setMessage("Verifique os campos e tente novamente!")
                    } else if (difetentPass === true || passReg === true){
                        senhaElement.target.style.borderColor = "red";
                        setMessage("Verifique a senha, e tente novamente");
                    } else{ 
                        try{
                            const req = await Cadastrar(obj);
                            if(req.Message !== undefined){
                                setMessage(req.Message);
                            }
                        }catch(ex){
                            setMessage("Algo deu errado durante a manipulação dos dados");
                        }
                    }
            }}>Cadastrar</button>
        </form>
    );
}