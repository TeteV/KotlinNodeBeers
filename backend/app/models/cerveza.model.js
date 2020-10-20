module.exports = (sequelize, Sequelize) => {
    const Cerveza = sequelize.define("cerveza", {
      brand: {
        type: Sequelize.STRING
      },
      model: {
        type: Sequelize.STRING
      }
    },{timestamps: false});
  
    return Cerveza;
  };