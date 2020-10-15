const { bicycles } = require("../models");
const db = require("../models");
const Bicycle = db.bicycles;
const Op = db.Sequelize.Op;

// Create and Save a new bicycle
exports.create = (req, res) => {
  // Validate request
  if (!req.body.brand || !req.body.model) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a bicycle object
  const bicycle = {
    brand: req.body.brand,
    model: req.body.model
  };

  // Save Tutorial in the database
  Bicycle.create(bicycle)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the bicycle."
      });
    });
};

// Retrieve all Tutorials from the database.
exports.findAll = (req, res) => { 
    bicycles.findAll()
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving tutorials."
        });
      });
};

// Find a single Tutorial with an id
exports.findOne = (req, res) => {
  let id = req.params.id;
  Bicycle.findByPk(id)
    .then(data => {
      res.send(data);
      if (!data){
        res.status(400).send({
          message:"No bicycle with this ID"
        })
      }
      return;
    })
    .catch(err=>{
      res.status(500).send({
        message: 
        err.message || "Someerror ocurred while retrieving ID"
      });
      return;
    });
    //este sale mal
    /*res.send (400).send({
      message: 
      "Id not found"
    });*/
};

// Update a Tutorial by the id in the request
exports.update = (req, res) => {
  
};

// Delete a Tutorial with the specified id in the request
exports.delete = (req, res) => {
  
};

// Delete all Tutorials from the database.
exports.deleteAll = (req, res) => {
  
};
