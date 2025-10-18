# Conversão do Projeto Android Crypto Monitor para Padrão Declarativo

## Resumo das Mudanças

Este documento descreve a conversão do projeto **android-crypto-monitor** do padrão XML para o padrão declarativo usando Jetpack Compose.

## Arquivos Criados/Modificados

### 1. Modelo de Dados
- **`model/TickerResponse.kt`** - Classes de modelo para resposta da API do MercadoBitcoin

### 2. Serviços de API
- **`service/MercadoBitcoinService.kt`** - Interface Retrofit para chamadas à API
- **`service/MercadoBitcoinServiceFactory.kt`** - Factory para criar instância do serviço

### 3. Interface do Usuário
- **`screens/CryptoMonitorScreen.kt`** - Tela principal convertida para Jetpack Compose
- **`MainActivity.kt`** - Atividade principal convertida para usar ComponentActivity

### 4. Configurações
- **`build.gradle.kts`** - Adicionadas dependências do Retrofit, Coroutines e Navigation Compose
- **`AndroidManifest.xml`** - Adicionada permissão de internet
- **`strings.xml`** - Adicionadas strings necessárias para a aplicação

## Funcionalidades Implementadas

### Interface Declarativa
- **Toolbar** com título da aplicação
- **Card principal** com informações da cotação
- **Botão de atualização** com indicador de carregamento
- **Tratamento de erros** com exibição de mensagens
- **Formatação de moeda** brasileira (R$)
- **Formatação de data** no padrão brasileiro

### Integração com API
- **Chamadas assíncronas** usando Coroutines
- **Tratamento de erros** HTTP e de rede
- **Atualização automática** dos dados da cotação do Bitcoin

## Estrutura Seguindo o Padrão do Professor

O projeto foi convertido seguindo a mesma estrutura do projeto **android--5-navigation-between-screens-main**:

- Uso de **Jetpack Compose** para UI declarativa
- **ComponentActivity** como base da MainActivity
- **Scaffold** para estrutura base
- **Material Design 3** para componentes
- **Navigation Compose** (preparado para futuras telas)

## Dependências Adicionadas

```kotlin
// Retrofit para chamadas de API
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Coroutines para operações assíncronas
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

// Navigation Compose
implementation("androidx.navigation:navigation-compose:2.6.0")
```

## Como Executar

1. Certifique-se de ter Java 11+ instalado
2. Execute o projeto no Android Studio
3. A aplicação irá fazer chamadas para a API do MercadoBitcoin
4. Use o botão "ATUALIZAR" para buscar novas cotações

## Funcionalidades da Tela

- **Exibição da cotação atual** do Bitcoin em tempo real
- **Data e hora da última atualização**
- **Botão para atualizar manualmente** os dados
- **Indicador de carregamento** durante as requisições
- **Tratamento de erros** com mensagens informativas
- **Design responsivo** seguindo Material Design 3

## Observações Técnicas

- O projeto mantém a mesma funcionalidade do original, mas agora usando UI declarativa
- Todas as chamadas de API são feitas de forma assíncrona
- A interface é totalmente responsiva e moderna
- O código segue as melhores práticas do Jetpack Compose
