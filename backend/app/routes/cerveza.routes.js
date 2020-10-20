module.exports = app => {
    const cervezas = require("../controllers/cerveza.controller.js");
  
    var router = require("express").Router();
  
    // Create a new Tutorial
    router.post("/", cervezas.create);
  
    // Retrieve all cervezas
    router.get("/", cervezas.findAll);
  
    // Retrieve a single Tutorial with id
    router.get("/:id", cervezas.findOne);
  
    // Update a Tutorial with id
    router.put("/:id", cervezas.update);
  
    // Delete a Tutorial with id
    router.delete("/:id", cervezas.delete);
  
    // Delete all cervezas
    //router.delete("/", cervezas.deleteAll);
  
    app.use('/api/cervezas', router);
  };