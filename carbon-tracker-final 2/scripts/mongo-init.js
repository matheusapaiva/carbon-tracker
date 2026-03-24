// Script de inicialização do MongoDB
// Cria o usuário da aplicação e coleções iniciais

db = db.getSiblingDB('carbontracker');

db.createUser({
  user: 'carbonapp',
  pwd: 'carbonpassword',
  roles: [{ role: 'readWrite', db: 'carbontracker' }]
});

db.createCollection('empresas');
db.createCollection('emissoes');
db.createCollection('agendamentos');

// Dados iniciais de exemplo
db.empresas.insertMany([
  {
    nome: "EcoTech Soluções",
    setor: "Tecnologia",
    descricao: "Empresa de tecnologia sustentável"
  },
  {
    nome: "GreenEnergy Brasil",
    setor: "Energia Renovável",
    descricao: "Líder em energia solar e eólica"
  }
]);

print('MongoDB inicializado com sucesso!');
