# Orange Blood Heroes

O **Orange Blood Heroes** é um sistema de combate baseado em turnos, onde os jogadores controlam um grupo de heróis em batalhas contra inimigos. Este projeto é uma implementação básica desse sistema, utilizando tecnologias como Spring Boot e OpenAPI.

## Funcionalidades Principais

- **Combate por Turnos:** Os jogadores podem iniciar combates e escolher entre diferentes tipos de ataques (melee ou spell).
- **Gerenciamento de Heróis:** Os jogadores podem visualizar o status dos heróis em sua festa (party), incluindo sua saúde e mana.
- **Recuperação Automática:** Após cada turno, os heróis recuperam parte de sua saúde e mana, e a moral da festa é ajustada.

## Pré-requisitos

- JDK 17
- Maven 3.6.3 ou superior

## Instalação

1. **Clone o repositório para sua máquina local:**
   
```
git clone https://github.com/seu-usuario/orange-blood-heroes.git
```

2. **Navegue até o diretório do projeto:**

```
cd orangebloodheroes
```

3. **Execute o projeto utilizando Maven:**

```
mvn spring-boot:run
```

4. **Acesse o sistema em [http://localhost:8080](http://localhost:8080)**

## Configuração

O sistema utiliza as seguintes configurações:

- `application.properties`: Configurações gerais da aplicação, como porta do servidor e configurações de banco de dados.
- `openapi.yaml`: Definição da API OpenAPI (Swagger).

## Uso

### Combate

Para iniciar um combate, faça uma requisição GET para `/combat` com os parâmetros `heroId`, `combatResult` e `combatType`.

Exemplo:

```
GET http://localhost:8080/combat?heroId=1&combatResult=WIN&combatType=MELEE
```

### Situação da party (Party)

Para consultar o status da party, faça uma requisição GET para `/party`.

Exemplo:

```
GET http://localhost:8080/party
```

## Contribuição

Contribuições são bem-vindas! Para contribuir com este projeto, siga estas etapas:

1. Faça um Fork do projeto
2. Crie uma nova branch (`git checkout -b feature/nova-feature`)
3. Faça commit das suas alterações (`git commit -am 'Adicione uma nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Crie um novo Pull Request

