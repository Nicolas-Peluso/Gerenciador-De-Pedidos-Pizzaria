import React, { useContext } from 'react';
import style from './Confirmar.module.css'
import { Global } from '../../Context/GlobalContext';

function Confirmar({fun}) {
    const {setConfirmar} = useContext(Global);
    
    return (
        <section className={style.ContainerConfirmar}>
            <span>
                Deseja mesmo prosseguir com a operação?
                Não ha como restaurar!
            </span>
            <form>
                <button onClick={(e) => {e.preventDefault(); setConfirmar(true)}} className={style.simBtn}>Sim</button>
                <button onClick={(e) => { e.preventDefault(); setConfirmar(false); fun(false)}}>Nao</button>
            </form> 
        </section>
    );
}

export default Confirmar;