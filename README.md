# autorizador
repositorio para a serie de videos sobre clean archtecture e DDD

## :information_source: Videos no Youtube
[Playlist](https://www.youtube.com/playlist?list=PLl-Um_Xc4KnMPd5iB_ixeYTCw-eHLmG_k)

## :information_source: Objetivo
Criar um projeto que atenda as necessidades de negocio utilizando as estrategias de DDD e clean archtecture

## :information_source: Detalhes da implementacao
O projeto de dominio deve ser feito o mais puro possivel, qualquer adicao de lib de terceiros deve ser explicado o motivo claro do uso

## :information_source: Definicao de negocio
O projeto sera responsavel por processar um array de eventos, podendo ser por entrada padrao(stdin), mensageria (AMQP/STOMP), transmissao de arquivos(FTP), requisicoes web (HTTP) e devera processar 2 tipos de evento
```bash
    # ACCOUNT_EVENT -> processa criacao de uma nova conta
    {
      "account": {
      "active-card": bool,
      "available-limit": number
    }

    # TRANSACTION_EVENT -> processa uma transacao financeira
   {
      "transaction": {
      "merchant": string,
      "amount": number,
      "time": DateTime
    }
```

O retorno do processamento deve sempre seguir o formato, account referente a account processada e violations um array de string com a regras de validacao, caso nao tenha nenhum problema de validacao devera retornar [] array vazio
```bash
  [
    {
      "account: account,
      "violations": [ violations ]
    }
  ]
```

## :information_source: Regras de validacao
- [x] ACCOUNT_NOT_INITIALIZED -> nao deve permitir nenhum transacao se a conta ainda nao foi inicializada
- [x] CARD_NOT_ACTIVE -> nao deve processar nenhuma transacao caso o cartao nao esteja ativo
- [x] DOUBLED_TRANSACTION -> nao deve permitir mais de uma transacao do mesmo lojista com o mesmo valor em menos de 1 minuto
- [x] HIGH_FREQUENCY_SMALL_INTERVAL -> nao deve permitir mais de uma transacao do mesmo lojista ao mesmo tempo
- [x] INSUFFICIENT_LIMIT -> nao deve processar uma transacao caso a conta nao tenha o limite suficiente
- [x] ACCOUNT_ALREADY_CREATED -> nao deve permitir inicializar uma conta mais de uma vez

## :rocket: Requsito Tecnico
Projeto deve ter no minimo 98% de cobertura de testes
