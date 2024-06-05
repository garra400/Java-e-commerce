Projeto de Engenharia de Software de uma lanchonete.
05/06/2024

É possível cadastrar um usuário no banco de dados, determinar se ele é admin (por default ele é cliente, podendo somente mudar diretamente no banco).
O sistema permite o usuário fazer login, caso cadastrado, e acessar páginas diferentes dependendo se ele é cliente ou admin.

O admin tem todo o acesso a criação, leitura e deletação de produtos e combos.
Combos são formados por produtos e possuem um desconto em % determinado pelo admin.
O cliente, por sua vez, pode apenas ver os produtos, adicioná-los ao carrinho e fazer o seu pagamento.

A linguagem utilizada foi o Java pelo IDE NetBeans e o banco de dados foi o MySQL (workbench para sua visualização) 
A fim de conseguir inicializar a aplicação é necessário ter dois arquivos instalados para fazer a conexão com o banco de dados, 
um é determinado pelas dependências do projeto no pom.xml
e o outro precisa ser instalado diretamente, colocando o mysql-connector-j-8.4.0 no 'drivers' em 'service'.

Além da inicialição do banco de dados na main:
'
 Class.forName("com.mysql.cj.jdbc.Driver");
conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_lanchonete", "USUARIO", "SENHA");
System.out.println("Conexão estabelecida com sucesso: " + conexao);
'
