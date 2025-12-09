### Introdução
Este projeto simula problemas de concorrência no sistema de vendas da AMATUR, onde múltiplos agentes tentam reservar o mesmo assento simultaneamente. O trabalho demonstra o cenário real de race condition, seguido das soluções usando sincronização, comunicação entre threads e controle de fluxo. O foco é entender como inconsistências acontecem e como as técnicas de concorrência evitam erros como overbooking.
Em um sistema de vendas de passagens concorridas, vários agentes acessam ao mesmo tempo o recurso crítico: o número de assentos disponíveis. 
Sem controle de concorrência, duas ou mais threads podem verificar o mesmo assento, acreditar que ele está disponível e realizar vendas duplicadas. Isso gera resultados incorretos como assentos negativos, vendas excedentes e inconsistência geral do sistema. O objetivo do projeto é implementar cada mecanismo de controle e entender como ele evita esse tipo de falha.
Contextualizando o exemplo a seguir, a classe ônibus possui apenas 5 assentos disponíveis, entretanto, devido ao erro explicado no texto anterior, 7 vendas foram realizadas.
##### Exemplo: 
<img width="258" height="244" alt="Imagem4" src="https://github.com/user-attachments/assets/f4d9ce37-3f71-4e73-a3f4-f20aa1cf0aed" />

 
###	Como o synchronized resolveu a inconsistência de dados
O problema fundamental da Parte I é que duas threads executam o trecho de código crítico ao mesmo tempo. Ambas verificam que existe um assento disponível e ambas realizam a venda, mesmo que apenas uma deveria conseguir. Isso ocorre porque a operação de verificar e decrementar o assento não é atômica.
Ao usar o synchronized, criamos uma seção crítica: apenas uma thread por vez pode executar o trecho que verifica e decrementa o número de assentos. Isso impede interleavings indesejados do processador. Assim, a operação completa passa a ser atômica e nenhuma outra thread pode interferir até que a primeira termine. O resultado é que os dados nunca entram em estado inconsistente e o overbooking deixa de ocorrer. O synchronized garante exclusão mútua, consistência e evita que duas threads façam compras simultaneamente com base no mesmo valor.
3.	Como o wait/notify economiza CPU evitando busy-waiting
Quando o sistema está lotado, uma thread poderia simplesmente tentar de novo várias vezes, verificando continuamente se um assento ficou disponível. Esse comportamento é conhecido como busy-waiting: a thread fica em um loop consumindo CPU sem necessidade. 
Além de ineficiente, isso degrada o desempenho do sistema. Com wait e notify, o comportamento muda completamente. Quando uma thread tenta comprar e não há assentos, ela entra automaticamente em estado de espera usando wait(). Ela não ocupa CPU, não fica em loop e não consome recursos. Ela fica totalmente parada até ser acordada. Quando uma passagem é cancelada, outra thread chama notify() ou notifyAll(), acordando as threads que estavam esperando. Nesse momento, elas retomam a execução exatamente do ponto onde pararam. 
Dessa forma, o padrão produtor-consumidor evita o desperdício de processamento, reduz carga no sistema e organiza a ordem de execução das threads de maneira eficiente e controlada, sem looping infinito e sem uso excessivo de CPU.

#### Resultados com o bloco sincronizado:
<img width="374" height="243" alt="Imagem1" src="https://github.com/user-attachments/assets/bd4fc0e1-fd7a-4902-b28c-89d7650c9828" />

#### Resultados com o wait/notify:
<img width="458" height="211" alt="Imagem2" src="https://github.com/user-attachments/assets/daf883c9-a4fe-4420-ae05-dd50bf7362ab" />

#### Resultados com o semáforo:
<img width="393" height="475" alt="Imagem3" src="https://github.com/user-attachments/assets/06c4f139-fe38-43f2-900a-a60a58a03f68" />
