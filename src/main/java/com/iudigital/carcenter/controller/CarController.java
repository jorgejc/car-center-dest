package com.iudigital.carcenter.controller;

import com.iudigital.carcenter.data.CarDao;
import com.iudigital.carcenter.domain.Car;
import java.sql.SQLException;
import java.util.List;

public class CarController {
 
    private CarDao carDao;
    
    public CarController(){
        
        carDao = new CarDao(); //creo un objeto carDao en el constructor
    }
    
    public List<Car> obtenerCarros() throws SQLException{
        return carDao.obtenerCarros();
    }
    
    public void crear (Car car) throws SQLException{
        carDao.crear(car);
    }
    
    public Car obtenerCar(int id) throws SQLException{
        return carDao.obtenerCar(id);
    }
    
    public void actualizarCarro(int id, Car car) throws SQLException{
        carDao.actualizarCarro(id, car);
    }
    
    public void eliminarCarro(int id) throws SQLException{
        carDao.eliminarCarro(id);
    }
    
}
