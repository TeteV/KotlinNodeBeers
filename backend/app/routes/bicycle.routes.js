module.exports = app => {
    const bicycles = require("../controllers/bicycle.controller.js");
  
    var router = require("express").Router();
  
    // Create a new Tutorial
    router.post("/", bicycles.create);
  
    // Retrieve all bicycles
    router.get("/", bicycles.findAll);
  
    // Retrieve a single Tutorial with id
    router.get("/:id", bicycles.findOne);
  
    // Update a Tutorial with id
    router.put("/:id", bicycles.update);
  
    // Delete a Tutorial with id
    router.delete("/:id", bicycles.delete);
  
    // Delete all bicycles
    router.delete("/", bicycles.deleteAll);
  
    app.use('/api/bicycles', router);
  };