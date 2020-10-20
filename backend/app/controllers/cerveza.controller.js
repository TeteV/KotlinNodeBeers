const { cervezas } = require("../models");
const db = require("../models");
const Cerveza = db.cervezas;
const Op = db.Sequelize.Op;

// Create and Save a new cerveza
exports.create = (req, res) => {
  // Validate request
  if (!req.body.brand || !req.body.model) {
    res.status(400).send({
      message: "Content can not be empty!"
    });
    return;
  }

  // Create a cerveza object
  const cerveza = {
    brand: req.body.brand,
    model: req.body.model
  };

  // Save Tutorial in the database
  Cerveza.create(cerveza)
    .then(data => {
      res.send(data);
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Some error occurred while creating the cerveza."
      });
    });
};

// Retrieve all Tutorials from the database.
exports.findAll = (req, res) => {
  cervezas.findAll()
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
  Cerveza.findByPk(id)
    .then(data => {
      if (!data) {
        res.status(400).send({
          message: "No cerveza with this ID"
        })
      }
      res.send(data);
      return;
    })
    .catch(err => {
      res.status(500).send({
        message:
          err.message || "Someerror ocurred while retrieving ID"
      });
      return;
    });
};

// Update a Tutorial by the id in the request
exports.update = (req, res) => {
  const id = req.params.id;

  Cerveza.update(req.body, {
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "Cerveza was updated successfully."
        });
      } else {
        res.send({
          message: `Cannot update beer with id=${id}. Maybe Bicycle was not found or req.body is empty!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Error updating beer with id=" + id
      });
    });
};

// Delete a Tutorial with the specified id in the request
exports.delete = (req, res) => {
  const id = req.params.id;

  Cerveza.destroy({
    where: { id: id }
  })
    .then(num => {
      if (num == 1) {
        res.send({
          message: "beer was deleted successfully!"
        });
      } else {
        res.send({
          message: `Cannot delete beer with id=${id}. Maybe Bicycle was not found!`
        });
      }
    })
    .catch(err => {
      res.status(500).send({
        message: "Could not delete beer with id=" + id
      });
    });
};

