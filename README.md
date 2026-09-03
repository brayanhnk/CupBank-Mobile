# CupBank

Aplicativo mobile de banco fictício desenvolvido como projeto acadêmico utilizando Kotlin, Android Studio e Jetpack Compose.

## Visão geral

O CupBank é uma aplicação bancária fictícia criada para praticar o desenvolvimento de interfaces Android com Compose. O projeto foi baseado em um mockup com cinco telas principais:

1. Login
2. Início
3. Extrato
4. Transferência PIX
5. Perfil

Nesta primeira entrega, foi implementada a tela de Login como base para o desenvolvimento das próximas interfaces.

## Tecnologias utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Gradle

## Objetivo do projeto

O objetivo principal é desenvolver telas visualmente próximas ao mockup e aplicar conceitos de UI em Android, como:

- `Scaffold`
- `Box`
- `Column`
- `Row`
- `Surface`
- `Card`
- `Button`
- `Text`
- `OutlinedTextField`
- `Toast`
- `remember`
- `mutableStateOf`

## Status atual

### Tela 1 — Login

A tela de login já foi implementada e apresenta os seguintes elementos:

- fundo escuro inspirado no mockup;
- identidade visual em roxo;
- ícone e nome do aplicativo;
- slogan “Seu banco, do seu jeito”;
- campos para CPF e senha;
- botão “Entrar”;
- link de cadastro “Não tem conta? Cadastre-se”;
- feedback visual via `Toast`.

### Funcionamento atual

Os campos de CPF e senha são controlados com estado utilizando:

```kotlin
remember { mutableStateOf("") }
```

Quando o botão “Entrar” é pressionado:

- se CPF ou senha estiverem vazios, aparece uma mensagem solicitando o preenchimento;
- se ambos os campos forem preenchidos, uma mensagem confirma o login;
- a validação é apenas demonstrativa, sem autenticação real, banco de dados ou persistência.

### Composables criadas

Para organizar a interface, a tela foi dividida em composables reutilizáveis:

- `LoginScreen()`
- `CupBankBrand()`
- `LoginTextField()`

Esses componentes ajudam a manter o código mais organizado e fácil de reutilizar.

## Componentes do Jetpack Compose utilizados

| Componente | Uso na tela |
| --- | --- |
| `Scaffold` | Estrutura principal da tela |
| `Box` | Centralização do conteúdo |
| `Column` | Organização vertical dos elementos |
| `Row` | Organização de blocos e alinhamentos |
| `Surface` | Área visual da marca e elementos visuais |
| `OutlinedTextField` | Campos de CPF e senha |
| `Button` | Ação de login |
| `Text` | Títulos, mensagens e labels |
| `Toast` | Feedback após clique |
| `remember` + `mutableStateOf` | Controle de estado dos campos |

## Estrutura inicial do projeto

```text
CupBank/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── .../
│   │   │   │       ├── MainActivity.kt
│   │   │   │       └── LoginScreen.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
├── settings.gradle.kts
└── local.properties
```

> O nome das pastas dentro de `java/` pode variar conforme o pacote definido no projeto.

## Próximas etapas

### Tela 2 — Início

Status: planejada.

Funcionalidades esperadas:

- saudação ao usuário;
- saldo disponível;
- ações rápidas;
- extrato recente;
- barra de navegação inferior.

### Tela 3 — Extrato

Status: planejada.

Funcionalidades esperadas:

- título da tela;
- filtros de transações;
- lista de movimentações;
- diferenciação visual entre entradas e saídas;
- navegação inferior.

### Tela 4 — Transferência PIX

Status: disponível somente no mockup.

Funcionalidades previstas:

- campo de chave PIX;
- campo de valor;
- descrição opcional;
- botão para continuar.

### Tela 5 — Perfil

Status: disponível somente no mockup.

Funcionalidades previstas:

- foto e dados do usuário;
- status da conta;
- informações bancárias;
- opções de dados pessoais e cartões.

## Como executar o projeto

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
```

2. Abra a pasta do projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Crie ou selecione um emulador Android no Device Manager.
5. Execute o aplicativo pelo botão Run (`▶`).

## Conclusão

Este projeto está em fase inicial de desenvolvimento, com a interface de login já implementada e o restante das telas planejadas para próximas etapas. O foco atual é aprimorar a experiência visual e consolidar os conceitos de desenvolvimento Android com Jetpack Compose.