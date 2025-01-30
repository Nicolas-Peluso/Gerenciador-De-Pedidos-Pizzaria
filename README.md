<h1>Gerenciador de Pedidos para uma pizzaria</h1>

<h2>Requisitos funcionais:</h2>
<ul>
    <li>Cadstrar usuario admin</li>
    <li>Cadastrar produtos no sistema (pizzas e acompanhamento)</li>
    <li>Criar um pedido</li>
    <li>Cancelar a criação de um pedido (caso cliente desista da compra durante o cadastro do pedido)</li>
    <li>Buscar endereco do cliente por API de gps</li>
    <li>Adicionar informações adicionais a um pedido(remover cebola por exemplo)</li>
    <li>Cadastrar até 5 pizzas e 4 acompanhamentos para um pedido, caso maior calcular duas rotas de entrega</li>
    <li>gerar comanda com o pedido</li>
    <li>Exibir todos os pedidos</li>
    <li>Cancelar um pedido</li>
    <li>Editar um Pedido</li>
    <li>Confirmar que o pedido foi entrgue</li>
    <li>Confirmar que o pedido esta em rota de entrega</li>
    <li>Exibir informações detalhadas dos pedidos</li>
    <li>Listar pedidos de acordo com um filtro (flags)</li>
    <li>Se pagamaneto = pix -> Confirmar Pagamento do pedido</li>
    <li>Informar que o pedido esta pronto (Aguardando motoboy)</li>
    <li>Criar uma rota de acordo com os pedidos que estao com um motoboy</li>
    <li>Cadastrar um motoboy</li>
    <li>finalizar pedido</li>
</ul>

<h2>Regras de negócio</h2>
<ul>
    <li>Todo pedido produto cadastrado no sistema deve ter um identificador unico</li>
    <li>Criar regra de limite de sabores para uma unica pizza</li>
    <li>todo pedido, deve ter:
        <ol>
            <li>nome do cliente</li>
            <li>Idetificador do pedido</li>
            <li>produtos pedidos</li>
            <li>valor total</li>
            <li>Tempo de espera</li>
            <li>Forma de pagamento</li>
            <li>Se pagamento em dinheiro -> valor a voltar ao cliente se houver troco</li>
            <li>flag -> começa com "em preparo"</li>
            <li>se pix -> pagamento consfirmado? zerar o campo de troco</li>
            <li>pedido agrupado?</li>
            <li>se pedido agrupado: identificador do pedido deve ser: 1a e 1b (IdentificadorA e IdentificadorB)</li>
        </ol>
    </li>
    <li>Buscar endereco do cliente por API de gps</li>
    <li>comandas devem ter as informações do pedido e O numero do identificador relativamente grande.</li>
    <li>Informações adicionais somente para a remoção de elementos, para adicionar elementos o valor e o elemento deve ser feito manualmente</li>
    <li>Se o pedido exceder 5 pizzas ou 4 refrigerante (capacidade que um motoqueiro consegue levar) botao de "criar pedido agrupado" deve sugirgir, onde:
        <ol>
            <li>Duas comandas separadas devem ser impressas</li>
            <li>Um identificador gigante no topo da comanda dever motrar "PEDIDO AGRUPADO" e o numero do identifcador do pedido</li>
            <li>A segunda comanda deve conter as mesmas informações a cima mas com o seu proprio identificador</li>
        </ol>
    </li>
    <li>Pedidos serao exibidos em uma pagina onde:
        <ol>
            <li>os pedidos com a flag, "em andamento" vao estar no topo</li>
            <li>Pedidos são listados por ordem de chegada (primeiro a ser pedido, primeiro a ser preparado)</li>
            <li>tera opçoes de filtro por flags</li>
            <li>opção de pesquisa por pedidos</li>
        </ol>
    </li>
     <li>
        <p>pedidos listados na pagina de "exibir pedidos" devem ter os botoes:</p>
        <ol>
            <li>pedido pronto</li>
            <li>Cancelar pedido (mostrar um aviso)</li>
            <li>Confirmar entrega</li>
            <li>em rota</li>
            <li>exibir informações</li>
            <li>editar pedido</li>
            <li>se e somente se pagamaneto = pix exibir CONFIRMAR PAGAMENTO</li>
        </ol>
    </li>
    <li>Pedidos com ENTREGA CONFIRMADA, São removidos da lista de pedidos, e armazenados em uma colunas pedidos finalizados: com as informações do pedido.</li>
    <li>só e possivel editar um pedido que possue as flags: Em preparo, Pronto</li>
    <li>Não é possivel alterar o pedido original, somente adicionar novos itens (mais pizzas ou mais refri)</li>
    <li>
        Qualquer pedido editado é recalculado seus atributos como:
        <ol>
            <li>nome do cliente</li>
            <li>produtos pedidos</li>
            <li>valor total</li>
            <li>Tempo de espera</li>
            <li>Forma de pagamento</li>
            <li>Se pagamento em dinheiro -> valor a voltar ao cliente se houver troco</li>
            <li>flag -> começa com "em preparo"</li>
            <li>se pix -> pagamento consfirmado? zerar o campo de troco</li>
            <li>pedido agrupado?</li>
            <li>se pedido agrupado: identificador do pedido e o proximo pedido agrupado deve ser sequencial;</li>
        </ol>
    </li>
    <li>a nova comanda de pedido editado deve conter escrito bem grande ++++++PEDIDO EDITADO++++++</li>
    <li>Pedidos confirmados como entregues, removidos da lista de pedidos e adicionados na lista de finalizados</li>
    <li>
        calcular rota
        <ol>
            <li>opções de checkbox devem ser mostrada com os nomes do motoboys</li>
            <li>Sera realizado uma busca por pedidos o a flag "PRONTO"</li>
            <li>para cada busca confirma se a bag do motoboy cabe o pedido.</li>
            <li>sempre que o pedido nao couber em um motoqueiro, informar ao usuario, onde sera exibido duas opções: "adicionar mesmo assim" e "ir para a proxima"
                <ul>
                    <li>
                        botao "adicionar mesmo assim", adiciona o pedido ao motoqueiro, e pergunta se quer adicionar mais pedidos a esse mtoqueiro
                        caso sim: repete o ciclo.
                        caso nao vai para o calculo de rota.
                    </li>
                     <li>
                       botão "ir para a proxima" repete o ciclo buscando a proxima entrega, (ignorando essa entrega atual).
                    </li>
                </ul>
            </li>
            <li>quando a bag do motoqueiro esta cheia, informar: bag cheia, "adicionar mais pedidos a esse motoqueiro" ou "Calcular Rota"</li>
            <li>se adicionar mais pedidos -> refaz o ciclo de adicionar pedidos ao motoqueiro</li>
            <li>se calcular rota -> pega todos os pedidos adicionados a bag do motoqueiro, e pelo endereço ou tempo de espera calcular qual deve ser entregue
            primeiro, retorna um lista com os pedidos em ordem de entrega, com os identificadores.
            </li>
        </ol>
    </li>
    <li>se confirmação do pagamento em pix, for feita após a impressão da comanda, sera necessario informar adicionar manualmente esse informação a comanda</li>
    <li>Pedidos prontons, recebm a flag pronto</li>
    <li>todo motoboy, possui uma bag</li>
</ul>

voce pode ver mais em:
ComoDeveFuncionar.txt