### README para `auth-service`

```markdown
# NailDesigner - Serviço de Autenticação

Este é o microserviço responsável pela autenticação de usuários, gerenciamento de identidades e geração de tokens JWT para a plataforma NailDesigner.

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.3.0 (ou a versão que você está usando)
* Spring Security
* Spring Data JPA
* JSON Web Tokens (JWT) - via biblioteca `jjwt`
* H2 Database (para ambiente de desenvolvimento/teste)
* Maven

## Pré-requisitos

* JDK 17 ou superior
* Maven 3.6+

## Configuração

As configurações principais da aplicação estão no arquivo `src/main/resources/application.properties`.
As configurações importantes incluem:

* `server.port` (padrão: 8081)
* Configurações do banco de dados (`spring.datasource.*`)
* `jwt.secret.key` (chave secreta para assinar os tokens JWT)

Este serviço depende da biblioteca `auth-service-api` para seus contratos de DTO e, opcionalmente, para implementar a interface do cliente Feign.

## Como Construir (Build)

Para compilar o serviço e gerar o artefato executável (`.jar`):

```bash
mvn clean package
Ou, para instalar o artefato no seu repositório Maven local:

Bash

mvn clean install
Como Executar
Pela IDE
Importe o projeto como um projeto Maven.
Execute a classe principal br.com.naildesigner.auth.AuthApplication.java.
Pelo Terminal
Após construir o projeto com mvn clean package, navegue até a pasta target/ e execute:

Bash

java -jar auth-service-0.0.1-SNAPSHOT.jar
Para ativar perfis específicos (ex: prod para usar MySQL, se configurado):

Bash

java -jar -Dspring.profiles.active=prod auth-service-0.0.1-SNAPSHOT.jar
Endpoints da API (Resumo)
POST /auth/register
Registra um novo usuário.
Corpo da requisição: RegisterRequest (definido em auth-service-api).
POST /auth/login
Autentica um usuário existente.
Corpo da requisição: LoginRequest (definido em auth-service-api).
Resposta: LoginResponse contendo o token JWT.
(Adicione aqui outros endpoints que seu serviço possa ter, como para validação de token ou gerenciamento de usuários, se aplicável)

