package com.iudigital.carcenter.data;

import com.iudigital.carcenter.domain.Car;
import com.iudigital.carcenter.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Dao, la misión es comunicarse con la bd y realizar las operaciones tipicas de repositorio 
para cada uno de los distintos modelos de la app

**/
public class CarDao {

    private static final String GET_CARS = "select * from car";
    private static final String CREATE_CARS = "insert into car (marca, modelo, anho, color, transmission) values (?, ?, ?, ?, ?) ";
    private static final String GET_CAR_BY_ID = "select * from car where id = ? ";
    private static final String UPDATE_CARS = "update car set marca = ?, modelo = ?, anho = ?, color = ?, transmission = ? where id = ? ";
    private static final String DELETE_CARS = "delete from car where id = ? ";
    
    
    public List<Car> obtenerCarros() throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;  //prepared statement lo que hace es preparar la conexion, 
                                        
        ResultSet resultSet = null;  // nos trae el resultado de la consulta
        List<Car> cars = new ArrayList<>();
        
        try{
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CARS); //prepara la conexion, le pasamos la consulta, 
                                                                       //la cual no tiene ningun parametro
             //executequery ejecutamos la consulta que preparamos con preparedstatement
            resultSet = preparedStatement.executeQuery(); //obtenemos el flujo o resultado de la consulta en la variable resultset
            while(resultSet.next()){ //creamos un while, mientras existan datos vamos seteando 
                                     //los parametros en el objeto car
                                     
                Car car = new Car(); //creamos una instancia del objeto car 
                car.setId(resultSet.getInt("id")); //y vamos seteando los parametros uno a uno y los vamos agregando al objeto car
                car.setMarca(resultSet.getString("marca"));
                car.setModelo(resultSet.getString("modelo"));
                car.setAnho(resultSet.getString("anho"));
                car.setColor(resultSet.getString("color"));
                car.setTransmission(resultSet.getString("transmission"));
                cars.add(car);
            }
            return cars;
        }finally{  // 
            if(connection != null){
                connection.close();
            }
            
            if(preparedStatement != null){
                preparedStatement.close();
            }
            
            if(resultSet != null){
                resultSet.close();
            }
        }
    }
    //metodo void no devuelve nada solo crea, este metodo no hace un select a la bd solo hace insert
    public void crear(Car car) throws SQLException{  //llega el objeto car como parametro
                                                    //utiliza los objetos conection y preparedstatement 
        //hacemos una instancia de la clase conecction para conectarse a la base de datos y preparar la consulta
        Connection connection = null;
        PreparedStatement preparedStatement = null; //enviamos parametros a la consulta de forma dinamica
        
        try{
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREATE_CARS);
            preparedStatement.setString(1, car.getMarca());
            preparedStatement.setString(2, car.getModelo());
            preparedStatement.setString(3, car.getAnho());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setString(5, car.getTransmission());
            preparedStatement.executeUpdate();
            
        }catch(SQLException ex){
            throw ex;
        }
        finally{
            if(preparedStatement != null){
                preparedStatement.close();
            }
            
            if(connection != null){
                connection.close();
            }
        }
    }
    
    public Car obtenerCar(int id) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car car = null;
        
        try{
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_CAR_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMarca(resultSet.getString("marca"));
                car.setModelo(resultSet.getString("modelo"));
                car.setAnho(resultSet.getString("anho"));
                car.setColor(resultSet.getString("color"));
                car.setTransmission(resultSet.getString("transmission"));
            }
            return car;
        }finally{
            
            if(resultSet != null){
                resultSet.close();
            }
            
            if(preparedStatement != null){
                preparedStatement.close();
            }
            
            if(connection != null){
                connection.close();
            }
        }
    }
    
    public void actualizarCarro(int id, Car car) throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CARS);
            preparedStatement.setString(1, car.getMarca());
            preparedStatement.setString(2, car.getModelo());
            preparedStatement.setString(3, car.getAnho());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setString(5, car.getTransmission());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        }finally{
            
            if(preparedStatement != null){
                preparedStatement.close();
            }
            
            if(connection != null){
                preparedStatement.close();
            }
        }
    }
    
    public void eliminarCarro(int id) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_CARS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }finally{
            if(preparedStatement != null){
                preparedStatement.close();
            }
            
            if(connection != null){
                connection.close();
            }
        }
    }
}
