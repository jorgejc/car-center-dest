package com.iudigital.carcenter;

import com.iudigital.carcenter.controller.CarController;
import com.iudigital.carcenter.domain.Car;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    
        public static void editCar(CarController carController){
            try{
                Scanner sc = new Scanner(System.in);
                
                System.out.println("Digite ID del carro: " );
                int id = sc.nextInt();
                System.out.println("id es: " + id);
                Car carExits = carController.obtenerCar(id);
                if(carExits == null){
                    System.out.println("No existe carro ");
                    return;
                }
                
                System.out.println("Digite la marca: ");
                String marca = sc.nextLine();
                System.out.println("La marca es: " + marca);
                
                System.out.println("Digite el modelo: " );
                String modelo = sc.nextLine();
                System.out.println("model = " + modelo);
                
                System.out.println("Digite el anho: ");
                String anho = sc.nextLine();
                System.out.println("El año del modelo es: " + anho);
                
                System.out.println("Digite el color: ");
                String color = sc.nextLine();
                System.out.println("color: " + color);
                
                System.out.println("Digite tipo de transmision: ");
                String transmission = sc.nextLine();
                System.out.println("transmission es: " + transmission);
                
                Car car = new Car();
                car.setMarca(marca);
                car.setModelo(modelo);
                car.setAnho(anho);
                car.setColor(color);
                car.setTransmission(transmission);
                carController.actualizarCarro(id, car);
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        public static void crear(CarController carController){
            try{
                Scanner sc = new Scanner(System.in);
                
                System.out.println("Digite la marca: ");
                String marca = sc.nextLine();
                System.out.println("Marca es: " + marca);
                System.out.println("--------------- ");
                
                System.out.println("Digite el modelo: ");
                String modelo = sc.nextLine();
                System.out.println("el modelo es: " + modelo);
                System.out.println("--------------- ");
                
                System.out.println("Digite el anho: ");
                String anho = sc.nextLine();
                System.out.println("El año del modelo es: " + anho);
                System.out.println("---------------------");
                
                System.out.println("Digite el color: ");
                String color = sc.nextLine();
                System.out.println("color: " + color);
                System.out.println("--------------- ");
                
                System.out.println("Digite tipo de transmision: ");
                String transmission = sc.nextLine();
                System.out.println("transmission es: " + transmission);
                System.out.println("--------------- ");
                
                Car car = new Car();
                car.setMarca(marca);
                car.setModelo(modelo);
                car.setAnho(anho);
                car.setColor(color);
                car.setTransmission(transmission);
                carController.crear(car);
                System.out.println("Carrro creado con exito: ");
                obtenerCarros(carController);
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        public static void obtenerCarros(CarController carController){
            try{
                List<Car> cars = carController.obtenerCarros();
                if(cars.isEmpty()){
                    System.out.println("No hay datos ");
                }else {
                    cars.forEach(car -> {
                        System.out.println("id: " + car.getId());
                        System.out.println("Marca: " + car.getMarca());
                        System.out.println("Modelo: " + car.getModelo());
                        System.out.println("Año: " + car.getAnho());
                        System.out.println("Color: " + car.getColor());
                        System.out.println("Transmission: " + car.getTransmission());
                        System.out.println("---------------------");
//                        System.out.println("id: " + car.getId() + "\n" + "Marca: " + car.getMarca() +
//                                "Modelo: " + car.getModelo() + "\n" + "Anho: " + car.getAnho() + "\n" +
//                                "Color: " + car.getColor() + "Transmision: " + car.getTransmission());
                    });
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        public static void eliminarCarro(CarController carController){
            try{
                Scanner sc = new Scanner(System.in);
                
                System.out.println("Digite el ID del carro: ");
                int id = sc.nextInt();
                System.out.println("el id del carro es: " + id);
                Car carExists = carController.obtenerCar(id);
                if(carExists == null){
                    System.out.println("No existe carro ");
                    return;
                }
                
                carController.eliminarCarro(id);
                System.out.println("Carro eliminado con exito ");
                obtenerCarros(carController);
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        public static void main(String[] args) {
            CarController carController = new CarController();
//            crear(carController);
//            obtenerCarros(carController);
//            eliminarCarro(carController);
            editCar(carController);
        }
}
