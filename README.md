## Custódia de Contratos Consignado

O microserviço Custódia de Contratos Consignado gerencia e armazena os contratos de empréstimos consignados, garantindo a integridade e disponibilidade das informações.

### Como Utilizar

1. **Clonar o Repositório**:
   ```sh
    git clone https://github.com/MatheusCavalari/custodia-contrato-consignado.git

2. **Executar a Aplicação:**:
- **Porta do Servidor: 8084**
- **Banco de Dados: H2 em memória**
   ```sh
    mvn spring-boot:run

### Endpoints

1. **Criar Contrato**: Endpoint para criar um novo contrato.
  - **URL**: `/v1/contrato`
  - **Método**: `POST`
  - **Corpo da Requisição**:
    ```json
    {
      "dataContrato": "yyyy-MM-dd",
      "idSimulacao": 1
    }
    ```
  - **Resposta de Sucesso**:
    - Código: `201 CREATED`
    - Corpo: Contrato criado

2. **Listar Contratos**: Endpoint para listar todos os contratos.
  - **URL**: `/v1/contrato`
  - **Método**: `GET`
  - **Resposta de Sucesso**:
    - Código: `200 OK`
    - Corpo: Lista de contratos

3. **Buscar Contrato por ID**: Endpoint para buscar um contrato pelo ID.
  - **URL**: `/v1/contrato/{id}`
  - **Método**: `GET`
  - **Parâmetros de URL**: `{id}` - ID do contrato a ser buscado
  - **Resposta de Sucesso**:
    - Código: `200 OK`
    - Corpo: Contrato encontrado

### Dependências

- Spring Boot Web
- Spring Boot DevTools
- Spring Boot Starter Test
- Spring Boot Starter Data JPA
- H2 Database
- Lombok
- Springdoc OpenAPI
- JUnit

### Arquitetura Hexagonal

O microserviço Custódia de Contratos Consignado segue a arquitetura hexagonal (ou ports and adapters), que separa a lógica de negócios da implementação técnica. Nessa arquitetura, as camadas são organizadas da seguinte forma:

- **Domínio**: Contém as entidades de negócio, os serviços e as interfaces que definem as operações do domínio.
- **Aplicação**: Implementa a lógica de negócios usando os serviços do domínio.
- **Adaptadores**: São responsáveis por conectar o domínio à infraestrutura externa, como bancos de dados e serviços externos.

### Versão do Java

O microserviço foi desenvolvido utilizando Java 17, aproveitando as últimas funcionalidades e melhorias da linguagem.

Framework para desenvolvimento: Spring Boot 3.0.12.

### Gerenciamento de Dependências

O Maven foi utilizado como gerenciador de dependências e para realizar o build da aplicação. Ele simplifica o processo de compilação e gerenciamento de dependências, facilitando o desenvolvimento e a manutenção do projeto.

## Autor

Matheus Cavalari Barbosa
