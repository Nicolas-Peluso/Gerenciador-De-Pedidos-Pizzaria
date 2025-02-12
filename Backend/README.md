Endpoints e configurações:

Versão do java: 23.0.1.
Versão do maven: 3.8.7.

Endpoints:
/
/Login
/Cadastro
/CadastroItem
/CadastroPedio

/ -> rota root retorna uma lista de pedidos, se o usuario estiver logado.

/Login POST -> recebe um json:
{
    "email": "",
    "senha": ""
}

realiza login se as informações estiverem de acordo.
Retorna 405 se algo der errado.

/Cadastro POST -> recebe um json:
{
    "nome": "",
    "cargo": "",
    "limiteSabrPizza": 0,
    "email": "",
    "senha": "",
    "nomePizzaria": "",
    "endereco": "",
    "telefone": "",
    "tempoMedioDelivery": ""
}
retorna erro 405 para erros.
Nome da pizzaria deve ser unico,
Email deve estar no formato padrão,
Senha deve ter mais de 8 digitos 1 numero e 1 caractere,
tempoMedioDelivery deve ser time: 00:00:00
nenhum campo pode estar vazio.

/CadastrarItem POST -> recebe um json com duas estruturas possiveis:
Acompanhamento:
{
    "tipo": "Acompanhamento",
    "nome": "Coca Cola Lata",
    "obs": "300ml",
    "tipoAcompanhamento": "teste",
    "preco": 7
}
Pizza:
{
    "tipo": "pizza",
    "nome": "Muçarela",
    "preco": 70,
    "tipoPizza": "pizza",
    "sabor": "queijo muçarela com fatias de tomate"
}
tipo só pode ser pizza ou Acompanhamento,
Nome do item deve ser unico,
tipoAcompanhamento é o tipo do acompanhamento ex: refrigerante, doce...; tipoPizza é o tipo da pizza ex: doce, calzone...;
preco deve ser maior que 1;

/CadastroPedido POST -> recebe um json:
{
    "nomeCliente": "marcos dos 2",
    "bairroCliente": "jd angela",
    "obsCliente": "",
    "numeroCasaCliente": 22,
    "nomeRuaCliente": "nelsom ribeiro",
    "aptoCliente": 0,
    "formaDePagamentoPedido": "dinheiro",
    "dinherioPedido": true,
    "valorDoCliente": 200,
    "pixPedido": false,
    "pagamentoConfirmadoPedido": false,
    "itensPedido": [
        {
            "nome": "Muçarela",
            "tipo": "pizza",
            "obs": "tirar a cebola"
        },
        {
            "nome": "Toscana",
            "tipo": "pizza",
            "obs": ""
        }
    ]
}     
retorna 405 para erros.
campos vazios, aptoCliente pode ser 0, obsCliente Pode ser "", valorCliente pode ser 0 se pagamento for diferente de dinheiro
clientes nao podem ter o mesmo nome sendo necessário adicionar um sobrenome a cada cliente
todas as informações do pedido devem ser preenchidas,
itensPedido, tipo deve ser pizza ou Acompanhamento (Acompanhemento nao deve ser o tipo do acompanhamento mas sim o tipo Acompanhamento).
se pagamento for dinheiro o valor do cliente deve ser maior que 0.
retorna um json com:

{
    "troco": 0.0,
    "Tempo de Espera": "",
    "Valor Total": 0.0
}
