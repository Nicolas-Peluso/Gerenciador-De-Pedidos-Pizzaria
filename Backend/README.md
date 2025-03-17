Configurações do Projeto
Versão do Java: 23.0.1

Versão do Maven: 3.8.7

Endpoints
1. GET /getItens
Descrição: Retorna um JSON com todos os itens (pizzas e acompanhamentos) cadastrados na conta do usuário.

Resposta de Exemplo:

json
Copy
{
  "itens": [
    {
      "obsAcomp": "",
      "obs": "300ml",
      "nome": "Coca Cola Lata",
      "preco": 7.0,
      "tipo": "teste",
      "Id": 10
    },
    {
      "sabor": "calabresa com rodelas de tomate",
      "obs": "",
      "nome": "Toscana",
      "preco": 70.0,
      "tipo": "Calzone",
      "Id": 18
    },
    {
      "sabor": "queijo muçarela com fatias de tomate",
      "obs": "",
      "nome": "Muçarela",
      "preco": 70.0,
      "tipo": "pizza",
      "Id": 19
    }
  ]
}
2. POST /Login
Descrição: Realiza o login do usuário.

Entrada (JSON):

json
Copy
{
  "email": "",
  "senha": ""
}
Respostas:

Sucesso: Retorna os dados do usuário.

Erro: Retorna status 405 se algo der errado.

3. POST /Cadastro
Descrição: Cadastra um novo usuário (pizzaria).

Entrada (JSON):

json
Copy
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
Regras de Validação:

Nome da pizzaria deve ser único.

Email deve estar no formato válido.

Senha deve ter mais de 8 caracteres, 1 número e 1 caractere especial.

tempoMedioDelivery deve estar no formato HH:mm:ss.

Nenhum campo pode estar vazio.

Respostas:

Sucesso: Retorna os dados do usuário cadastrado.

Erro: Retorna status 405 para erros de validação.

4. POST /CadastrarItem
Descrição: Cadastra um novo item (pizza ou acompanhamento).

Entrada (JSON):

Estrutura para Acompanhamento:

json
Copy
{
  "tipo": "Acompanhamento",
  "nome": "Coca Cola Lata",
  "obs": "300ml",
  "tipoAcompanhamento": "teste",
  "preco": 7
}
Estrutura para Pizza:

json
Copy
{
  "tipo": "pizza",
  "nome": "Muçarela",
  "preco": 70,
  "tipoPizza": "pizza",
  "sabor": "queijo muçarela com fatias de tomate"
}
Regras de Validação:

tipo só pode ser pizza ou Acompanhamento.

Nome do item deve ser único.

preco deve ser maior que 1.

Respostas:

Sucesso: Retorna os dados do item cadastrado.

Erro: Retorna status 405 para erros de validação.

5. POST /CadastroPedido
Descrição: Cadastra um novo pedido.

Entrada (JSON):

json
Copy
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
Regras de Validação:

Campos obrigatórios não podem estar vazios.

aptoCliente pode ser 0.

obsCliente pode ser vazio ("").

valorDoCliente pode ser 0 se o pagamento for diferente de dinheiro.

Clientes não podem ter o mesmo nome (é necessário adicionar um sobrenome).

itensPedido deve conter itens válidos (tipo deve ser pizza ou Acompanhamento).

Se o pagamento for em dinheiro, valorDoCliente deve ser maior que 0.

Resposta de Sucesso (JSON):

json
Copy
{
  "troco": 0.0,
  "Tempo de Espera": "",
  "Valor Total": 0.0
}
Respostas:

Sucesso: Retorna o JSON acima.

Erro: Retorna status 405 para erros de validação.

Observações Gerais
Todos os endpoints retornam status 405 em caso de erros de validação ou regras de negócio.

Campos obrigatórios devem ser sempre preenchidos, exceto quando explicitamente permitido (como obsCliente e aptoCliente).

Para pagamento em dinheiro, o campo valorDoCliente deve ser maior que 0 para calcular o troco corretamente.
