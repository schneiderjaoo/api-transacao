# Itaú Unibanco - Desafio de Programação

Este repositório contém a implementação de uma **API REST** que recebe transações e retorna estatísticas sobre elas. A API foi desenvolvida usando **Java/Kotlin** com **Spring Boot**. O desafio visa testar a habilidade de desenvolver uma API com boa organização, clareza de código, qualidade nos testes e preocupação com segurança.

## 1. Introdução

Este projeto implementa uma **API REST** para gerenciar transações financeiras e fornecer estatísticas sobre elas. A aplicação foi construída utilizando **Spring Boot**, **Java** ou **Kotlin**, e armazena os dados em memória, sem o uso de banco de dados. A API recebe transações, armazena-as, e fornece estatísticas como a quantidade, soma, média, valor mínimo e máximo das transações realizadas nos últimos 60 segundos.

## 2. Tecnologias Usadas

- **Spring Boot**: Framework para construção de APIs REST.
- **Java 11+ / Kotlin**: Linguagens de programação usadas no desenvolvimento da API.
- **JSON**: Formato de dados utilizado para troca de informações.

## 3. Como Rodar o Projeto

1. **Clone o repositório**:
    ```bash
    git clone <url_do_repositorio>
    cd <diretorio_do_repositorio>
    ```

2. **Instale as dependências**:
    ```bash
    gradle build
    ```

3. **Execute a aplicação**:
    ```bash
    gradle bootRun
    ```

A API estará disponível em `http://localhost:8080` por padrão.

## 4. Endpoints

### 4.1. Receber Transações: `POST /transacao`

Este endpoint recebe transações financeiras e valida as informações. A transação contém dois campos obrigatórios:

#### Requisição:
```json```
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}

valor: Valor em decimal da transação (obrigatório, não pode ser negativo).

dataHora: Data e hora da transação no formato ISO 8601 (obrigatório, não pode ser no futuro).

Respostas:
 - 201 Created: Transação aceita e registrada.
 - 422 Unprocessable Entity: A transação não foi aceita (ex: valor negativo ou data futura).
 - 400 Bad Request: JSON inválido ou mal formatado.

### 4.2. Limpar Transações: DELETE /transacao

Este endpoint apaga todas as transações armazenadas na memória.

Respostas:
200 OK: Todas as transações foram apagadas com sucesso.

### 4.3. Calcular Estatísticas: GET /estatistica
Este endpoint retorna as estatísticas das transações realizadas nos últimos 60 segundos. As estatísticas incluem:

count: Quantidade de transações.
sum: Soma total dos valores transacionados.
avg: Média dos valores das transações.
min: Menor valor transacionado.
max: Maior valor transacionado.

## 5. Regras de Validação

 - A transação deve ter um valor maior ou igual a 0.
 - A data da transação não pode ser no futuro.
 - O formato do JSON deve ser válido, com os campos valor e dataHora corretamente preenchidos.

## 6. Considerações
 - O projeto não utiliza banco de dados ou sistemas de cache. Todos os dados são armazenados em memória.

 - Todos os endpoints e respostas seguem exatamente o formato e estrutura especificados no desafio.


