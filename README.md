# Sistema de Gestão de Concessionária

Aplicação backend desenvolvida em Java com foco na aplicação prática dos pilares da Programação Orientada a Objetos (POO) para gerenciar o ciclo de vida operacional de uma concessionária de veículos.

## Funcionalidades

- **Controle de Estoque:** Cadastro de automóveis e motocicletas com validação de unicidade por número de chassi e consulta detalhada de status.
- **Fluxo de Montagem (PDI):** Máquina de estados que controla a transição operacional do veículo (`AGUARDANDO_MONTAGEM` -> `EM_MONTAGEM` -> `DISPONIVEL` -> `VENDIDO`), bloqueando vendas antes da conclusão da revisão técnica.
- **Gestão de Vendas:** Registro de transações vinculadas a clientes, cálculo automático de impostos e opcionais por categoria de veículo e geração de comprovante com faturamento acumulado.

## Arquitetura e Conceitos Aplicados

- **Encapsulamento:** Proteção de regras de integridade nos modelos (`Veiculo`, `Cliente`, `Venda`) e transição controlada de status.
- **Herança:** Reaproveitamento de estrutura e comportamento através da classe abstrata `Veiculo` especializada por `Carro` e `Moto`.
- **Polimorfismo:** Sobrescrita dos métodos `calcularPrecoFinal()` e `executarEtapaMontagem()`, permitindo regras distintas de precificação e inspeção técnica dentro de coleções genéricas.
- **Separação de Responsabilidades:** Divisão clara entre camada de domínio (`model`), regras de negócio (`service`) e interface de execução (`Main`).
